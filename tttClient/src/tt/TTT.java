/**
 * TTT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tt;

public interface TTT extends java.rmi.Remote {
    public boolean move(int idgame, java.lang.String position, java.lang.String mark) throws java.rmi.RemoteException;
    public void main(java.lang.String[] args) throws java.rmi.RemoteException;
    public int addPlayer(java.lang.String name) throws java.rmi.RemoteException;
    public java.lang.String checkWin(int idgame) throws java.rmi.RemoteException;
    public void deletePlayer(int key) throws java.rmi.RemoteException;
    public int startGame(int player1, int player2) throws java.rmi.RemoteException;
    public int chooseOpponent(int idplayer) throws java.rmi.RemoteException;
    public int getOpponentID(int idplayer) throws java.rmi.RemoteException;
}
