package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.*;

public interface IAgenceData extends Remote {

	public void addAgence(Agence ag) throws RemoteException;
	public void updateAgence(Agence ag) throws RemoteException;
	public void deleteAgence(Agence ag) throws RemoteException;
	public List<Agence> listAgences() throws RemoteException;
}
