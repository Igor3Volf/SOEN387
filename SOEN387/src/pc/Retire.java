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
import ts.GameTS;
import ts.UserTS;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Retire")
public class Retire extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Retire() {
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

			String gameId = request.getParameter("game");
			if (request.getSession(true).getAttribute("userid") == null) {
				request.setAttribute("message", "You must be logged in.");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(
						request, response);
			} else {
				int userid= (int)request.getSession(true).getAttribute("userid");
				if (gameId == null || gameId.isEmpty() || gameId == "") {
					request.setAttribute("message",
							"You need to select a game.");
					request.getRequestDispatcher("WEB-INF/jsp/fail.jsp")
							.forward(request, response);

				} else {
					if(!UserTS.checkGame(userid, gameId)){
						request.setAttribute("message",
								"It is not your game.");
						request.getRequestDispatcher("WEB-INF/jsp/fail.jsp")
								.forward(request, response);
					}
					else {
						if(!UserTS.retire(userid,gameId)){
							request.setAttribute("message",
									"This user is already retired.");
							request.getRequestDispatcher("WEB-INF/jsp/fail.jsp")
									.forward(request, response);
						}else{	
							request.setAttribute("message",
									"That user has been successfully retired.");
							request.getRequestDispatcher("WEB-INF/jsp/success.jsp")
									.forward(request, response);
						}
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
