package tt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.jws.WebService;

@WebService
public class TTT 
{
	public Connection con = null;
	public static ResultSet rs;
	public Statement stmt = null;
	public String a1,a2,a3,b1,b2,b3,c1,c2,c3;

	public TTT()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe","root","mypasswd");
			stmt = con.createStatement();
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public static void main(String[] args) throws SQLException 
	{
		TTT t = new TTT();
		Scanner s = new Scanner(System.in);
		System.out.println("Enter player1 name :");
		String player1 = s.nextLine();
		int idplayer1 = t.addPlayer(player1);
		int idplayer2 = t.chooseOpponent(idplayer1);

		//		System.out.println("Enter player2 name :");
		//		String player2 = s.nextLine();
		//		int idplayer2 = t.addPlayer(player2);

		if(idplayer2 == 0)
			return;

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
		t.closeDBCon();
	}	


	public int addPlayer(String name) throws SQLException 
	{
		String query0 = "select max(idplayer) as max from player" +";";
		rs = stmt.executeQuery(query0);
		int maxid = 0;
		while(rs.next())
		{
			maxid = rs.getInt("max");
		}
		int playerid = maxid + 1;
		String query = "Insert into player (idplayer,name) values ('"+ playerid +"','"+ name +"');";
		stmt.executeUpdate(query);	
		return playerid;
	}

	public int chooseOpponent(int idplayer) throws SQLException
	{
		String query0 = "select * from player where idopponent='0' and idplayer!='"+ idplayer +"';";
		rs = stmt.executeQuery(query0);
		int oppid = 0;
		while(rs.next())
		{
			rs.last();
			oppid = rs.getInt("idplayer");
		}
		if(oppid == 0)
			System.out.println("no opponents ready. please check again later..");
		else
		{
			String query = "update player set idopponent=" + oppid + " where idplayer=" + idplayer;
			System.out.println(query);
			stmt.executeUpdate(query);
			notifyOpponent(idplayer,oppid);
		}
		return oppid;
	}
	
	public int getOpponentID(int idplayer) throws SQLException
	{
		String query = "select idopponent from player where idplayer=" + idplayer;
		rs = stmt.executeQuery(query);
		int oppid = 0;
		while(rs.next())
		{
			oppid = rs.getInt("idopponent");
		}
		return oppid;
	}


	private void notifyOpponent(int idplayer, int oppid) throws SQLException {
		String query = "update player set idopponent=" + idplayer + " where idplayer=" + oppid;
		System.out.println(query);
		stmt.executeUpdate(query);
	}

