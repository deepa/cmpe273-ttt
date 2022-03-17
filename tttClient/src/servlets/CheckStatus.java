package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tt.TTTProxy;

/**
 * Servlet implementation class CheckStatus
 */
@WebServlet("/CheckStatus")
public class CheckStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TTTProxy proxy = new TTTProxy();
		PrintWriter out = response.getWriter();
		int idplayer = Integer.parseInt((String)request.getParameter("playerID"));
		out.println("Your id is :" + idplayer);
		int opponentid = proxy.getOpponentID(idplayer);
		if(opponentid == 0)
		{
			out.println("An opponent is still not available. Go back and check again.");
		}
		else
		{
			out.println("An opponent is now ready<br>You will be playing against :" + opponentid);
			request.getRequestDispatcher("game.jsp").include(request, response);
			request.setAttribute("opponentID", Integer.toString(opponentid));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post method not supported");
	}

}
