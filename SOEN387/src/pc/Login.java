package pc;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utility.DatabaseConn;
import ts.UserTS;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
		(new DatabaseConn()).init();
		try {
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");
			System.out.println("login "+user+" "+pass);
			if (user == null || user.isEmpty() || pass == null
					|| pass.isEmpty()) {
				request.setAttribute("message",
						"Please enter both a username and a password.");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(
						request, response);
			} else if ((int) UserTS.verify(user, pass)>0) {
				int usId = (int) UserTS.verify(user, pass);
				request.setAttribute("message", "Successfully logged in.");				
				request.getSession(true).setAttribute("userid",usId );
				request.getRequestDispatcher("WEB-INF/jsp/success.jsp")
						.forward(request, response);

			} else {
				request.setAttribute("message",
						"I do not recognize that username and password combination.");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(
						request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			(new DatabaseConn()).tearDown();
		}

	}

}
