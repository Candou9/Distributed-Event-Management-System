package corba;


/**
* corba/_AddItemInterfaceStub.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��corba.idl
* 2019��7��8�� ����һ ����03ʱ21��36�� EDT
*/

public class _AddItemInterfaceStub extends org.omg.CORBA.portable.ObjectImpl implements corba.AddItemInterface
{

  public void shutdown ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("shutdown", false);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                shutdown (        );
            } finally {
                _releaseReply ($in);
            }
  } // shutdown

  public int swapEvent (String customerID, String newEventID, String newEventType, String oldEventID, String oldEventType)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("swapEvent", true);
                $out.write_string (customerID);
                $out.write_string (newEventID);
                $out.write_string (newEventType);
                $out.write_string (oldEventID);
                $out.write_string (oldEventType);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return swapEvent (customerID, newEventID, newEventType, oldEventID, oldEventType        );
            } finally {
                _releaseReply ($in);
            }
  } // swapEvent

  public boolean addEvent (String managerID, String eventID, String eventType, int eventQty)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("addEvent", true);
                $out.write_string (managerID);
                $out.write_string (eventID);
                $out.write_string (eventType);
                $out.write_long (eventQty);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return addEvent (managerID, eventID, eventType, eventQty        );
            } finally {
                _releaseReply ($in);
            }
  } // addEvent

  public boolean removeEvent (String managerID, String eventID, String eventType)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("removeEvent", true);
                $out.write_string (managerID);
                $out.write_string (eventID);
                $out.write_string (eventType);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return removeEvent (managerID, eventID, eventType        );
            } finally {
                _releaseReply ($in);
            }
  } // removeEvent

  public String listEventAvailability (String managerID, String eventType)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("listEventAvailability", true);
                $out.write_string (managerID);
                $out.write_string (eventType);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return listEventAvailability (managerID, eventType        );
            } finally {
                _releaseReply ($in);
            }
  } // listEventAvailability

  public boolean bookEvent (String customerID, String eventID, String eventType, int numberOfDays)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("bookEvent", true);
                $out.write_string (customerID);
                $out.write_string (eventID);
                $out.write_string (eventType);
                $out.write_long (numberOfDays);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return bookEvent (customerID, eventID, eventType, numberOfDays        );
            } finally {
                _releaseReply ($in);
            }
  } // bookEvent

  public boolean cancelEvent (String customerID, String eventType, String eventID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("cancelEvent", true);
                $out.write_string (customerID);
                $out.write_string (eventType);
                $out.write_string (eventID);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return cancelEvent (customerID, eventType, eventID        );
            } finally {
                _releaseReply ($in);
            }
  } // cancelEvent

  public String getBookingSchedule (String customerID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getBookingSchedule", true);
                $out.write_string (customerID);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getBookingSchedule (customerID        );
            } finally {
                _releaseReply ($in);
            }
  } // getBookingSchedule

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:corba/AddItemInterface:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _AddItemInterfaceStub
