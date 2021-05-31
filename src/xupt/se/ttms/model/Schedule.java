package xupt.se.ttms.model;


import java.lang.Integer;

public class Schedule {
	private int sched_id=0;
	private int studio_id=0;
	private int play_id=0;
	private String time;
    private double price;
    private Integer status;
    
    public Schedule() {
    	sched_id=0;
    }
    public Schedule(int ID,int studio_Id,int play_Id,String time,double price,Integer status) {
    	sched_id=ID;
    	this.studio_id = studio_Id;
    	this.play_id = play_Id;
    	this.time = time;
    	this.price = price;
    	this.status = status;
    }
	public int getSched_id() {
		return sched_id;
	}
	public void setSched_id(int sched_id) {
		this.sched_id = sched_id;
	}
	public void setID(int ID){
		this.sched_id=ID;
	}
	
	public int getID(){
		return sched_id;
	}
	public int getStudio_id() {
		return studio_id;
	}
	public void setStudio_id(int studio_id) {
		this.studio_id = studio_id;
	}
	public int getPlay_id() {
		return play_id;
	}
	public void setPlay_id(int play_id) {
		this.play_id = play_id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
    
    
}