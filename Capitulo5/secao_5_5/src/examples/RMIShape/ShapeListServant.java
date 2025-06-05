package examples.RMIShape;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class ShapeListServant extends UnicastRemoteObject implements ShapeList{
    private Vector theList;
    private int version;
    
    public ShapeListServant()throws RemoteException{
        theList = new Vector();
        version = 0;
    }

  	public Shape newShape(GraphicalObject g) throws RemoteException{
  	    version++;
       	Shape s = new ShapeServant(g, version);
        theList.addElement(s);                
        return s;
     }

   	public  Vector allShapes()throws RemoteException{
        return theList;
    }

    public int getVersion() throws RemoteException{
        return version;
    } 
}