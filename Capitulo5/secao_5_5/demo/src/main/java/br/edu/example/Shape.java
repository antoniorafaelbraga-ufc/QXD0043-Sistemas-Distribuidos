package br.edu.example;

import java.rmi.*;

public interface Shape extends Remote {
	int getVersion() throws RemoteException;
	GraphicalObject getAllState() throws RemoteException;
}