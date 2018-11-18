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
import Utility.DatabaseConn;
import ts.ChallengeTS;
import ts.UserTS;

/**
 * Servlet implementation class Register
 */
@WebServlet("/ChallengePlayer")
public class ChallengePlayer extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChallengePlayer() {
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

			String challengee = request.getParameter("player");			
			if (request.getSession(true).getAttribute("userid") == null) {
				request.setAttribute("message", "You must be logged in");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(
						request, response);
			} else if (challengee == null || challengee.isEmpty()) {
				request.setAttribute("message", "You must enter a challengee.");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(
						request, response);

			} else if (Integer.parseInt(challengee)==(int)(request.getSession(true).getAttribute(
					"userid"))) {
				request.setAttribute("message",
						"You cannot challenge your self.");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(
						request, response);
			} else {
				int challenger = (int) request.getSession(true).getAttribute(
						"userid");
				int deckId = UserTS.hasDeck(challenger);
				if (deckId <= 0) {
					request.setAttribute("message", "You must have a deck.");
					request.getRequestDispatcher("WEB-INF/jsp/fail.jsp")
							.forward(request, response);
				} else {
					if (!UserTS.exist(challengee)) {
						request.setAttribute("message",
								"This Challangee does not exist.");
						request.getRequestDispatcher("WEB-INF/jsp/fail.jsp")
								.forward(request, response);
					} else {
						ChallengeTS.challenge(challenger, challengee);
						request.setAttribute("message",
								"The challenge have been created.");
						request.getRequestDispatcher("WEB-INF/jsp/success.jsp")
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
