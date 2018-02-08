package beans;


public class Aeroport {
   private Integer idAeroport;
   private String nom;
   public String ville;
   
   public Aeroport(Integer idAeroport, String nom, String ville) 
	  {
		super();
		this.idAeroport = idAeroport;
		this.nom = nom;
		this.ville = ville;
	  }

   	public Aeroport() 
   	{
   		
   	}
	public Integer getIdAeroport() {
		return idAeroport;
	}
	
	public void setIdAeroport(Integer idAeroport) {
		this.idAeroport = idAeroport;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getVille() {
		return ville;
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}

	public String toString() {
		return "Aeroport [idAeroport=" + idAeroport + ", nom=" + nom + ", ville=" + ville + "]";
	}
	  
   

}