package Map;

public class BookedEvents {

	private Integer numberOfDays;
    private String eventID;
    private String customerID;
       
    public BookedEvents(String customerID, String eventID, Integer numberOfDays ) {
        this.numberOfDays = numberOfDays;
        this.eventID = eventID;
        this.customerID = customerID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setcustomerID(String customerID) {
        this.customerID = customerID;
    }
    
    public String getEventID() {
        return eventID;
    }

    public void setEventId(String eventId) {
        this.eventID = eventId;
    }

   
    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public String toString() {
        return "{" +
                "eventID='" + eventID + '\'' +
                ", numberOfDays='" + numberOfDays + '\'' +
                '}';
    }
}
