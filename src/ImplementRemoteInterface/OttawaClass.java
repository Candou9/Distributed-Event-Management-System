package ImplementRemoteInterface;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.omg.CORBA.ORB;

import Map.BookedEvents;
import Map.Event;


import corba.AddItemInterfacePOA;;

public class OttawaClass extends AddItemInterfacePOA{
	
	private ORB orb;
	public void setORB(ORB orb_val) {
		orb = orb_val;
	}
	
	private HashMap<String, HashMap<String, Event>> cityHashmap = new HashMap<String, HashMap<String, Event>>();
	private HashMap<String, List<BookedEvents>> BookedEvents;
	private HashMap<String,LinkedList<String>> userinfor=new HashMap<String,LinkedList<String>>();
	private String cityName = "OTW";
	private int montrealServerPort = 8888;
	private int ottawaServerPort = 7777;
	private int torontoServerPort = 6666;
	
	
	public OttawaClass()throws Exception{
		super(); 
		
		HashMap<String, Event> Conferences = new HashMap<String, Event>();
		HashMap<String, Event> Seminars = new HashMap<String, Event>();
		HashMap<String, Event> TradeShows = new HashMap<String, Event>();
				
							
		Conferences.put("OTWA100519", new Event("Conferences", "OTWA100519", 20 ));
		Conferences.put("OTWE100519", new Event("Conferences", "OTWE100519", 20 ));
		Conferences.put("OTWM100519", new Event("Conferences", "OTWM100519", 20 ));
	
		Seminars.put("OTWA100619", new Event("Seminars", "OTWA100619", 20 ));
		Seminars.put("OTWE100619", new Event("Seminars", "OTWE100619", 20 ));
		Seminars.put("OTWM100719", new Event("Seminars", "OTWM100619", 20 ));
			
		TradeShows.put("OTWA100719", new Event("TradeShows", "OTWA100719", 20 ));
		TradeShows.put("OTWE100719", new Event("TradeShows", "OTWE100719", 20 ));
		TradeShows.put("OTWM100719", new Event("TradeShows", "OTWM100719", 20 ));
		
		cityHashmap.put("CONFERENCES",Conferences);
		cityHashmap.put("SEMINARS",Seminars);
		cityHashmap.put("TRADESHOWS",TradeShows);
			
	/*    for (HashMap<String, Event> inner : array) {
	            Set<String> set = inner.keySet();
	            for (String key : set) {
	                Event value = inner.get(key);
	                System.out.println(key + "---" + value); */
	       }
		
