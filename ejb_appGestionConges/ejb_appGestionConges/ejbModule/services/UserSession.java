package services;

import domaine.User;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless (name="UR")
public class UserSession implements UserSessionLocal , UserSessionRemote {
	
	@PersistenceContext
    private EntityManager em;
	
 
    @Override
    public User getUserById(Long userId) {
        return em.find(User.class, userId);
    }

    @Override
    public User getUserByLogin(String login, String password) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :password", User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        List<User> users = query.getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    
    @Override
    public void addUser(User user) {
        em.persist(user);
    }
    
    public void incrementLeaveDays(User u,int x) {
    	
    	int t = u.getTotyearLeaveDays();
    	u.setTotyearLeaveDays(t+x);
    	
    };

    
   
    @Override
    public void deleteUser(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }
	@Override
	public User findUserById(Long id) {
		 return em.find(User.class, id);
	}
	@Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
	
	
	
	@Override
    public User updateUser(User user) {
        User existingUser = em.find(User.class, user.getId());
        if (existingUser != null) {
            existingUser.setCode(user.getCode());
            existingUser.setNom(user.getNom());
            existingUser.setPrenom(user.getPrenom());
            existingUser.setDateEmbauchement(user.getDateEmbauchement());
            existingUser.setLogin(user.getLogin());
            existingUser.setPassword(user.getPassword());
            existingUser.setType(user.getType());
            existingUser = em.merge(existingUser);
        }
        return existingUser;
    }
	
}
