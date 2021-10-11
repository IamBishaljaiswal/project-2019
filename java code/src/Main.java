import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);		
		int option;
		String inside_loop;
		boolean operation_next = true;		
		String nameGuest = "";		
		int numberRoom = 0;
		int capacityRoom = 0;
		boolean currentRoom = false;		
		int guestID = 0;
		int numberGuests = 0;
		int checkinDay = 0;
		int checkinMonth = 0;
		int checkoutDay = 0;
		int checkoutMonth = 0;
		boolean currentGuest = false;
		boolean currentRoomNumber = false;
		int currentCapacity = 0;
		String currentBooking = "";
		int startDayNumber = 0;
		int endDayNumber = 0;
		int dayNumberToMonth = 0;

	try {
		
		
			while (operation_next) {
		
						
				System.out.flush();
				System.out.println("Hotel Bookings");
				System.out.println("1 - Add Guest");
				System.out.println("2 - Add Room");
				System.out.println("3 - Add Booking");
				System.out.println("4 - View Bookings");
				System.out.println("5 - Exit");
				System.out.print("\nType the option: ");
				option = input.nextInt();
		


			switch (option) {
			case 1: // ADD A GUEST
					
					boolean inner_transaction = true;
					
					do {

						System.out.print("\nPlease, type the guest name: ");
						nameGuest = input2.nextLine();
					
						if (nameGuest.equals("") || nameGuest.equals(null))
							System.out.println("\nThe guest name cannot be null!");
						else {
							
							Guest.getInstance().setName(nameGuest);
							Guest.getInstance().addGuest();
							guestID = Guest.getInstance().getGuestID(nameGuest);

							
							System.out.println("\nThe guest " + nameGuest + " has been added under guest ID " + guestID + "!");
						
							
							System.out.print("\nPress [0]Add New Guest or any other key to return to the main menu: ");
							inside_loop = input2.nextLine();
						
							
							if (inside_loop.equals("0") == false)
								inner_transaction = false;
						}
						
						
					} while (inner_transaction);
					break;
			case 2: 
					
				boolean inner_transaction1 = true;
				
				do {
					
					do {

						System.out.print("\nPlease type the room number: ");
						numberRoom = input.nextInt();
						
						if (numberRoom < 0)
							System.out.println("\n The room number cannot be negative. Please type a positive room number!");
						else if (numberRoom == 0)
							System.out.println("\nThe room number has to be higher than 0!");
						else {							
						
							currentRoomNumber = Room.getInstance().checkRoom(numberRoom);
							if (currentRoomNumber == true)  											
								System.out.println("\nThis room number already exists. Please type another room number!");
							else
								inner_transaction1 = false;
						}
						
					} while (inner_transaction1);
					
					
					do {			
						
						inner_transaction1 = true;
						
						System.out.print("\nPlease type the room capacity: ");
						capacityRoom = input.nextInt();
							
						
						if 	(capacityRoom < 0)
							System.out.print("\nThe room capacity cannot be negative. Please type a positive capacity!");
						else if (capacityRoom == 0)
							System.out.print("\nThe room capacity has to be higher than 0!");
						else
							inner_transaction1 = false;
						
					} while (inner_transaction1);
					
						
						inner_transaction1 = true;					
					
						Room.getInstance().setRoom(numberRoom);
						Room.getInstance().setCapacity(capacityRoom);
						Room.getInstance().addRoom();

						System.out.println("\nThe room number " + numberRoom + " with capacity for " + capacityRoom + " individual(s) has been added!");
							
							
						System.out.print("\nPress [0]Add New Room or any other key to return to the main menu: ");
						inside_loop = input2.nextLine();
							
						if (inside_loop.equals("0") == false)
							inner_transaction1 = false;		 							
						
						
					} while (inner_transaction1);
				break;
			case 3: 	
								
					boolean inner_transaction2 = true;
					boolean inner_transaction4 = true;
					boolean inner_transaction5 = true;
								
					do {
						
						
						do {
						
							inner_transaction2 = true;
						
							System.out.print("\nPlease type the guest ID: ");
							guestID = input.nextInt();

							
							currentGuest = Guest.getInstance().checkGuestID(guestID);
							if (currentGuest == false) {				
								System.out.print("\nThis guest ID does not exist. Please register this guest first or enter a valid guest ID!");
								System.out.println("\nWould you like to create a new guest [Y]es or [N]o?: ");
								String choiceGuest = input2.nextLine().toUpperCase();
								
								if (choiceGuest.equals("Y")) {
									
																		
									System.out.print("\nPlease, type the guest name: ");
									nameGuest = input2.nextLine();
								
									
									if (nameGuest.equals("") || nameGuest.equals(null))
										System.out.println("\nThe guest name cannot be null!");
									else {
										
										inner_transaction2 = false;
										
										Guest.getInstance().setName(nameGuest);
										Guest.getInstance().addGuest();
										guestID = Guest.getInstance().getGuestID(nameGuest);

										System.out.print("\nThe guest " + nameGuest + " has been added under guest ID " + guestID + "!");
										System.out.println("\nThis guest will be used for this booking!");
									}
								} else {								
									System.out.print("\nThe booking cannot continue without a valid guest ID. The application has been closed!");
									System.exit(0);
								}
								
							} 
							else
								inner_transaction2 = false;
							
						} while (inner_transaction2);
					
					
						
						do {
						
							inner_transaction2 = true;
							inner_transaction4 = true;
						
							System.out.print("\nPlease type the room number: ");
							numberRoom = input.nextInt();

							
							currentRoomNumber = Room.getInstance().checkRoom(numberRoom);
							if (currentRoomNumber == false) {
								System.out.print("\nThis room number does not exist. Please register this room first or enter a valid room number!");
								
													
								do {									
									
									if (numberRoom < 0) {
										System.out.println("\nThe room number cannot be negative. Please type a positive room number!");
										inner_transaction4 = false;
									}
									else if (numberRoom == 0) {
										System.out.println("\nThe room number has to be higher than 0!");
										inner_transaction4 = false;
									}
									else {
										
										System.out.println("\nWould you like to create the room number " + numberRoom + " now? [Y]es or [N]o?: ");
										String choiceGuest = input2.nextLine().toUpperCase();
																			
										if (choiceGuest.equals("Y")) {
																		
											
											inner_transaction4	= false;
											
											
											do {			
												
								
												System.out.print("\nPlease type the room capacity for the room number " + numberRoom + ": ");
												capacityRoom = input.nextInt();
													
												
												if 	(capacityRoom < 0)
													System.out.println("\nThe room capacity cannot be negative. Please type a positive capacity!");
												else if (capacityRoom == 0)
													System.out.println("\nThe room capacity has to be higher than 0!");
												else {
													
													inner_transaction5 = false;
													inner_transaction4 = false;
													inner_transaction2 = false;
													
													Room.getInstance().setRoom(numberRoom);
													Room.getInstance().setCapacity(capacityRoom);
													Room.getInstance().addRoom();

													System.out.print("\nThe room number " + numberRoom + " with capacity for " + capacityRoom + " individual(s) has been added!");
													System.out.println("\nThis room number will be used for this booking!");
												}
												
											} while (inner_transaction5);									
										} else { 
											System.out.print("\nThe booking cannot continue without a valid room number. The application has been closed!");
											System.exit(0);										
									} 
								}
									
								}while (inner_transaction4);
							}
							else
								inner_transaction2 = false;
							
						} while (inner_transaction2);

					
					
						do {
						
							inner_transaction2 = true;
						
							System.out.print("\nPlease enter the number of guests: ");
							numberGuests = input.nextInt();

							currentCapacity = Room.getInstance().getRoomCapacity(numberRoom);
							
							if (numberGuests < 0)
								System.out.print("\nThe number of guests cannot be negative. Please type a positive number!");
							else if (numberGuests == 0)
								System.out.print("\nThe number of guests has to be higher than 0!");
							else if (numberGuests > currentCapacity)
								System.out.print("\nThe number of guests entered exceeded this room's capacity of " + currentCapacity + "!");
							else
								inner_transaction2 = false;
							
						} while (inner_transaction2);

					
					
						do {						
						
							inner_transaction2 = true;
						
							System.out.print("\nPlease type the check-in month: ");
							checkinMonth = input.nextInt();

							
							if (checkinMonth < 1 || checkinMonth > 12)				
								System.out.println("\nThe check-in month has to be between 1 and 12!");
							else
								inner_transaction2 = false;
							
						} while (inner_transaction2);
					
					
						do {
						
							inner_transaction2 = true;
									
							System.out.print("\nPlease type the check-in day: ");
							checkinDay = input.nextInt();

							
							startDayNumber = Booking.getInstance().dateToDayNumber(checkinDay, checkinMonth);
									
							
							dayNumberToMonth = Booking.getInstance().dayNumberToMonth(startDayNumber);

							if (dayNumberToMonth != checkinMonth || startDayNumber == 0) {								
								System.out.println("\nLooks like this day does not exist in the month " + checkinMonth + "!");		
								System.out.println("Please check the calendar and enter a valid day for the chosen month!");
							}
							else 
								inner_transaction2 = false;
							
						} while (inner_transaction2);
					
					
					
						do {
							
							inner_transaction2 = true;
							
							System.out.print("\nPlease type the check-out month: ");
							checkoutMonth = input.nextInt();

							if (checkoutMonth < 1 || checkoutMonth > 12) 			
								System.out.println("\nThe check-out month has to be between 1 and 12!");
							else if (checkoutMonth < checkinMonth)
								System.out.println("\nThe check-out month cannot be shorter than the check-in month!");
							else
								inner_transaction2 = false;
							
						} while (inner_transaction2);
					
					
					
						do {
						
							inner_transaction2 = true;
																													
							System.out.print("\nPlease type the check-out day: ");
							checkoutDay = input.nextInt();															
											
							endDayNumber = Booking.getInstance().dateToDayNumber(checkoutDay,checkoutMonth);

							dayNumberToMonth = Booking.getInstance().dayNumberToMonth(endDayNumber);

							if (dayNumberToMonth != checkoutMonth || endDayNumber == 0 || endDayNumber < 0) {								
								System.out.print("\nLooks like this day does not exist in the month " + checkoutMonth + "!");		
								System.out.println("Please check the calendar and enter a valid day for the chosen month!");
							}
							else if (endDayNumber < startDayNumber)
								System.out.print("\nThe check-out date is shorter than the check-in date! Enter a valid date range!");
							else if (endDayNumber == startDayNumber)
								System.out.print("\nThe check-out date is equal to the check-in date! Enter a valid date range!");
							else 
								inner_transaction2 = false;
							
						} while (inner_transaction2);
					
					
						inner_transaction2 = true;
						
						Booking.getInstance().setCheckInDate(checkinDay, checkinMonth);		
					
					
						Booking.getInstance().setCheckOutDate(checkoutDay, checkoutMonth);
					
					
						currentBooking = Booking.getInstance().addBooking(guestID, numberRoom, numberGuests, checkinMonth, checkinDay, checkoutMonth, checkoutDay);
					
					
						if (currentBooking != "addBookingSuccess") {	
							System.out.println("\nThe following days have already been booked for this room: " + currentBooking);														
							System.out.println("Choose the option [4] View Booking to see the check-in and check-out dates that the days above refer to!");
							System.out.println("Please try again using available days!");
						}
						else {								//No prior bookings, therefore, success in the inclusion of the booking
							System.out.println("\nRoom scheduled successfully");
							System.out.println("Room Number:" + numberRoom); 
							System.out.println("Room Capacity:" + currentCapacity); 
							System.out.println("Guest ID:" + guestID); 
							System.out.println("Guest Name:" + Guest.getInstance().getGuestName(guestID));
							System.out.println("Booked for:   " + numberGuests	+ " Individual(s)");
							System.out.println("Check-in:   " + checkinDay 	+ "/" 	+ 	checkinMonth 	+ 	"/2019"); 
							System.out.println("Check-out:" + checkoutDay 	+ "/" 	+	checkoutMonth 	+ 	"/2019"); 			
						}	
					
						System.out.println("\nPress [0]Add New Booking or any other key to return to the main menu: ");
						inside_loop = input2.nextLine();
					
						
						if (inside_loop.equals("0") == false)
							inner_transaction2 = false;
						
					} while (inner_transaction2);
					
					break;					
			case 4: 
		
				boolean inner_transaction3 = true;
			
				do {					
					
								
					do {
						
						inner_transaction3 = true;
								
						System.out.print("\nSearch by [1]Guest ID or [2]Room Number: "); 
						option = input.nextInt();
			 
						if (option == 1) {  

				
							do {
																						
								System.out.print("\nPlease type the guest ID: "); 
								guestID = input.nextInt();
					
								currentGuest = Guest.getInstance().checkGuestID(guestID);
								if (currentGuest == false) {
									System.out.print("\nThis Guest ID does not exist. Please enter a valid guest ID!"); 
									System.out.println("\nType [C]lose if you wish to close the application!"); 
								} 	
								else {
									Booking.getInstance().getBookingByGuest(guestID);
									inner_transaction3 = false;
								} 
					
							} while (inner_transaction3);
					
				
						} else if (option == 2) { 
					
							do {
					
									  
								System.out.print("\nPlease type the room number: ");
								numberRoom = input.nextInt();
				  
								currentRoom = Room.getInstance().checkRoom(numberRoom);
								if (currentRoom == false) {
									System.out.print("\nThis room number does not exist. Please enter a valid room number!");
									System.out.println("\nType [C]lose if you wish to close the application!"); 
								} else {
									Booking.getInstance().getBookingByRoom(numberRoom);
									inner_transaction3 = false;
								} 
				
							} while (inner_transaction3);
						}
					else
						System.out.println("\nThis option is not available! Try again with a valid option!");		
				
					} while (inner_transaction3);
								
			
					inner_transaction3 = true;
					
					
					System.out.print("\nPress [0]View Other Bookings or any other key to return to the main menu: ");
					inside_loop = input2.nextLine();
		
					if (inside_loop.equals("0") == false)
						inner_transaction3 = false;
			
				} while (inner_transaction3);				
				break;
			case 5: 
				operation_next = false;
				System.out.print("\nThanks for using the Bookings App");
				input.close();
				input2.close();
				System.exit(0);
			default: 
				System.out.println("\nThis option is not available. Select one of the options above!");
				break;
			}
		} 
	} catch (NumberFormatException e1) {
		System.out.println("\nOnly valid numbers are accepted. You have not entered a valid number! Restart the application and try again.");
	} catch (NullPointerException e2) {
		System.out.println("\nA value should have been entered. You have not entered a value! Restart the application and try again.");
	} catch (InputMismatchException e3) {
		System.out.println("\nOnly numbers are accepted for this option. You have not entered a number. Restart the application and try again.");
	} 
	
	} 
} 