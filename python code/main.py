# -*- coding: utf-8 -*-

from guest import Guest
from room import Room
from bookedRoom import BookedRoom

guest = []
room = []
bookedRoom = []

# =============================================================================
# Option 1: Add Guest
# =============================================================================
def addGuest():    
    
    global guest

    while(True):      
        #Take input from user
        guestName = input("Enter name for the guest \n")        
        
        #    insert guest array
        newGuest = Guest(guestName)
        print("New Guest ", newGuest.name, "added with ID: ", newGuest.id)
        guest.append(newGuest)      
        
        repeat = input("Would you like to [Any Key]add another or [R] to go back").lower()
        
        #    condition to stop loop
        if repeat == "r":
            break

# =============================================================================
# Option 2: Add room
# =============================================================================
def addRoom():
    global room
    
    while(True):
        # Take input form user
        roomNumber = int(input("Please enter room number: "))
                
        if checkRoomExists(roomNumber) == True:
            print("Room already exists, please enter another")
            continue;
        
        roomCapacity = int(input("Please enter room capacity: "))
        
        room.append(Room(roomNumber, roomCapacity))
        
        repeat = input("Would you like to [Any Key]add another or type [R] to return").lower()
        
        #    condition to stop loop
        if repeat == "r":
            break
        

# =============================================================================
# Option 3: Add booking
# =============================================================================
def addBooking():
    global bookedRoom
    
    while(True):
        try:
            guestID = int(input("Please enter guest ID \n"))
        except:
            print("Invalid value submitted! \n Try again? \n")
            continue
            
        if checkGuestExists(guestID) == False:
            continue
        
        try:
            roomNumber = int(input("Please enter room number \n"))
        except:
            print("Invalid value submitted! \n Try again? \n")
            continue
        
        #check room number
        if checkRoomExists(roomNumber) == False:
            print("The room #", roomNumber, " does not exists. Please select proper room")
            continue
        
        try:
            numberOfGuest = int(input("Please enter number of guest \n"))
        except:
            print("Invalid value submitted! \n Try again? \n")
            continue
        
        #check room capacity 
        if checkRoomCapacity(roomNumber, numberOfGuest) == False:
            print("Room #",roomNumber, " capacity exceeds with ", numberOfGuest, " guest")
            continue
        
        try:
            checkInMonth = int(input("Enter check-in month \n"))
        except:
            print("Invalid value submitted! \n Try again? \n")
            continue
        
        #validate month
        if validateMonth(checkInMonth) == False:
            print("Not a valid month")
            continue
        
        try:
            checkInDay = int(input("Enter check-in day \n"))
        except:
            print("Invalid value submitted! \n Try again? \n")
            continue
        
        #validate day
        if validateDay(checkInDay) == False:
            print("Not a valid day")
            continue
        
        try:
            checkOutMonth = int(input("Enter checkout month \n"))
        except:
            print("Invalid value submitted! \n Try again? \n")
            continue
        
        #validate month
        if validateMonth(checkOutMonth) == False:
            print("Not a valid month")
            continue
        
        try:
            checkOutDay = int(input("Enter checkout day \n"))
        except:
            print("Invalid value submitted! \n Try again? \n")
            continue
        
        #validate day
        if validateDay(checkOutDay) == False:
            print("Not a valid day")
            continue
        
        bookedRoom.append(BookedRoom(guestID, roomNumber, numberOfGuest, checkInMonth, checkInDay, checkOutMonth, checkOutDay))
        print("*** Successfully Booked a room! ***")
        
        repeat = input("Would you like to [Any Key]add another Booking or type [R] to return").lower()
        
        #    condition to stop loop
        if repeat == "r":
            break

# =============================================================================
# Option 4: View Booking
# =============================================================================
def viewBooking():
    global room
    global guest
    global bookedRoom
    
    if len(bookedRoom) == 0:
        print("No bookings available \n *****************")
        return
    
    while (True):
        option = input("Would you like to view [G]uest booking or [R]oom Booking or [Any Key] to Menu? ").lower()
    
        if option == "g":
            guestID = int(input("Please enter GuestID"))
            if checkGuestExists(guestID) == False:                
                continue;

            printBookingByGuest(guestID)
            
        elif option == "r":
            roomNumber = int(input("Please enter room Number"))
            #check room number
            if checkRoomExists(roomNumber) == False:
                print("The room #", roomNumber, " does not exists. Please select proper room")
                continue
            
            printBookingByRoom(roomNumber)
            
        else:
            break
            
# =============================================================================
# Printing Functions
# =============================================================================

def printBookingByGuest(guestID):
    global guest
    global bookedRoom
    
    for i,v in enumerate(bookedRoom):
        if v.guestID == guestID:
            for k,g in enumerate(guest):
                if g.id == guestID:
                    print("Guest ",guestID,": ",g.name)
            print("Booking : Room ",v.roomNumber,", ", v.guestCount, "guest(s) from", v.checkInMonth,"/",v.checkInDay, " to ", v.checkOutMonth,"/", v.checkOutDay)

def printBookingByRoom(roomNumber):
    global guest
    global bookedRoom
     
    for i,v in enumerate(bookedRoom):
        if v.roomNumber == roomNumber:
            print("Room ",roomNumber, " bookings:")
            
            for k,g in enumerate(guest):
                if g.id == v.guestID:
                    print("Guest ",g.id, " - ", g.name, ", ", v.guestCount," guest(s) from ", v.checkInMonth,"/",v.checkInDay, " to ", v.checkOutMonth,"/", v.checkOutDay)

def printMenu():
    print("Main Menu - Please select option: ")
    print("1. ) Add Guest")
    print("2. ) Add Room")
    print("3. ) Add Booking")
    print("4. ) View Booking")
    print("5. ) Quit")
    
    try:
        read = int(input())
        return read
    except:
        print("Invalid value submitted\n\n ******************")
        
# =============================================================================
# Validation functions
# =============================================================================
def checkGuestExists(guestID):
    global guest
    
    for i, v in enumerate(guest):
        if v.id == guestID:
            return True
            break
        else:
            i = -1
    
    if i == -1:
        print("Guest does not exist.")
        return False

def checkRoomExists(roomNumber):
    global room
    
    if(len(room) == 0):
        return False;
        
    for i,v in enumerate(room):
        if v.roomNumber == roomNumber:
            return True
            break
        else:
            i = -1
    
    if i == -1:
        return False

def checkRoomCapacity(roomNumber, guestCount):
    global room
    
    for i,v in enumerate(room):
        if v.roomNumber == roomNumber:
            if v.capacity >= guestCount:
                return True
            else:
                i = -1
        else:
            i = -1
                
    if i == -1:
        return False
    
def validateMonth(month):
    if month < 13:
        return True
    else:
        return False

def validateDay(day):
    if day < 32:
        return True
    else:
        return False
# =============================================================================
# End Validation Functions
# =============================================================================



# =============================================================================
# Main Functions
# =============================================================================

def main():
   while(True):    
        menu = printMenu()
        
        if menu == 1:
            addGuest()
        elif menu == 2:
            addRoom()
        elif menu == 3:
            addBooking()
        elif menu == 4:
             viewBooking()
        elif menu == 5:
            print("Thank you for using the sotware. Quitting...")
            break;
        else:
            continue
            
if __name__ == '__main__':
    main()    