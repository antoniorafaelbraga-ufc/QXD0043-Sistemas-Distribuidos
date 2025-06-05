package examples.RMIShape;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ShapeServant extends UnicastRemoteObject implements Shape {
	int myVersion;
	GraphicalObject theG;

	public ShapeServant(GraphicalObject g, int version) throws RemoteException {
		theG = g;
		myVersion = version;
	}

	public int getVersion() throws RemoteException {
		return myVersion;
	}

	public GraphicalObject getAllState() throws RemoteException {
		return theG;
	}

}