package services;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.IAgenceData;
import entities.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
//import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

//import java.net.MalformedURLException;

public class AgenceService extends  UnicastRemoteObject implements IAgenceData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em;
	
	 public AgenceService() throws RemoteException {
	    

	    try {
	   
	   EntityManagerFactory emf = Persistence.createEntityManagerFactory("AGENCE");
	   this.em = emf.createEntityManager();
	
	
	    } catch (Exception e) {
       System.out.println(e.getMessage());
	
	    }

	     
	 }

	 
	public void addAgence(Agence ag) throws RemoteException {
				// TODO Auto-generated method stub
	    	   /*creation d'une transaction */
				EntityTransaction transaction = em.getTransaction();
			/* Demarage de la transaction */
				transaction.begin();
				try{
					/* Enregistrer l'agence dans la base de donnees */
						em.persist(ag);
						/* Validation de la transaction si ok */
						transaction.commit();
					}
					catch(Exception e){
						/* Annulation de la transaction si KO */
						transaction.rollback();
						e.printStackTrace();					
					}
			}
	       
		public void updateAgence(Agence agence) throws RemoteException
	       {
	    	   int num = agence.getNumag();
	    	   Query query = em.createQuery("select ag from Agence ag where ag.numag = "+num );
	    	       // query.setParameter("x", agence.getNumag());
	    	        Agence ag = (Agence) query.getSingleResult();
	    	        ag.setNomag(agence.getNomag());
	    	        ag.setAdresseag(agence.getAdresseag());
	    	        /*creation d'une transaction */
					EntityTransaction transaction = em.getTransaction();
				/* Demarage de la transaction */
					transaction.begin();
					try{
						em.persist(ag);
						/* Validation de la transaction si ok */
						transaction.commit();
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
	       }
			@SuppressWarnings("unchecked")
			public List<Agence> listAgences() throws RemoteException {
				// TODO Auto-generated method stub
				Query query = em.createQuery("select ag from Agence ag");
				return query.getResultList();
			}
			
			public void deleteAgence(Agence ag) throws RemoteException {
				// TODO Auto-generated method stub
				int num = ag.getNumag();
				EntityTransaction transaction = em.getTransaction();
				transaction.begin();
				try {
					 @SuppressWarnings("unused")
					int query = em.createQuery("delete from Agence ag where ag.numag = "+num ).executeUpdate();
					 transaction.commit();
				}
				catch(Exception ex)
				{
					transaction.rollback();
					ex.printStackTrace();
					
				}
		    	   
				
			}
			
			

		public static void main(String args[]) {
	       AgenceService service;
	       try {
	    	   service = new AgenceService();
	    	   //LocateRegistry.createRegistry(1099);
	    	   LocateRegistry.createRegistry(1099);
	    	   //System.setProperty("java.rmi.server.hostname","192.168.43.49");
	    	   Naming.rebind("rmi://127.0.0.1:1099/AS",service);
	    	   System.out.println("binding successfulled");
	       } catch (RemoteException e) {
	    	   // TODO Auto-generated catch block
	    	   e.printStackTrace();
	       	} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    
}

