package services;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import domaine.Conges;
import domaine.CongeEtat;

@Remote
public interface CongesSessionRemote {
	List<Conges> getAllConges();
	List<Conges> getAllCongesByUserId(Long userId);
    List<Conges> getAllCongesById(Long id);
    List<Conges> getAllCongesByState(CongeEtat etat);
    List<Conges> getAllCongesByStateAndId(Long id, CongeEtat etat);

    public void requestNewConge(Long userId, String description, Date dateDebut, Date dateFin);
    public void refuseCongeRequest(Long congeId);
    public void refuseValidatedConge(Long congeId);
    void stopOngoingConge(Long congeId);
    public void validateConge(Long congeId);
    
    void addConge(Conges conge);
    Conges findCongeById(Long id);
    void updateConge(Conges conge);
    void removeConge(Long id);
    void cancelConge(Long congeId);

    

}
