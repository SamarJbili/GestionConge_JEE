package domaine;

import java.io.Serializable;

import java.lang.Long;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
public class User implements Serializable  {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column (unique=true)
    private String code;
	
	@Column (length=50)
    private String nom;
	
	@Column (length=50)
    private String prenom;
	
	@Temporal(TemporalType.DATE)
    private Date dateEmbauchement;
    
    @Column (length=50)
    private String login;
    
    @Column
    private int totyearLeaveDays; 
    
    @Size (min = 6, max = 10)
    private String password;
    
    @Pattern(regexp = "^(EMPLOYE|ADMIN)$")
    private String type;
    
    
    @OneToMany(mappedBy = "user")
    private List<Conges> conges;
    
     
    

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String code, String nom, String prenom, Date dateEmbauchement, String login,
			@Size(min = 6, max = 10) String password, @Pattern(regexp = "^(EMPLOYE|ADMIN)$") String type,
			List<Conges> conges) {
		super();
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.prenom = prenom;
		this.dateEmbauchement = dateEmbauchement;
		this.login = login;
		this.password = password;
		this.type = type;
		this.conges = conges;
		this.totyearLeaveDays=0;

	}



	public User(Long id, String code, String nom, String prenom, Date dateEmbauchement, String login,
			@Size(min = 6, max = 10) String password, @Pattern(regexp = "^(EMPLOYE|ADMIN)$") String type) {
		super();
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.prenom = prenom;
		this.dateEmbauchement = dateEmbauchement;
		this.login = login;
		this.password = password;
		this.type = type;
		this.totyearLeaveDays=0;
	}

	public List<Conges> getConges() {
		return conges;
	}

	public void setConges(List<Conges> conges) {
		this.conges = conges;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateEmbauchement() {
		return dateEmbauchement;
	}

	public void setDateEmbauchement(Date dateEmbauchement) {
		this.dateEmbauchement = dateEmbauchement;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTotyearLeaveDays() {
		return totyearLeaveDays;
	}

	public void setTotyearLeaveDays(int totyearLeaveDays) {
		this.totyearLeaveDays = totyearLeaveDays;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", code=" + code + ", nom=" + nom + ", prenom=" + prenom
				+ ", dateEmbauchement=" + dateEmbauchement + ", login=" + login + ", password=" + password + ", type="
				+ type + "]";
	}
    
    

}
