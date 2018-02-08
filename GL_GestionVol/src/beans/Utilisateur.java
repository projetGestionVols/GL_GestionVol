package beans;



public class Utilisateur {
  
   private int idUser;
   private String nomUser;
   private String prenomUser;
   private String adresseUser;
   private String eMail;
   private int numTele;
   private String login;
   private String motPasse;
   
   public Utilisateur() {
     
   }
  
   public Utilisateur(int idUser, String nomUser, String prenomUser, String adresseUser, String eMail, int numTele,
		String login, String motPasse) {
	super();
	this.idUser = idUser;
	this.nomUser = nomUser;
	this.prenomUser = prenomUser;
	this.adresseUser = adresseUser;
	this.eMail = eMail;
	this.numTele = numTele;
	this.login = login;
	this.motPasse = motPasse;
}


public int getIdUser() {
      return idUser;
   }
   

   public void setIdUser(int newIdUser) {
      idUser = newIdUser;
   }
   
   
   public String getNomUser() {
      return nomUser;
   }
   
  
   public void setNomUser(String newNomUser) {
      nomUser = newNomUser;
   }
   
  
   public String getPrenomUser() {
      return prenomUser;
   }
   
   
   public void setPrenomUser(String newPrenomUser) {
      prenomUser = newPrenomUser;
   }
   
  
   public String getAdresseUser() {
      return adresseUser;
   }
   
  
   public void setAdresseUser(String newAdresseUser) {
      adresseUser = newAdresseUser;
   }
   
  
   public String getEMail() {
      return eMail;
   }
   
  
  
   public void setEMail(String newEMail) {
      eMail = newEMail;
   }
   
  
   public int getNumTele() {
      return numTele;
   }
   
   /** @param newNumTele
    * @pdOid bbdb52d5-4af2-4ebf-a242-1206aa315f4e */
   public void setNumTele(int newNumTele) {
      numTele = newNumTele;
   }
   
   /** @pdOid 040b01c0-46ab-4925-ba9f-12b0a6897444 */
   public String getLogin() {
      return login;
   }
   
   /** @param newLogin
    * @pdOid 2f100330-659c-4422-9e15-3782a686d892 */
   public void setLogin(String newLogin) {
      login = newLogin;
   }
   
   /** @pdOid 2a97ff38-ff85-459e-9e36-b963a87c22a4 */
   public String getMotPasse() {
      return motPasse;
   }
   
   /** @param newMotPasse
    * @pdOid 0187c991-86c4-463d-917d-9303d0999f55 */
   public void setMotPasse(String newMotPasse) {
      motPasse = newMotPasse;
   }

}