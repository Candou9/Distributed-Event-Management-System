package corba;


/**
* corba/AddItemInterfaceOperations.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从corba.idl
* 2019年7月8日 星期一 下午03时21分36秒 EDT
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
