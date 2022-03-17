/**
 * TTTServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tt;

public class TTTServiceLocator extends org.apache.axis.client.Service implements tt.TTTService {

    public TTTServiceLocator() {
    }


    public TTTServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TTTServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TTT
    private java.lang.String TTT_address = "http://localhost:8080/ttt/services/TTT";

    public java.lang.String getTTTAddress() {
        return TTT_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TTTWSDDServiceName = "TTT";

    public java.lang.String getTTTWSDDServiceName() {
        return TTTWSDDServiceName;
    }

    public void setTTTWSDDServiceName(java.lang.String name) {
        TTTWSDDServiceName = name;
    }

    public tt.TTT getTTT() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TTT_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTTT(endpoint);
    }

    public tt.TTT getTTT(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            tt.TTTSoapBindingStub _stub = new tt.TTTSoapBindingStub(portAddress, this);
            _stub.setPortName(getTTTWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTTTEndpointAddress(java.lang.String address) {
        TTT_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (tt.TTT.class.isAssignableFrom(serviceEndpointInterface)) {
                tt.TTTSoapBindingStub _stub = new tt.TTTSoapBindingStub(new java.net.URL(TTT_address), this);
                _stub.setPortName(getTTTWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("TTT".equals(inputPortName)) {
            return getTTT();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tt", "TTTService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tt", "TTT"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TTT".equals(portName)) {
            setTTTEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
