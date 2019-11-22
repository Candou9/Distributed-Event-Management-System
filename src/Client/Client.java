package Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import corba.AddItemInterface;
import corba.AddItemInterfaceHelper;
import ImplementRemoteInterface.MontrealClass;

public class Client {

	public static void main(String args[])
	{
		try 
		{
			ORB orb = ORB.init(args, null);
			//-ORBInitialPort 1050 -ORBInitialHost localhost
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			
					
		} catch (Exception e) {
			System.out.println("Hello Client exception: " + e);
			e.printStackTrace();
		}
		startSystem();
		
	}
	// Initiating client site program 

	private static void startSystem() {
		System.out.println("Enter your username: ");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine().toUpperCase();
		System.out.println("You are loging as " + username);
		if(username.length()!=8) {
			System.out.println("Wrong ID");
			startSystem();
		}
		String accessParameter = username.substring(3, Math.min(username.length(), 4));
		System.out.println("You are loging as " + accessParameter);
		if(accessParameter.equals("C") || accessParameter.equals("c") ) {
			try {
				user(username);
				startSystem();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(accessParameter.equals("M") || accessParameter.equals("m")) {
			try {
				manager(username);
//				startSystem();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("This user is not authorized");
			startSystem();
		}
	}


	
	
	
	
	private static void manager(String username) throws Exception
	{
		int serverPort = decideServerport(username);
		if(serverPort==1) {
			return;
		}
		Registry registry = LocateRegistry.getRegistry(serverPort);
		System.out.println("1. Add Event \n 2.Remove Event \n 3. List vents Availability  \n 4. Logout");
		System.out.println("Select the option you want to do: ");
		Scanner scanner = new Scanner(System.in);
		String menuSelection = scanner.nextLine();
		if(menuSelection.equals("1")) {
			String eventID = setEventId(username);
			String eventType = setEventType(username);
			int eventQty = setEventQty(username);
			AddItemInterface obj = (AddItemInterface) registry.lookup("AddItem");
			boolean n = obj.addEvent(username, eventID, eventType, eventQty);
			System.out.println("Event Added : " + n);
			manager(username);
		}
		else if(menuSelection.equals("2")) {
			String eventID = setEventId(username);
			String eventType = setEventType(username);
			AddItemInterface obj = (AddItemInterface) registry.lookup("AddItem");
			boolean n = obj.removeEvent(username, eventID, eventType);
			System.out.println("Event Removed : " + n);
			if(!n) {
				System.out.println("Check Quantity");
			}
			manager(username);
		}
		else if(menuSelection.equals("3")) {
			String eventType = setEventType(username);
			System.out.println("EventList is given below. ");
			AddItemInterface obj = (AddItemInterface) registry.lookup("AddItem");
			System.out.println(obj.listEventAvailability(username, eventType));
			String exit = scanner.nextLine();
			if(exit.equals("E") || exit.equals("e")) {
				manager(username);
			}else {
				manager(username);
			}
		}
		else if(menuSelection.equals("4")) {
			startSystem();
			}
		
		else {
			manager(username);
		}

	}

		
		
	
		
		private static void user(String username) throws Exception
		{
			int serverPort = decideServerport(username);
			if(serverPort==1) {
				return;
			}
			Registry registry = LocateRegistry.getRegistry(serverPort);
			System.out.println("1. Book Event \n 2.Get booking schedule \n 3. Cancel event \n 4. Logout");
			System.out.println("Select the option you want to do: ");
			Scanner scanner = new Scanner(System.in);
			String menuSelection = scanner.nextLine();

			if(menuSelection.equals("1")) {
				String eventID = setEventId(username);
				int numbersOfDay = setNumbersOfDay(username);
				String eventType = setEventType(username);
				AddItemInterface obj = (AddItemInterface) registry.lookup("AddItem");
				boolean n = obj.bookEvent(username, eventID, eventType, numbersOfDay);
				System.out.println(" Booked Event : " + n);
				if(!n) {
					System.out.println("Event is not available now.");
						}
					else {
						user(username);
					}
			user(username);
				}

			
            if(menuSelection.equals("2")){ 
            	AddItemInterface obj = (AddItemInterface) registry.lookup("AddItem");
            	Scanner scanner1 = new Scanner(System.in);
				String customerID = scanner1.nextLine().toUpperCase();
				System.out.println("Enter Customer ID: "+ customerID);
				System.out.println(obj.getBookingSchedule(customerID));			
				}else {
					user(username);
				}
			
		    if(menuSelection.equals("3")) {
				String eventID = setEventId(username);
				String eventType = setEventType(username);
				AddItemInterface obj = (AddItemInterface) registry.lookup("AddItem");
				boolean n = obj.cancelEvent(username, eventType, eventID);
				System.out.println("Event Cancel : " + n);
				user(username);
			}
		    if (menuSelection.equals("4")) {
		    	startSystem();
				}else {
					user(username);
						}
		}


	

		private static int decideServerport(String username) {
			int serverPort=1;
			String serverDirection = username.substring(0, Math.min(username.length(), 3));
			if(serverDirection.equals("MTL") || serverDirection.equals("mtl")) {
				serverPort = 2964;
			}else if(serverDirection.equals("OTW") || serverDirection.equals("otw")) {
				serverPort = 2965;
			}else if(serverDirection.equals("TOR") || serverDirection.equals("tor")) {
				serverPort = 2966;
			}else {
				System.out.println("This is an invalid request. Please check your username");
				startSystem();
			}
			return serverPort;
		}
		
		
		private static String setEventId(String username) {
			String cityName = username.substring(0, Math.min(username.length(), 3));
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter Event Id: ");
			String eventID = scanner.nextLine().toUpperCase();
			String eventIDPrefix = eventID.substring(0, Math.min(eventID.length(), 3));
			if(eventID.length()!=10 && cityName !=eventIDPrefix) {
				System.out.println("Enter a valid Event ID: ");
				eventID = setEventId(username);
			}
			return  eventID;
		}
		
		
		private static String setEventType(String username) {
		
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter Event Type: ");
			String eventType = scanner.nextLine().toUpperCase();
			if(eventType.equals("CONFERENCES") ){
				return eventType;
			}
			if(eventType.equals("SEMINARS")){
				return eventType;
			}
		    if(eventType.equals("TRADE SHOWS")){
		    	return  eventType;
			}
		    return  eventType;
}


		
		private static int setNumbersOfDay(String username) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter Number Of Days: ");
				int numberOfDays;
				if(scanner.hasNextInt()){
					numberOfDays = scanner.nextInt();
				}else{
					System.out.println("Enter a valid Number: ");
					numberOfDays = setNumbersOfDay(username);
				}
				return  numberOfDays;
			}
		
		
		
		private static int setEventQty(String username) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter Event Quantity: ");
			int eventQty;
			if(scanner.hasNextInt()){
				eventQty = scanner.nextInt();
				if(eventQty<0) {
					System.out.println("Enter a valid Number: ");
					eventQty = setEventQty(username);
				}
			}else{
				System.out.println("Enter a valid Item Id: ");
				eventQty = setEventQty(username);
			}
			return  eventQty;
		}
	}
	

