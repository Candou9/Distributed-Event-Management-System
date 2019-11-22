package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import ImplementRemoteInterface.MontrealClass;
import ImplementRemoteInterface.OttawaClass;
import corba.AddItemInterface;
import corba.AddItemInterfaceHelper;

public class OttawaServer {
	
	public static void main(String args[]) throws Exception
	{
		

		try {
			// create and initialize the ORB //
			ORB orb = ORB.init(args, null);
			
			// get reference to rootpoa &amp; activate
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			OttawaClass otwobj = new OttawaClass();
			otwobj.setORB(orb);

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(otwobj);
			
			
			// and cast the reference to a CORBA reference
			AddItemInterface href = AddItemInterfaceHelper.narrow(ref);

			// get the root naming context
			// NameService invokes the transient name service
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			
			// Use NamingContextExt, which is part of the
			// Interoperable Naming Service (INS) specification.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// bind the Object Reference in Naming
			NameComponent path[] = ncRef.to_name("abc");
			ncRef.rebind(path, href);

			System.out.println("Ottawa Server ready and waiting ...");

			// wait for invocations from clients
			for (;;) {
				orb.run();
			}
		}

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		
		System.out.println("Ottawa Server is Started");
	}

	private static void receive(OttawaClass obj) {
		// TODO Auto-generated method stub
		DatagramSocket aSocket = null;
		String sendingResult = "";
		try {
			aSocket = new DatagramSocket(7777);
			byte[] buffer = new byte[1024];
			System.out.println("Ottawa UDP Server 8888 Started............");
			while (true) {
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);
				String sentence = new String( request.getData(), 0,
						request.getLength() );
				String[] parts = sentence.split(";");
				String function = parts[0]; 
				String userID = parts[1]; 
				String eventType = parts[2]; 
				String eventID = parts[3]; 
				int numberOfDays = Integer.parseInt(parts[4]); 
				if(function.equals("list")) {
					String result = obj.listEventAvailability(userID, eventType);
					sendingResult = result;
					sendingResult= sendingResult+";";
				}else if(function.equals("book")) {
					boolean result = obj.bookEvent(userID, eventID, eventType, numberOfDays);
					sendingResult = Boolean.toString(result);
					sendingResult= sendingResult+";";
				}else if(function.equals("cancel")) {
					boolean result = obj.cancelEvent(userID, eventType, eventID);
					sendingResult = Boolean.toString(result);
					sendingResult= sendingResult+";";
				
				}
				byte[] sendData = sendingResult.getBytes();
				DatagramPacket reply = new DatagramPacket(sendData, sendingResult.length(), request.getAddress(),
						request.getPort());
				aSocket.send(reply);
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
	}
}
