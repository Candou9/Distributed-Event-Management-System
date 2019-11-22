package corba;


/**
* corba/AddItemInterfacePOA.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从corba.idl
* 2019年7月8日 星期一 下午03时21分36秒 EDT
*/

public abstract class AddItemInterfacePOA extends org.omg.PortableServer.Servant
 implements corba.AddItemInterfaceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("shutdown", new java.lang.Integer (0));
    _methods.put ("swapEvent", new java.lang.Integer (1));
    _methods.put ("addEvent", new java.lang.Integer (2));
    _methods.put ("removeEvent", new java.lang.Integer (3));
    _methods.put ("listEventAvailability", new java.lang.Integer (4));
    _methods.put ("bookEvent", new java.lang.Integer (5));
    _methods.put ("cancelEvent", new java.lang.Integer (6));
    _methods.put ("getBookingSchedule", new java.lang.Integer (7));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // corba/AddItemInterface/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       case 1:  // corba/AddItemInterface/swapEvent
       {
         String customerID = in.read_string ();
         String newEventID = in.read_string ();
         String newEventType = in.read_string ();
         String oldEventID = in.read_string ();
         String oldEventType = in.read_string ();
         int $result = (int)0;
         $result = this.swapEvent (customerID, newEventID, newEventType, oldEventID, oldEventType);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 2:  // corba/AddItemInterface/addEvent
       {
         String managerID = in.read_string ();
         String eventID = in.read_string ();
         String eventType = in.read_string ();
         int eventQty = in.read_long ();
         boolean $result = false;
         $result = this.addEvent (managerID, eventID, eventType, eventQty);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 3:  // corba/AddItemInterface/removeEvent
       {
         String managerID = in.read_string ();
         String eventID = in.read_string ();
         String eventType = in.read_string ();
         boolean $result = false;
         $result = this.removeEvent (managerID, eventID, eventType);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 4:  // corba/AddItemInterface/listEventAvailability
       {
         String managerID = in.read_string ();
         String eventType = in.read_string ();
         String $result = null;
         $result = this.listEventAvailability (managerID, eventType);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 5:  // corba/AddItemInterface/bookEvent
       {
         String customerID = in.read_string ();
         String eventID = in.read_string ();
         String eventType = in.read_string ();
         int numberOfDays = in.read_long ();
         boolean $result = false;
         $result = this.bookEvent (customerID, eventID, eventType, numberOfDays);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 6:  // corba/AddItemInterface/cancelEvent
       {
         String customerID = in.read_string ();
         String eventType = in.read_string ();
         String eventID = in.read_string ();
         boolean $result = false;
         $result = this.cancelEvent (customerID, eventType, eventID);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 7:  // corba/AddItemInterface/getBookingSchedule
       {
         String customerID = in.read_string ();
         String $result = null;
         $result = this.getBookingSchedule (customerID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:corba/AddItemInterface:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public AddItemInterface _this() 
  {
    return AddItemInterfaceHelper.narrow(
    super._this_object());
  }

  public AddItemInterface _this(org.omg.CORBA.ORB orb) 
  {
    return AddItemInterfaceHelper.narrow(
    super._this_object(orb));
  }


} // class AddItemInterfacePOA
