package corba;


/**
* corba/AddItemInterfaceHelper.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��corba.idl
* 2019��7��8�� ����һ ����03ʱ21��36�� EDT
*/

abstract public class AddItemInterfaceHelper
{
  private static String  _id = "IDL:corba/AddItemInterface:1.0";

  public static void insert (org.omg.CORBA.Any a, corba.AddItemInterface that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static corba.AddItemInterface extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (corba.AddItemInterfaceHelper.id (), "AddItemInterface");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static corba.AddItemInterface read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_AddItemInterfaceStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, corba.AddItemInterface value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static corba.AddItemInterface narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof corba.AddItemInterface)
      return (corba.AddItemInterface)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      corba._AddItemInterfaceStub stub = new corba._AddItemInterfaceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static corba.AddItemInterface unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof corba.AddItemInterface)
      return (corba.AddItemInterface)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      corba._AddItemInterfaceStub stub = new corba._AddItemInterfaceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
