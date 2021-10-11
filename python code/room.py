# -*- coding: utf-8 -*-

class Room:
    totalRoomCount = 1
    
    def __init__(self, roomNumber, capacity):
        self.id = Room.totalRoomCount
        self.roomNumber = roomNumber
        self.capacity = capacity
        self.booked = False
        Room.totalRoomCount += 1
        
    def addBooking(guestID, roomNumber, guestCount):
        return
        
        