package pc;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import org.apache.commons.io.IOUtils;

import Utility.DatabaseConn;
import technical_service.UserRDG;
import ts.ChallengeTS;
import ts.UserTS;

/**
 * Servlet implementation class Register
 */
@WebServlet("/RefuseChallenge")
public class RefuseChallenge extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RefuseChallenge() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// this doPost was based on the goGet from the file that Thiel Stuart
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
		(new DatabaseConn()).init();

		try {

			String challengeId = request.getParameter("challenge");
			if (request.getSession(true).getAttribute("userid") == null) {
				request.setAttribute("message", "You must be logged in.");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(
						request, response);
			} else {
				int challanger = (int) request.getSession(true).getAttribute(
						"userid");
				System.out.println("Challanger " + challanger + " "
						+ "ChallangID " + challengeId);
				if (challengeId == null || challengeId.isEmpty()
						|| challengeId == "") {
					request.setAttribute("message",
							"You need to select a challenge.");
					request.getRequestDispatcher("WEB-INF/jsp/fail.jsp")
							.forward(request, response);

				} else {
					if (ChallengeTS.yourOwnChallenge(challengeId, challanger)) {
						ChallengeTS.withdrawChallenge(challengeId);
						request.setAttribute("message",
								"You successfully withdraw from challenge.");
						request.getRequestDispatcher("WEB-INF/jsp/success.jsp")
								.forward(request, response);
					} else if (ChallengeTS.yourChallenge(challengeId,
							challanger)) {
						ChallengeTS.declineChallenge(challengeId);
						request.setAttribute("message",
								"That user has been successfully refused challenge.");
						request.getRequestDispatcher("WEB-INF/jsp/success.jsp")
								.forward(request, response);
					} else {
						request.setAttribute("message",
								"You cannot refuse someone else challenge.");
						request.getRequestDispatcher("WEB-INF/jsp/fail.jsp")
								.forward(request, response);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			(new DatabaseConn()).tearDown();
		}
	}
}
