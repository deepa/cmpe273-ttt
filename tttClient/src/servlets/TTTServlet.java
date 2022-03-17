package servlets;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tt.TTTProxy;

/**
 * Servlet implementation class TTTServlet
 */
@WebServlet("/TTTServlet")
public class TTTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TTTServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TTTProxy t = new TTTProxy();
		t.setEndpoint("http://localhost:8080/ttt/services/TTT");		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter your name :");
		String player1 = s.nextLine();
		int idplayer1 = t.addPlayer(player1);		
		System.out.println("Enter player2 name :");
		String player2 = s.nextLine();
		int idplayer2 = t.addPlayer(player2);
		int idgame = t.startGame(idplayer1,idplayer2);
		String winner = "nobody";
		int i = 0;
		boolean played = false;
		while(i <= 8)
		{
			if(i % 2 == 0)
			{
				System.out.println("Player1 turn. Enter the position");
				String position = s.nextLine();
				played = t.move(idgame, position, "X");
			}
			else
			{
				System.out.println("Player2 turn. Enter the position");
				String position = s.nextLine();
				played = t.move(idgame, position, "O");
			}
			if(played == true)
				i++;
			else
				continue;
			winner = t.checkWin(idgame);
			if(winner.equals("X"))
			{
				System.out.println("player1 wins");
				break;
			}
			else if(winner.equals("O"))
			{
				System.out.println("player2 wins");
				break;
			}
		}
		t.deletePlayer(idplayer1);
		t.deletePlayer(idplayer2);
		//		t.closeDBCon();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
