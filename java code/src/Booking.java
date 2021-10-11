public class Booking{
		
	//DECLARATION OF VARIABLES
	private String dateCheckIn;
	private String dateCheckOut;
	String[] listBookings = new String[300];	
	int countAdd;
	int countView;
	public static Booking bookingInstance;																				
	private Booking() {}			
			
	
	
    public static Booking getInstance() {
        if (bookingInstance == null)
        	bookingInstance = new Booking();
        return bookingInstance;
    }
			
	
	public void setCheckInDate(int day, int month) {
		this.dateCheckIn 	= day + "/" + month + "/" + "2019";
	}
	
	public String getCheckInDate() {
		return dateCheckIn;
	}
	
	public void setCheckOutDate(int day, int month) {
		this.dateCheckOut = day + "/" + month + "/" + "2019";
	}
	
	public String getCheckOutDate() {
		return dateCheckOut;
	}	
		
	
	public String addBooking(int guestID, int roomID, int guestsNumber, int startMonth, int startDay, int endMonth, int endDay) {
		
		int dayNumberStart = dateToDayNumber(startDay, startMonth);
		int dayNumberEnd   = dateToDayNumber(endDay, endMonth);
		
		String isBooked = Room.getInstance().setBooked(roomID, dayNumberStart, dayNumberEnd);		//IF FALSE IT MEANS THAT THE DAYS ARE NOT YET BOOKED
		
		
		if (isBooked == "setBookedSuccess") {					
				
									
			if (listBookings[0] == "" || listBookings[0] == null) {
				
				listBookings[0] = Integer.toString(roomID);
				listBookings[1] = Integer.toString(guestID);
				listBookings[2] = "Guests...........: "  + Integer.toString(guestsNumber);
				listBookings[3] = "Check In Date....: "  + getCheckInDate();
				listBookings[4] = "Check Out Date...: " + getCheckOutDate();
				
				return "addBookingSuccess";
			}
			else {
				
				countAdd +=5;
				
				listBookings[countAdd] = Integer.toString(roomID);
				listBookings[countAdd+1] = Integer.toString(guestID);
				listBookings[countAdd+2] = "Guests...........: "  + Integer.toString(guestsNumber);
				listBookings[countAdd+3] = "Check In Date....: "  + getCheckInDate();
				listBookings[countAdd+4] = "Check Out Date...: " + getCheckOutDate();
				
				return "addBookingSuccess";
			}			
		} else  												
				return isBooked;
	}					
					
	
	public void getBookingByGuest(int guestID) {
		
		int countBooking = 0;
		int countDisplay = 0;
		String tempGuestID = Integer.toString(guestID);
		countView = 6;
				
		//ADDING THE BOOKING DETAILS TO SHOW IN THE GET BOOKING - NOT USED FOR CONSISTENCY
		if (listBookings[0] == null || listBookings[0] == "") {
			System.out.println("\n<INFO> There is currently no booking in the system!");
			countBooking = -1;
		}
		else {
			
			System.out.println("\nShowing all bookings for guest..: " + guestID + " | " + Guest.getInstance().getGuestName(guestID));
			
			
			if (listBookings[1].equals(tempGuestID)){
				countDisplay++;
				countBooking++;
				System.out.println("------SUMMARY OF BOOKING " + countDisplay + " --------");
				System.out.println("Room ID..........: " + listBookings[0]);
				System.out.println("Guest ID.........: " + guestID + " | " + Guest.getInstance().getGuestName(guestID));
				System.out.println(listBookings[2]);
				System.out.println(listBookings[3]);
				System.out.println(listBookings[4]);		
				System.out.println("------END OF BOOKING " + countDisplay + " ------------\n");
			}
			
								
			for (int i = countView; i < listBookings.length; i += 5) {
							
				if (listBookings[i] != null) {
					if (listBookings[i].equals(tempGuestID)) {
						countDisplay++;
						countBooking++;
						System.out.println("------SUMMARY OF BOOKING " + countDisplay + " -------");
						System.out.println("Room ID..........: " + listBookings[i-1]);
						System.out.println("Guest ID.........: " + listBookings[i] + " | " + Guest.getInstance().getGuestName(guestID));
						System.out.println(listBookings[i+1]);
						System.out.println(listBookings[i+2]);
						System.out.println(listBookings[i+3]);	
						System.out.println("------END OF BOOKING " + countDisplay + " ------------\n");
					}
				}
			}		
			
			if (countBooking == 0)
				System.out.println("<INFO> No bookings have been found for the Guest ID: " + guestID + "\n");
				
		}
	}
		
	
	public void getBookingByRoom(int roomID) {
			
		int countBooking = 0;
		int countDisplay = 0;
		String tempRoomID = Integer.toString(roomID);
		int tempGuestID;
		countView = 5;
		

		if (listBookings[0] == "" || listBookings[0] == null) {
			System.out.println("\n<ALERT> There is currently no booking in the system!");
			countBooking = -1;
		}
		else {
			
			System.out.println("\nShowing all bookings for room number..: " + roomID);
			
			if (listBookings[0].equals(tempRoomID)){
				countDisplay++;
				countBooking++;
				tempGuestID = Integer.parseInt(listBookings[1]);
				System.out.println("------SUMMARY OF BOOKING " + countDisplay + " --------");
				System.out.println("Room ID..........: " + roomID);
				System.out.println("Guest ID.........: " + listBookings[1] + " | " + Guest.getInstance().getGuestName(tempGuestID));
				System.out.println(listBookings[2]);
				System.out.println(listBookings[3]);
				System.out.println(listBookings[4]);		
				System.out.println("------END OF BOOKING " + countDisplay + " ------------\n");
			}			
				
			for (int i = countView; i < listBookings.length; i += 5) {
				
				if (listBookings[i] != null) {
					if (listBookings[i].equals(tempRoomID)) {
						countDisplay++;
						countBooking++;
						tempGuestID = Integer.parseInt(listBookings[i+1]);
						System.out.println("------SUMMARY OF BOOKING " + countDisplay + " -------");
						System.out.println("Room ID..........: " + roomID);
						System.out.println("Guest ID.........: " + listBookings[i+1] + " | " + Guest.getInstance().getGuestName(tempGuestID));
						System.out.println(listBookings[i+2]);
						System.out.println(listBookings[i+3]);
						System.out.println(listBookings[i+4]);	
						System.out.println("------END OF BOOKING " + countDisplay + " ------------\n");
					}
				}
			}
		}
			
		if (countBooking == 0)
			System.out.println("<INFO> No bookings have been found for the Room ID: " + roomID + "\n");			
	}
		
				
	public int dateToDayNumber(int day, int month) {
		
		if (month < 1 || month > 12 || day < 1 || day > 31) return 0;			
		if (month == 1) return day;
		if (month == 2) return 31 + day;
		if (month == 3) return 59 + day;
		if (month == 4) return 90 + day;
		if (month == 5) return 120 + day;
		if (month == 6) return 151 + day;
		if (month == 7) return 181 + day;
		if (month == 8) return 212 + day;
		if (month == 9) return 243 + day;
		if (month == 10) return 273 + day;
		if (month == 11) return 304 + day;
		return 334 + day;			
	}
	
	
	public int dayNumberToMonth(int dayNumber) {								
			
		if (dayNumber < 1 || dayNumber > 365) return 0;
		if (dayNumber <= 31 ) return 1; // January
		if (dayNumber <= 59 ) return 2; // February
		if (dayNumber <= 90 ) return 3; // March
		if (dayNumber <= 120) return 4; // April
		if (dayNumber <= 151) return 5; // May
		if (dayNumber <= 181) return 6; // June
		if (dayNumber <= 212) return 7; // July
		if (dayNumber <= 243) return 8; // August
		if (dayNumber <= 273) return 9; // September
		if (dayNumber <= 304) return 10; // October
		if (dayNumber <= 334) return 11; // November
		return 12; // December
	}
					
		
}
