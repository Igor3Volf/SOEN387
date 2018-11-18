package pc;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Utility.DatabaseConn;
import ts.UserTS;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// this doPost was based on the goGet from the file that Thiel Stuart
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		try {
			response.getWriter().append("Served at: ")
					.append(request.getContextPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		(new DatabaseConn()).init();
		try {

			String user = request.getParameter("user");
			String pass = request.getParameter("pass");
			if (user == null || user.isEmpty() || pass == null
					|| pass.isEmpty()) {
				request.setAttribute("message",
						"Please enter both a username and a password.");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(
						request, response);

			} else {
				if (UserTS.contains(user)) {
					request.setAttribute("message",
							"That user has already registered.");
					request.getRequestDispatcher("WEB-INF/jsp/fail.jsp")
							.forward(request, response);
				} else {
					UserTS newU = UserTS.register(user, pass);
					
					System.out.println("Session Id " + newU.getUserId());
					request.getSession(true).setAttribute("userid", newU.getUserId());
					request.setAttribute("message",
							"That user has been successfully registered.");
					request.getRequestDispatcher("WEB-INF/jsp/success.jsp")
							.forward(request, response);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			(new DatabaseConn()).tearDown();
		}
	}
}
