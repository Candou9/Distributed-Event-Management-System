package Map;

public class Event {
      
	private String eventType;
	private Integer eventQty;
	private String eventID;
		
	public Event(String eventType, String eventID, Integer eventQty ) {
	    this.eventType = eventType;    
		this.eventQty = eventQty;
	    this.eventID = eventID;
    }
	
	public String geteventType() {
	    return eventType;
	}

	public void seteventType(String eventType) {
	    this.eventType = eventType;
    }
	
	public String geteventID() {
	    return eventID;
	}

	public void seteventID(String eventID) {
	    this.eventID = eventID;
    }
	
	
	public Integer geteventQty() {
        return eventQty;
    }

    public void seteventQty(Integer eventQty) {
        this.eventQty = eventQty;
    }
   
    @Override
    public String toString() {
        return "Event{" +
                "eventType='" + eventType + '\'' +
                ", eventID='" + eventID + '\'' +
                ", eventQty='" + eventQty + '\'' +
                '}';
    }
}
