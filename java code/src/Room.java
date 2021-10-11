import java.util.Arrays;
import java.util.HashMap;

public class Room{
	
	private int roomID;		
	private int roomCapacity;
	HashMap<Integer, Integer> roomCompositeKey = new HashMap<Integer, Integer>();											
	HashMap<String, Boolean[]> roomList = new HashMap<String, Boolean[]>();
	String isBooked = "";
	String roomCompositeKeyString;
	public static Room roomInstance;																						
	private Room() {}
	
	
    public static Room getInstance() {
        if (roomInstance == null)
            roomInstance = new Room();
        return roomInstance;
    }
		
	public Room(int roomID, int capacity) {
			this.roomID = roomID;
			this.roomCapacity = capacity;		
		}
				
	public void setRoom(int roomID) {
		this.roomID = roomID;
	}
	
	public int getRoom() {
		return this.roomID;
	}
	
	public void setCapacity(int capacity) {
		this.roomCapacity = capacity;
	}
	
	public int getCapacity() {
		return this.roomCapacity;
	}
	
	public void setRoomKey() {
		
		roomCompositeKeyString = Integer.toString(this.roomID) + Integer.toString(this.roomCapacity);
		roomCompositeKey.put(this.roomID, this.roomCapacity);
	}

	public String getRoomKeyString() {
		return roomCompositeKeyString;
	}
	
		
	public void addRoom() {
		
		Boolean[] roomStatus = new Boolean[366];				
		
		Arrays.fill(roomStatus, false);							
			
		
		if (roomStatus[365] == false) {
			
			setRoomKey();
			
			roomList.put(getRoomKeyString(), roomStatus);			
			
		}
		
	}
	
	
	public boolean checkRoom(int roomID) {
		
		boolean checkRoom = false;
		
		checkRoom = roomCompositeKey.containsKey(roomID);
		
		return checkRoom;										 
										
	}
	
	
	public int getRoomCapacity(int roomID) {
		
		boolean checkRoom = checkRoom(roomID);	
		
		if (checkRoom == true) {
			roomCapacity = roomCompositeKey.get(roomID);
			return roomCapacity;							
		}
		else return 0;											
	}
	
			
	public String setBooked(int roomID, int startDayNumber, int endDayNumber) {
		
		boolean isAvailable = true;										
		
		
		isBooked = "";
		int tempCapacity = getRoomCapacity(roomID);
		Boolean[] tempArray;
		String tempRoomKeyString = Integer.toString(roomID) + Integer.toString(tempCapacity);
		
		
		tempArray = roomList.get(tempRoomKeyString);
		
		
		for (int i = startDayNumber; i <= endDayNumber; i++) {
												
			if (tempArray.length > 0 && tempArray[i] == true ) {
				
				isBooked += " | " + Integer.toString(dayNumberToDayOfMonth(i));		
				isAvailable = false;														
			}	
			
		}
		
		if (isAvailable == false) {										
			return isBooked; 
		}
		else {	
			
			
			for (int i = startDayNumber; i <= endDayNumber; i++) {
								
					tempArray[i] = true;											
				}	
			
				roomList.replace(tempRoomKeyString, tempArray);
				isBooked = "setBookedSuccess";											
				return isBooked;			
		}
		
	}
		
	
	public int dayNumberToDayOfMonth(int dayNumber) {							
					
		if (dayNumber < 1 || dayNumber > 365) return 0;							
		if (dayNumber <= 31 ) return dayNumber; // January
		if (dayNumber <= 59 ) return dayNumber - 31; // February
		if (dayNumber <= 90 ) return dayNumber - 59; // March
		if (dayNumber <= 120) return dayNumber - 90; // April
		if (dayNumber <= 151) return dayNumber - 120; // May
		if (dayNumber <= 181) return dayNumber - 151; // June
		if (dayNumber <= 212) return dayNumber - 181; // July
		if (dayNumber <= 243) return dayNumber - 212; // August
		if (dayNumber <= 273) return dayNumber - 243; // September
		if (dayNumber <= 304) return dayNumber - 273; // October
		if (dayNumber <= 334) return dayNumber - 304; // November
		return dayNumber - 334; // December
		}	
	}
