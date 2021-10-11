import java.util.HashMap;
import java.util.Map.Entry;

public class Guest {
	
	
	private String name;
	private int guestID;
	private int count = 0;
	HashMap<Integer, String> guestList = new HashMap<Integer, String>();
	public static Guest guestInstance;											//ONLY 1 INSTANCE FOR THE ENTIRE CLASS TO KEEP THE SAME DATA THROUGHOUT THE PROGRAM'S EXECUTION
	private Guest() {}

	
    public static Guest getInstance() {
        if (guestInstance == null)
        	guestInstance = new Guest();
        return guestInstance;
    }
		
	
    public Guest(String name) {
		this.name = name;
	}
	
    
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	public void addGuest() {
		count++;
		guestList.put(this.count,this.name);
	}
	
	
	public int getGuestID(String name) {
				
		boolean checkGuestName = guestList.containsValue(name); 
		
		if (checkGuestName == true)							
		{
			for (Entry<Integer, String> entry : guestList.entrySet()) {		
	            if (entry.getValue().equals(name)) {						
	                guestID = entry.getKey();								
	            }
			}
			return guestID;													
		}
		else {
			return 0;														
		}		
		
	}
	
		
	public boolean checkGuestID(int id) {
		
		boolean checkGuestID = guestList.containsKey(id);				
			
		if (checkGuestID) return true;															
		else return false;												
		
	}
	
	
	public String getGuestName(int id) {
		
		if (checkGuestID(id))
			return guestList.get(id);
		else
			return "Guest Name Not Found!";
	}
}
