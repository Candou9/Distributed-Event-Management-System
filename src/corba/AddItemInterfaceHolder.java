package corba;

/**
* corba/AddItemInterfaceHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��corba.idl
* 2019��7��8�� ����һ ����03ʱ21��36�� EDT
*/

public final class AddItemInterfaceHolder implements org.omg.CORBA.portable.Streamable
{
  public corba.AddItemInterface value = null;

  public AddItemInterfaceHolder ()
  {
  }

  public AddItemInterfaceHolder (corba.AddItemInterface initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corba.AddItemInterfaceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corba.AddItemInterfaceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corba.AddItemInterfaceHelper.type ();
  }

}