	public void deletePlayer(int key) 
	{
		try
		{
			String query = "delete from player where idplayer = " + key;
			stmt.executeUpdate(query);
			System.out.println("delete successful");

		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public int startGame(int player1, int player2) throws SQLException
	{
		String query0 = "select max(idgame) as max from game" +";";
		rs = stmt.executeQuery(query0);
		int maxid = 0;
		while(rs.next())
		{
			maxid = rs.getInt("max");
		}
		int idgame = maxid + 1;
		String query = "insert into game (idgame,player1,player2) values ('"+ idgame +"','"+ player1 +"','"+ player2 +"')";
		System.out.println(query);
		stmt.executeUpdate(query);
		System.out.println("game started");
		return idgame;
	}

	private void closeDBCon()
	{
		try { 
			if(con != null) 
			{
				con.close();
			}
		} 
		catch(Exception e) { }
	}

	public boolean move(int idgame,String position,String mark) throws SQLException
	{
		//		update game set a1 = 'X', counter = 1 where idgame = 111;
		int counter = 0;
		String p = "-";
		System.out.println(position);
		boolean valid = (position.equals("a1") || position.equals("a2") || position.equals("a3") || position.equals("b1") || position.equals("b2") || position.equals("b3") || position.equals("c1") || position.equals("c2") || position.equals("c3"));
		System.out.println(valid);
		if(!valid)
		{
			System.out.println("invalid position");
			return false;
		}
		String query0 = "select " + position + ", counter from game where idgame=" + idgame +";";
		System.out.println(query0);
		stmt = con.createStatement();
		rs = stmt.executeQuery(query0);
		while(rs.next())
		{
			counter = rs.getInt("counter");
			p = rs.getString(position);
		}
		if((counter % 2 == 0 && mark.equals("O")) || (counter % 2 != 0 && mark.equals("X")) )
		{
			System.out.println("this is not your turn. wait for your opponent");
			return false;
		}
		if(!p.equals("-"))
		{
			System.out.println("position already filled. select another position");
			return false;
		}
		String query = "update game set " + position + "=" + "'" + mark + "',counter=" + ++counter +" where idgame=" + idgame;
		System.out.println(query);
		stmt.executeUpdate(query);
		return true;
	}

	public String checkWin(int idgame) throws SQLException
	{
		System.out.println("inside checkwin");
		String query = "select * from game where idgame=" + idgame;
		Statement st = con.createStatement();
		//		String a1,a2,a3,b1,b2,b3,c1,c2,c3;
		ResultSet rs = st.executeQuery(query);
		String winner = "nobody";
		int counter = 0;
		while (rs.next())
		{
			a1 = rs.getString("a1");
			a2 = rs.getString("a2");
			a3 = rs.getString("a3");
			b1 = rs.getString("b1");
			b2 = rs.getString("b2");
			b3 = rs.getString("b3");
			c1 = rs.getString("c1");
			c2 = rs.getString("c2");
			c3 = rs.getString("c3");
			counter = rs.getInt("counter");
		}
		System.out.println(a1+a2+a3+"\n"+b1+b2+b3+"\n"+c1+c2+c3);
		if(a1.equals(a2) && a2.equals(a3) && a1.equals("X") || b1.equals(b2) && b2.equals(b3) && b1.equals("X") || c1.equals(c2) && c2.equals(c3) && c1.equals("X") || a1.equals(b2) && b2.equals(c3) && a1.equals("X")|| a3.equals(b2) && b2.equals(c1) && a3.equals("X") ||a1.equals(b1) && b1.equals(c1) && a1.equals("X") || a2.equals(b2) && b2.equals(c2) && a2.equals("X") ||a3.equals(b3) && b3.equals(c3) && a3.equals("X") || a1.equals(a2) && a2.equals(a3) && a1.equals("X") || a1.equals(a2) && a2.equals(a3) && a1.equals("X") || a1.equals(a2) && a2.equals(a3) && a1.equals("X"))
		{
			System.out.println("X won!");
			winner = "X";
		}
		else if(a1.equals(a2) && a2.equals(a3) && a1.equals("O") || b1.equals(b2) && b2.equals(b3) && b1.equals("O") || c1.equals(c2) && c2.equals(c3) && c1.equals("O") || a1.equals(b2) && b2.equals(c3) && a1.equals("O")|| a3.equals(b2) && b2.equals(c1) && a3.equals("O") ||a1.equals(b1) && b1.equals(c1) && a1.equals("O") || a2.equals(b2) && b2.equals(c2) && a2.equals("O") ||a3.equals(b3) && b3.equals(c3) && a3.equals("O") || a1.equals(a2) && a2.equals(a3) && a1.equals("O") || a1.equals(a2) && a2.equals(a3) && a1.equals("O") || a1.equals(a2) && a2.equals(a3) && a1.equals("O"))
		{
			System.out.println("O won!");
			winner = "O";
		}
		else if(counter == 9)
		{
			System.out.println("tie game");
			winner = "tie";
		}
		if(winner.equals("X") || winner.equals("O") || winner.equals("tie"))
			endGame(idgame);
		return winner;
	}
	private void endGame(int key) 
	{
		try
		{
			String query = "delete from game where idgame = " + key;
			stmt.executeUpdate(query);
			System.out.println("end of game");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
}