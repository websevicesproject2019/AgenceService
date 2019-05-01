package entities;

import java.net.MalformedURLException;
import java.rmi.*;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;
import java.util.List;

import dao.IAgenceData;
import services.AgenceService;


public class DemoAgence {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		// TODO Auto-generated method stub
		//AgenceService service = new AgenceService();
		IAgenceData objetServeur = (IAgenceData) Naming.lookup("rmi://127.0.0.1:1099/AS");
		//serveur.ServiceRMI service = new serveur.ServiceRMI();
		Agence ag = new Agence();
		//ag.setNumag(10);
		ag.setNomag("CBAO");
		ag.setAdresseag("Gueule Tape");
		try {
			objetServeur.addAgence(ag);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("test");
		
		
		List<Agence> l = objetServeur.listAgences();
		for(Agence a:l)
		{
			System.out.println(a.getNumag()+" "+a.getNomag()+" "+a.getAdresseag());
		}
		;
		
		
		
	}

}
