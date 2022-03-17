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
 * Servlet implementation class Entry
 */
//@WebServlet("/Entry")
public class Entry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Entry() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TTTProxy proxy = new TTTProxy();
		proxy.setEndpoint("http://localhost:8080/ttt/services/TTT");	
		PrintWriter out = response.getWriter();
		String playerName = request.getParameter("playername");
		int playerID = proxy.addPlayer(playerName);
		out.println("Welcome " + playerName +". Your ID is: " + playerID +"<br><br>");
		int opponentID = proxy.chooseOpponent(playerID);
		request.setAttribute("playerID", Integer.toString(playerID));
		if(opponentID == 0)
		{
			out.println("No opponents available. Please wait<br><br>");
			out.println("<form name=\"form1\" method=\"get\" action=\"CheckStatus\">");
			out.println("<input type=\"submit\" value=\"Check Now\"><input type=\"hidden\" name=\"playerID\" value=\""+playerID+"\"</form>");
		}	
		else
		{
			out.println("You are playing against " + opponentID);
			request.getRequestDispatcher("game.jsp").include(request, response);
			request.setAttribute("opponentID", Integer.toString(opponentID));
		}
	}
}