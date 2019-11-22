package corba;


/**
* corba/AddItemInterfaceOperations.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��corba.idl
* 2019��7��8�� ����һ ����03ʱ21��36�� EDT
*/

public interface AddItemInterfaceOperations 
{
  void shutdown ();
  int swapEvent (String customerID, String newEventID, String newEventType, String oldEventID, String oldEventType);
  boolean addEvent (String managerID, String eventID, String eventType, int eventQty);
  boolean removeEvent (String managerID, String eventID, String eventType);
  String listEventAvailability (String managerID, String eventType);
  boolean bookEvent (String customerID, String eventID, String eventType, int numberOfDays);
  boolean cancelEvent (String customerID, String eventType, String eventID);
  String getBookingSchedule (String customerID);
} // interface AddItemInterfaceOperations
