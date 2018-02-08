package beans;


public class Reservation {
   private Integer attributRe;
   private Integer nbPlace;
   private int vol;
   
   public Reservation()
   {
	   
   }
   public Reservation( int vol)
   {
		this.vol = vol;   
   }
   public Reservation(Integer attributRe, Integer nbPlace, int vol) {
	super();
	this.attributRe = attributRe;
	this.nbPlace = nbPlace;
	this.vol = vol;
   }

	public Integer getAttributRe() {
		return attributRe;
	}
	
	public void setAttributRe(Integer attributRe) {
		this.attributRe = attributRe;
	}
	
	public Integer getNbPlace() {
		return nbPlace;
	}
	
	public void setNbPlace(Integer nbPlace) {
		this.nbPlace = nbPlace;
	}
	
	public int getVol() {
		return vol;
	}
	
	public void setVol(int vol) {
		this.vol = vol;
	}
   
   
   



   
}