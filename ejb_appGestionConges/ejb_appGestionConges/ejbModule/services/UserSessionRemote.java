package services;




import java.util.List;

import javax.ejb.Remote;

import domaine.User;

@Remote
public interface UserSessionRemote {
	public User getUserById(Long userId) ;

    public User getUserByLogin(String login ,String password) ;
    
    public void addUser(User user) ;

    public User updateUser(User user); 
    
    public void deleteUser(Long userId);
    
    public User findUserById(Long id);
    
    public List<User> getAllUsers();
    public void incrementLeaveDays(User u,int x);


}
