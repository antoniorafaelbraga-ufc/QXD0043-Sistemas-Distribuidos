package examples.RMIShape;

import java.rmi.*;

public interface Shape extends Remote {
	int getVersion() throws RemoteException;
	GraphicalObject getAllState() throws RemoteException;
}