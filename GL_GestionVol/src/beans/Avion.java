package beans;



public class Avion {

   private Integer id;
   private String reference;
   private String nomAvion;
   private Integer nbplace;
   		public Avion()
   		{
   			
   		}
		public Avion(Integer id, String reference, String nomAvion, Integer nbplace) 
		 {
			super();
			this.id = id;
			this.reference = reference;
			this.nomAvion = nomAvion;
			this.nbplace = nbplace;
		  }
		public Avion(String reference, String nomAvion, Integer nbplace) {
			super();
			this.reference = reference;
			this.nomAvion = nomAvion;
			this.nbplace = nbplace;
		}
		public String getReference() {
			return reference;
		}
		public void setReference(String reference) {
			this.reference = reference;
		}
		public String getNomAvion() {
			return nomAvion;
		}
		public void setNomAvion(String nomAvion) {
			this.nomAvion = nomAvion;
		}
		public Integer getNbplace() {
			return nbplace;
		}
		public void setNbplace(Integer nbplace) {
			this.nbplace = nbplace;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		@Override
		public String toString() {
			return "Avion [id=" + id + ", reference=" + reference + ", nomAvion=" + nomAvion + ", nbplace=" + nbplace
					+ "]";
		}
		   
   
	
  
}