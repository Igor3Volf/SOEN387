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
 * Servlet implementation class Login
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Logout() {
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
			if (request.getSession(true).getAttribute("userid") == null
					|| request.getSession(true).getAttribute("userid")
							.equals("")) {
				request.setAttribute("message", "This user is not logged in.");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(
						request, response);
			} else {
				request.getSession(true).invalidate();
				request.setAttribute("message",
						"User has been successfully logged out.");
				request.getRequestDispatcher("WEB-INF/jsp/success.jsp")
						.forward(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}  finally {
			(new DatabaseConn()).tearDown();
		}

	}

}