   	public boolean addEvent( String managerID, String eventID, String eventType, int eventQty ){
		String eventIDPrefix = eventID.substring(0, Math.min(eventID.length(), 3)).toLowerCase();
		String managerPrefix = managerID.substring(0, Math.min(managerID.length(), 3)).toLowerCase();
		String userType = managerID.substring(3, Math.min(managerID.length(), 4));
		if(eventIDPrefix.equals(managerPrefix) && (userType.equals("M") || userType.equals("m"))){
			if (cityHashmap.containsKey(eventType)) {
				HashMap<String, Event> array = new HashMap<String, Event>();
				array = cityHashmap.get(eventType);
				if (array.containsKey(eventID)) {
					System.out.println("Event already exists for "+eventType+" event type.");
					System.out.println("2");
					return true;
				} else {
          		synchronized(this) {
					array.put(eventID, new Event(eventType, eventID, eventQty));
					String action = "ADD EVENT "+ eventID;
					try {
						logCreate(managerID, action, "EVENT ADDED: true");
						serverLogCreate(managerID,action,"EVENT ADDED: true", "Success",  "USER ID: "+managerID+"/ Event Type: "+eventType+"/ Event ID: "+  eventID+"/ eventQty: "+eventQty);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					                        }
						    return true;
					    
			                     }
		                }
			}else{
				//event type doesn't exists
				System.out.println("Event already exists for "+eventType+" event type.");
									}
		}
			return true;      				                         	      	      
	} 
		                         		
					
 
 
@Override
  public boolean removeEvent(String managerID, String eventID, String eventType) {
	    String eventIDPrefix = eventID.substring(0, Math.min(eventID.length(), 3)).toLowerCase();
		String managerPrefix = managerID.substring(0, Math.min(managerID.length(), 3)).toLowerCase();
		String userType = managerID.substring(3, Math.min(managerID.length(), 4));
		if(eventIDPrefix.equals(managerPrefix) && (userType.equals("M") || userType.equals("m"))){
			if (cityHashmap.containsKey(eventType)) {
				HashMap<String, Event> array = new HashMap<String, Event>();
				array = cityHashmap.get(eventType);
				if (array.containsKey(eventID)) {
					int eventLeft = array.get(eventID).geteventQty();
					if(eventLeft>=0){
						synchronized(this) {
						array.get(eventID).seteventQty(eventLeft);
						String action = "REMOVE EVENT "+ eventID;
						array.remove(eventID);
						try {
							logCreate(managerID, action, "EVENT REMOVED: true");
							serverLogCreate(managerID,action,"EVENT REMOVED: true", "Success",  "USER ID: "+managerID+"/ Event ID: "+  eventID);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return true;
					}
					}else{
						String action = "REMOVE EVENT "+ eventID;
						try {
							logCreate(managerID, action, "ITEM REMOVED: false");

							serverLogCreate(managerID,action,"ITEM REMOVED: false", "Failed",  "USER ID: "+managerID+"/ EVENT ID: "+  eventID);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return false;
					}
				}
			}
		}
		String action = "REMOVE EVENT "+ eventID;
		try {
			logCreate(managerID, action, "ITEM REMOVED: false");

			serverLogCreate(managerID,action,"ITEM REMOVED: false", "Failed",  "USER ID: "+managerID+"/ EVENT ID: "+  eventID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		 }
  

  @Override
  public String listEventAvailability(String managerID, String eventType){
	 			String action = "List of Items ";
	 			String listEvents = "No events are available";
	 			HashMap<String, Event> array = new HashMap<String, Event>();
				array = cityHashmap.get(eventType);
				
			try {
				logCreate(managerID, action, array.toString());

				serverLogCreate(managerID,action,array.toString(), "Success",  "USER ID: "+managerID);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listEvents = array.toString();
			String managerPrefix = managerID.substring(0, Math.min(managerID.length(), 3)).toLowerCase();
			if(managerPrefix.equals(cityName)){
				    if(cityName.equals("mtl")) {
				   		String resultotw = sendMessage(ottawaServerPort,"list", managerID, eventType,  null,  0);
						String resulttor = sendMessage(torontoServerPort,"list", managerID, eventType,  null,  0);
						listEvents = listEvents+resultotw+resulttor;
					}else if(cityName.equals("tor")) {
						String resultotw = sendMessage(ottawaServerPort,"list", managerID, eventType,  null,  0);
						String resultmtl = sendMessage(montrealServerPort,"list", managerID, eventType,  null,  0);
						listEvents = listEvents+resultotw+resultmtl;
					}else if(cityName.equals("otw")) {
						String resultmtl = sendMessage(montrealServerPort,"list", managerID, eventType,  null,  0);
						String resulttor = sendMessage(torontoServerPort,"list", managerID, eventType,  null,  0);
						listEvents = listEvents+resultmtl+resulttor;
				}
				
				
			}
			return listEvents;
					}
  

  @Override
  public boolean bookEvent(String customerID, String eventID, String eventType, int numberOfDays){
	    String eventIDPrefix = eventID.substring(0, Math.min(eventID.length(), 3)).toUpperCase();
		String userType = customerID.substring(3, Math.min(customerID.length(), 4));
		System.out.println("eventID"+eventID);
		System.out.println(cityName);
		System.out.println(eventIDPrefix);
		if(cityName.equals(eventIDPrefix) && (userType.equals("C") || userType.equals("c"))){
			if (cityHashmap.containsKey(eventType)) {
				HashMap<String, Event> array = new HashMap<String, Event>();
				array = cityHashmap.get(eventType);
				if (array.containsKey(eventID)) {
					int eventLeft = array.get(eventID).geteventQty();
					if(eventLeft>=0){
						synchronized(this) {
						String action = "Booked an Event: "+eventID;
						int eventLeft2 = array.get(eventID).geteventQty() -1;
						array.get(eventID).seteventQty(eventLeft2);
						insertEvent(customerID,eventID,eventType);
						try {
							logCreate(customerID, action, " true");
							serverLogCreate(customerID,action,"true", "Success",  "USER ID: "+customerID+"/ Event ID: "+  eventID+"/Number OF Day:"+numberOfDays);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return true;
					}
					}else{
						System.out.println("1");
						String action = "Booked an Event: "+eventID;
						try {
							logCreate(customerID, action, " true");
							serverLogCreate(customerID,action,"true", "Success",  "USER ID: "+customerID+"/ Event ID: "+  eventID+"/Number OF Day:"+numberOfDays);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return false;
					}
				}else{
					System.out.println("2");
					String action = "Booked an Event: "+eventID;
					try {
						logCreate(customerID, action, " true");
						serverLogCreate(customerID,action,"true", "Success",  "USER ID: "+customerID+"/ Event ID: "+  eventID+"/Number OF Day:"+numberOfDays);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return false;
				}
			}else{
				System.out.println("3");
				String action = "Booked an Event: "+eventID;
				try {
					logCreate(customerID, action, " true");
					serverLogCreate(customerID,action,"true", "Success",  "USER ID: "+customerID+"/ Event ID: "+  eventID+"/Number OF Day:"+numberOfDays);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
				}
		}else{
			System.out.println("4");
			String action = "Booked an Event: "+eventID;
			try {
				logCreate(customerID, action, " true");
				serverLogCreate(customerID,action,"true", "Success",  "USER ID: "+customerID+"/ Event ID: "+  eventID+"/Number OF Day:"+numberOfDays);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
					}
					}

  
  

	public void insertEvent(String customerID,String eventID,String  eventType) {
		if(userinfor.containsKey(customerID)) {
			userinfor.get(customerID).add(eventType+" "+eventID);
			
		}else {
			LinkedList<String> usTemp= new LinkedList<String>();
			usTemp.add(eventType+" "+eventID);		
			userinfor.put(customerID, usTemp);
		}
	
}




@Override
public boolean cancelEvent(String customerID, String eventType, String eventID){
	    String eventIDPrefix = eventID.substring(0, Math.min(eventID.length(), 3)).toLowerCase();
		String userType = customerID.substring(3, Math.min(customerID.length(), 4));
		if(eventIDPrefix.equals(cityName)&&(userType.equals("C")||userType.equals("c"))){
			if (cityHashmap.containsKey(eventType)) {
				HashMap<String, Event> array = new HashMap<String, Event>();
				array = cityHashmap.get(eventType);
				if (array.containsKey(eventID)) {
					int eventLeft = array.get(eventID).geteventQty()+1;
					array.get(eventID).seteventQty(eventLeft);
					userinfor.get(customerID).remove(eventType+" "+eventID);
					String action = "Cancel an event:" + eventID;
					try{
						logCreate(customerID, action,"true");
						serverLogCreate(customerID,action,"true","Success","USER ID:"+customerID+"/Event ID:"+eventID);
					}catch (IOException e){
						e.printStackTrace();
					                      }
					}
				return true;
		}
		}		else if((eventIDPrefix.equals("C") || eventIDPrefix.equals("c"))){

			if(eventIDPrefix.equals("mtl")) {
				String result = sendMessage(montrealServerPort,"cancel", customerID, eventType,  eventID,  0);
				System.out.println(result);
				return Boolean.parseBoolean(result);
			}else if(eventIDPrefix.equals("otw")) {
				String result = sendMessage(ottawaServerPort,"cancel", customerID, eventType,  eventID, 0);
				return Boolean.parseBoolean(result);
			}else if(eventIDPrefix.equals("tor")) {
				String result = sendMessage(torontoServerPort,"cancel",customerID, eventType,  eventID,  0);
				return Boolean.parseBoolean(result);
}
		return false;
		}
		return false;
}


private void serverLogCreate(String userID, String acion, String response,String requestResult, String peram)throws IOException {
	String userPrefix = userID.substring(0, Math.min(userID.length(), 3)).toLowerCase();
	final String dir = System.getProperty("user.dir");
	String fileName = dir;
	if(userPrefix.equals("mtl")) {
		fileName = dir+"\\src\\Log\\Client\\Montreal\\"+userID+".txt";
	}else if(userPrefix.equals("tor")) 
	{
		fileName = dir+"\\src\\Log\\Client\\Toronto\\"+userID+".txt";
	}else if(userPrefix.equals("Otw")) 
	{
		fileName = dir+"\\src\\Log\\Client\\Ottawa\\"+userID+".txt";
	}

	Date date = new Date();

	String strDateFormat = "yyyy-MM-dd hh:mm:ss a";

	DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

	String formattedDate= dateFormat.format(date);


	FileWriter fileWriter = new FileWriter(fileName,true);
	PrintWriter printWriter = new PrintWriter(fileWriter);
	printWriter.println("DATE: "+formattedDate+"Action: "+acion+" | Parameters: "+ peram +" | Action Status: "+requestResult+" | Resonse: "+ response);

	printWriter.close();
	
}

private void logCreate(String userID, String action, String response)throws IOException {
    String userPrefix = userID.substring(0, Math.min(userID.length(), 3)).toLowerCase();
	final String dir = System.getProperty("user.dir");
	String fileName = dir;
	if(userPrefix.equals("mtl")) {
		fileName = dir+"\\src\\Log\\Client\\Montreal\\"+userID+".txt";
	}else if(userPrefix.equals("tor")) 
	{
		fileName = dir+"\\src\\Log\\Client\\Toronto\\"+userID+".txt";
	}else if(userPrefix.equals("Otw")) 
	{
		fileName = dir+"\\src\\Log\\Client\\Ottawa\\"+userID+".txt";
	}

	FileWriter fileWriter = new FileWriter(fileName,true);
	PrintWriter printWriter = new PrintWriter(fileWriter);
	printWriter.println("Action: "+action+" | Response: "+ response);

	printWriter.close();
	
}


private String sendMessage(int serverPort, String function, String userID, String eventType,String eventID,int numberOfDays){
	DatagramSocket aSocket = null;
	String result = "";
	String dataFromClient = function+";"+userID+";"+eventType+";"+eventID+";"+numberOfDays;
	try{
		aSocket = new DatagramSocket();
		byte[]message = dataFromClient.getBytes();
		InetAddress aHost = InetAddress.getByName("localhost");
		DatagramPacket request = new DatagramPacket(message, dataFromClient.length(), aHost, serverPort);
		aSocket.send(request);
		
		byte[]buffer = new byte[1024];
		DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
		
		aSocket.receive(reply);
		result = new String(reply.getData());
		String[]parts = result.split(";");
		result = parts[0];
	}catch (SocketException e) {
		System.out.println("Socket: " + e.getMessage());
	} catch (IOException e) {
		e.printStackTrace();
		System.out.println("IO: " + e.getMessage());
	} finally {
		if (aSocket != null)
			aSocket.close();
	}
	return result;

}

@Override
public void shutdown() {		
	orb.shutdown(false);
}



@Override
public int swapEvent(String customerID, String newEventID, String newEventType, String oldEventID,
		String oldEventType) {
	// TODO Auto-generated method stub
			int res=-1;
			LinkedList<String> scheduleList = userinfor.get(customerID);
				
			for(String s:scheduleList) {
				if(s.equals(oldEventID))res=1;
			}
			
			if(res==1) {
				int numberOfDays = 0;
				if(this.bookEvent(customerID, newEventID, newEventType, numberOfDays)==true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.cancelEvent(oldEventID, oldEventType, customerID);
					return 1;
				}
				else return 0;
			}else return -1;
			
		}

@Override
public String getBookingSchedule(String customerID) {
	
	String listEvents = "No events are available";
	LinkedList<String> array = userinfor.get(customerID);
	listEvents = array.toString();
	if(userinfor.containsKey(customerID)) {
		return null;
	}
	 
	else return listEvents;
}



}


























	