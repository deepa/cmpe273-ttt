/**
 * TTTService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tt;

public interface TTTService extends javax.xml.rpc.Service {
    public java.lang.String getTTTAddress();

    public tt.TTT getTTT() throws javax.xml.rpc.ServiceException;

    public tt.TTT getTTT(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
