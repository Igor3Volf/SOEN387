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
import ts.DeckTS;
import ts.UserTS;

/**
 * Servlet implementation class Register
 */
@WebServlet("/UploadDeck")
public class UploadDeck extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadDeck() {
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

			String deck = request.getParameter("deck");

			System.out.println(deck);		
			
			if (deck == null || deck.isEmpty()) {
				request.setAttribute("message",
						"Please submit a complete.");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(
						request, response);

			} else {
				DeckTS d = DeckTS.proccess(deck);				
				if (d.getDeckSize()!=40) {
					request.setAttribute("message",
							"The deck size is invalide.");
					request.getRequestDispatcher("WEB-INF/jsp/fail.jsp")
							.forward(request, response);
				} else {
					if(request.getSession(true).getAttribute("userid")==null){
						request.setAttribute("message",
								"You must be logged in.");
						request.getRequestDispatcher("WEB-INF/jsp/fail.jsp")
								.forward(request, response);
					}else{
						int uid = (int)request.getSession(true).getAttribute("userid");
						System.out.println("user id "+uid+ " deck id :"+d.getDeckId());
						DeckTS.upload(d,uid);			

						request.setAttribute("message",
								"The deck has been uploaded.");
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
