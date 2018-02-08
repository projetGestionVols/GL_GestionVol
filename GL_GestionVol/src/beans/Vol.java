package beans;

public class Vol {
    private int    idVol;
    private String dateDepart;
    private String dateArrivee;
    private String heureDepart;
    private String heureArrivee;
    private double prix;
    private int    nbplace;
    private int    avion;
    private int    depart;
    private int    arrive;

    public Vol() {

    }

    public Vol( int idVol, String dateDepart, String dateArrivee, String heureDepart, String heureArrivee, double prix,
            int nbplace, int avion, int depart, int arrive ) {
        super();
        this.idVol = idVol;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.prix = prix;
        this.nbplace = nbplace;
        this.avion = avion;
        this.depart = depart;
        this.arrive = arrive;
    }

    public Vol( String dateDepart, String dateArrivee, String heureDepart, String heureArrivee, double prix,
            int nbplace, int avion, int depart, int arrive ) {
        super();

        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.prix = prix;
        this.nbplace = nbplace;
        this.avion = avion;
        this.depart = depart;
        this.arrive = arrive;
    }

    public int getIdVol() {
        return idVol;
    }

    public void setIdVol( int idVol ) {
        this.idVol = idVol;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart( String dateDepart ) {
        this.dateDepart = dateDepart;
    }

    public String getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee( String dateArrivee ) {
        this.dateArrivee = dateArrivee;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart( String heureDepart ) {
        this.heureDepart = heureDepart;
    }

    public String getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee( String heureArrivee ) {
        this.heureArrivee = heureArrivee;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix( double prix ) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Vol [idVol=" + idVol + ", dateDepart=" + dateDepart + ", dateArrivee=" + dateArrivee + ", heureDepart="
                + heureDepart + ", heureArrivee=" + heureArrivee + ", prix=" + prix + ", nbplace=" + nbplace
                + ", avion=" + avion + ", depart=" + depart + ", arrive=" + arrive + "]";
    }

    public int getNbplace() {
        return nbplace;
    }

    public void setNbplace( int nbplace ) {
        this.nbplace = nbplace;
    }

    public int getAvion() {
        return avion;
    }

    public void setAvion( int avion ) {
        this.avion = avion;
    }

    public int getDepart() {
        return depart;
    }

    public void setDepart( int depart ) {
        this.depart = depart;
    }

    public int getArr() {
        return arrive;
    }

    public void setArr( int arrive ) {
        this.arrive = arrive;
    }

}