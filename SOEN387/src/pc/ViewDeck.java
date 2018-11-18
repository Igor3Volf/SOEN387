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

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import org.apache.commons.io.IOUtils;

import Utility.DatabaseConn;
import ts.CardTS;
import ts.DeckTS;
import ts.UserTS;

/**
 * Servlet implementation class Register
 */
@WebServlet("/ViewDeck")
public class ViewDeck extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewDeck() {
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
						"There is no users.");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp")
						.forward(request, response);

			} else {
				int uId=(int)request.getSession(true).getAttribute("userid");
				DeckTS d = DeckTS.findDeck(uId); 
				if(d==null){
					request.setAttribute("message",
							"This user does not have a deck.");
					request.getRequestDispatcher("WEB-INF/jsp/fail.jsp")
							.forward(request, response);
				}
				else{
					System.out.println("Deck cards ");
					List<CardTS> deck =d.getCards();

					request.setAttribute("deckid", d.getDeckId());
					request.setAttribute("deck", deck);
					request.getRequestDispatcher("WEB-INF/jsp/viewdeck.jsp")
							.forward(request, response);
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
