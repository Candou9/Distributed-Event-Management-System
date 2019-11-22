package ServerInterface;

import java.rmi.*;
import java.util.LinkedList;

public interface AddItemInterface extends Remote{

	public boolean addEvent( String managerID,String eventID, String eventType, int eventQty) throws RemoteException;
	public boolean removeEvent(String managerID,String eventID, String eventType) throws RemoteException;
	public String listEventAvailability(String managerID,String eventType) throws RemoteException;
	public boolean bookEvent(String customerID, String eventID, String eventType, int numberOfDays) throws RemoteException;
	public LinkedList<String>  getBookingSchedule(String customerID) throws RemoteException;
	public boolean cancelEvent(String customerID, String eventType, String eventID) throws RemoteException;
	
}
