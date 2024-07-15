package services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.*;

import domaine.CongeEtat;
import domaine.Conges;
import domaine.User;

@Stateless (name="CR")
public class CongesSession implements CongesSessionLocal, CongesSessionRemote {
	@PersistenceContext
	private EntityManager em;
	public List<Conges> getAllConges(){
		
		Query q=em.createQuery("Select c from Conges c");
		return q.getResultList();
		
		
	}
	 @Override
	    public List<Conges> getAllCongesById(Long id) {
	        Query q = em.createQuery("SELECT c FROM Conges c WHERE c.user.id = :userId");
	        q.setParameter("userId", id);
	        return q.getResultList();
	    }

	    @Override
	    public List<Conges> getAllCongesByState(CongeEtat etat) {
	        Query q = em.createQuery("SELECT c FROM Conges c WHERE c.etat = :state");
	        q.setParameter("state", etat);
	        return q.getResultList();
	    }

	    @Override
	    public List<Conges> getAllCongesByStateAndId(Long id, CongeEtat etat) {
	        Query q = em.createQuery("SELECT c FROM Conges c WHERE c.user.id = :userId AND c.etat = :state");
	        q.setParameter("userId", id);
	        q.setParameter("state", etat);
	        return q.getResultList();
	    }
	    
	    
	    
	 // Method to change state from SOLLICITE to VALIDE
	    @Override
	    public void validateConge(Long congeId) {
	        Conges conge = em.find(Conges.class, congeId);
	        if (conge != null && conge.getEtat() == CongeEtat.SOLLICITE) {
	            conge.setEtat(CongeEtat.VALIDE);
	            em.merge(conge);
	        }
	    }

	    // Method to change state from SOLLICITE to REFUSE
	    @Override
	    public void refuseCongeRequest(Long congeId) {
	        Conges conge = em.find(Conges.class, congeId);
	        if (conge != null && conge.getEtat() == CongeEtat.SOLLICITE) {
	            conge.setEtat(CongeEtat.REFUSE);
	            em.merge(conge);
	        }
	    }

	    // Method to change state from VALIDE to REFUSE
	    @Override
	    public void refuseValidatedConge(Long congeId) {
	        Conges conge = em.find(Conges.class, congeId);
	        if (conge != null && conge.getEtat() == CongeEtat.VALIDE) {
	            conge.setEtat(CongeEtat.REFUSE);
	            em.merge(conge);
	        }
	    }

	    // Method to stop an ongoing leave request and change state to ARRETE
	    @Override
	    public void stopOngoingConge(Long congeId) {
	        Conges conge = em.find(Conges.class, congeId);
	        if (conge != null && conge.getEtat() == CongeEtat.EN_COURS) {
	            conge.setEtat(CongeEtat.ARRETE);
	            em.merge(conge);
	        }
	    }
	    @Override
	    public void requestNewConge(Long userId, String description, Date dateDebut, Date dateFin) {
	        User user = em.find(User.class, userId);
	        if (user != null) {
	            Conges conge = new Conges(description, dateDebut, dateFin, null, CongeEtat.SOLLICITE, user);
	            em.persist(conge);
	        }
	    }
		@Override
		public void addConge(Conges conge) {
			 em.persist(conge);
			
		}
		@Override
		public Conges findCongeById(Long id) {
			 return em.find(Conges.class, id);
		}
		@Override
		public void updateConge(Conges conge) {
			 em.merge(conge);			
		}
		@Override
		public void removeConge(Long id) {
			 Conges conge = em.find(Conges.class, id);
		        if (conge != null) {
		            em.remove(conge);
		        }
		}
		@Override
		public void cancelConge(Long congeId) {
			Conges conge = em.find(Conges.class, congeId);
	        if (conge != null && conge.getEtat() == CongeEtat.SOLLICITE) {
	            conge.setEtat(CongeEtat.ANNULE);
	            em.merge(conge);
	        }
		}
	
		
	
		
		


	 

}
