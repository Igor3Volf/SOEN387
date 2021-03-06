package pc;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








import Utility.DatabaseConn;
import ts.BoardTS;
import ts.ChallengeTS;
import ts.HandTS;
import ts.UserTS;

/**
 * Servlet implementation class Register
 */
@WebServlet("/ViewHand")
public class ViewHand extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewHand() {
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
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
		(new DatabaseConn()).init();
		try {			
			if (request.getSession(true).getAttribute("userid") == null) {
				request.setAttribute("message",
						"You must be logged in.");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp")
						.forward(request, response);
			} else {
				int userId = (int)request.getSession(true).getAttribute("userid");
				if(request.getParameter("game")==null||request.getParameter("game")==""||request.getParameter("game").isEmpty())
				{
					request.setAttribute("message", "You did not select which game to output.");
					request.getRequestDispatcher("WEB-INF/jsp/fail.jsp")
							.forward(request, response);
				}
				else
				{
					String gameId = request.getParameter("game");					
					if(UserTS.checkGame(userId, gameId))
					{
						Map<Integer,Integer> lhand = HandTS.listHand(userId,gameId);
						request.setAttribute("hand",lhand);
						request.getRequestDispatcher("WEB-INF/jsp/viewhand.jsp")
								.forward(request, response);
					}else
					{
						request.setAttribute("message", "You do not belong to this game.");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// this doPost was based on the goGet from the file that Thiel Stuart
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
}
