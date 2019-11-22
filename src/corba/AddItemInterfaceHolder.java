package corba;

/**
* corba/AddItemInterfaceHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从corba.idl
* 2019年7月8日 星期一 下午03时21分36秒 EDT
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
