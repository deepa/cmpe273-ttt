package tt;

public class TTTProxy implements tt.TTT {
  private String _endpoint = null;
  private tt.TTT tTT = null;
  
  public TTTProxy() {
    _initTTTProxy();
  }
  
  public TTTProxy(String endpoint) {
    _endpoint = endpoint;
    _initTTTProxy();
  }
  
  private void _initTTTProxy() {
    try {
      tTT = (new tt.TTTServiceLocator()).getTTT();
      if (tTT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)tTT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)tTT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (tTT != null)
      ((javax.xml.rpc.Stub)tTT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public tt.TTT getTTT() {
    if (tTT == null)
      _initTTTProxy();
    return tTT;
  }
  
  public boolean move(int idgame, java.lang.String position, java.lang.String mark) throws java.rmi.RemoteException{
    if (tTT == null)
      _initTTTProxy();
    return tTT.move(idgame, position, mark);
  }
  
  public void main(java.lang.String[] args) throws java.rmi.RemoteException{
    if (tTT == null)
      _initTTTProxy();
    tTT.main(args);
  }
  
  public int addPlayer(java.lang.String name) throws java.rmi.RemoteException{
    if (tTT == null)
      _initTTTProxy();
    return tTT.addPlayer(name);
  }
  
  public java.lang.String checkWin(int idgame) throws java.rmi.RemoteException{
    if (tTT == null)
      _initTTTProxy();
    return tTT.checkWin(idgame);
  }
  
  public void deletePlayer(int key) throws java.rmi.RemoteException{
    if (tTT == null)
      _initTTTProxy();
    tTT.deletePlayer(key);
  }
  
  public int startGame(int player1, int player2) throws java.rmi.RemoteException{
    if (tTT == null)
      _initTTTProxy();
    return tTT.startGame(player1, player2);
  }
  
  public int chooseOpponent(int idplayer) throws java.rmi.RemoteException{
    if (tTT == null)
      _initTTTProxy();
    return tTT.chooseOpponent(idplayer);
  }
  
  public int getOpponentID(int idplayer) throws java.rmi.RemoteException{
    if (tTT == null)
      _initTTTProxy();
    return tTT.getOpponentID(idplayer);
  }
  
  
}