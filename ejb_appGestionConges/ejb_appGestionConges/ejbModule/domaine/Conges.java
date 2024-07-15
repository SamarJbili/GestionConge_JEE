package domaine;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Entity
public class Conges implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(length = 255)
	    private String description;

	    @Temporal(TemporalType.DATE)
	    private Date dateDebut;

	    @Temporal(TemporalType.DATE)
	    private Date dateFin;

	    @Temporal(TemporalType.DATE)
	    private Date dateRupture;

	    @Enumerated(EnumType.STRING)
	    private CongeEtat etat;
	    
	    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	    private User user;
	    
	    
	    
	    
	    
	    public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Conges() {
			super();
			
		}
		
		public Conges(String description, Date dateDebut, Date dateFin, Date dateRupture, CongeEtat etat,User user) {
			super();
			this.description = description;
			this.dateDebut = dateDebut;
			this.dateFin = dateFin;
			this.dateRupture = dateRupture;
			this.etat = etat;
			this.user=user;
		}
		

		

		public Conges(String description, Date dateDebut, Date dateFin, Date dateRupture, CongeEtat etat) {
			super();
			this.description = description;
			this.dateDebut = dateDebut;
			this.dateFin = dateFin;
			this.dateRupture = dateRupture;
			this.etat = etat;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Date getDateDebut() {
			return dateDebut;
		}

		public void setDateDebut(Date dateDebut) {
			this.dateDebut = dateDebut;
		}

		public Date getDateFin() {
			return dateFin;
		}

		public void setDateFin(Date dateFin) {
			this.dateFin = dateFin;
		}

		public Date getDateRupture() {
			return dateRupture;
		}

		public void setDateRupture(Date dateRupture) {
			this.dateRupture = dateRupture;
		}
		

		public CongeEtat getEtat() {
			return etat;
		}

		public void setEtat(CongeEtat etat) {
			this.etat = etat;
		}

		@Override
		public String toString() {
			return "Conges [id=" + id + ", description=" + description + ", dateDebut=" + dateDebut + ", dateFin="
					+ dateFin + ", dateRupture=" + dateRupture + ", etat=" + etat + "]";
		}
		

}
