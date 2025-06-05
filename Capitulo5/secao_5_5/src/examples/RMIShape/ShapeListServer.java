package examples.RMIShape;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class ShapeListServer {
	@SuppressWarnings("deprecation")
	public static void main(String args[]){
		//if(System.getSecurityManager() == null){
        //	System.setSecurityManager(new RMISecurityManager());
        //} else {
        //	System.out.println("Already has a security manager, so cant set RMI SM");
        //}
        //System.out.println("RMISecurityManager OK");
        try{
        	LocateRegistry.createRegistry(1099);
        	System.out.println("LocateRegistry OK");
            ShapeList aShapelist = new ShapeListServant();
            System.out.println("After create");
			Naming.bind("ShapeList", aShapelist);
            System.out.println("ShapeList server ready");
        }catch(Exception e) {
            System.out.println("ShapeList server main " + e.getMessage());
        }
    }
}