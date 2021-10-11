# -*- coding: utf-8 -*-

class Guest:
    totalGuestCount = 1
    def __init__(self, name):
        self.id = Guest.totalGuestCount
        self.name = name
        Guest.totalGuestCount += 1