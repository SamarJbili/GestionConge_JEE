package services;
import java.util.List;

import javax.ejb.Local;


import domaine.User;

@Local
public interface UserSessionLocal {
	
	    public User getUserById(Long userId) ;

	    public User getUserByLogin(String login,String password) ;
	    
	    public void addUser(User user) ;

	    public User updateUser(User user);
	    
	    public void deleteUser(Long userId);
	    
	    public User findUserById(Long id);
	    
	    public List<User> getAllUsers();
}
