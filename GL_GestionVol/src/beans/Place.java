package beans;


public class Place {

   private int id;

   private int num;

   private boolean disponible;
   

  public Place(int id, int num, boolean disponible) {
	super();
	this.id = id;
	this.num = num;
	this.disponible = disponible;
   }

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public boolean isDisponible() {
		return disponible;
	}
	
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
   
   

}