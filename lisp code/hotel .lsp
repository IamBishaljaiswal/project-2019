(setq guestCount 0 )  
(setq roomCount 0 )
(setq bookingCount 0 )
(setq guests (make-array '(10)))
(setq rooms (make-array '(10)))
(setq bookings (make-array '(10)))

; Guest model
(defstruct Guest
   id
   name
)

; Room model
(defstruct Room
   number
   capacity
)

; Booking model
(defstruct Booking
   mroom
   mguest
   startday
   endday
)

(defun findGuestByID (guestID)

)

; select menu options
(defun selectMenu ()
    (terpri)
    (princ "Chose Menu")
    (terpri)
    (princ "1: Add Guest 2: Add Room 3: Add Booking 4: View Booking 5: Quit ")
)

; method to add guest
(defun addGuest ()
    (terpri)
    (princ "Enter Guest Name:")
    (setq name (read))
    (setq guest (make-Guest :id (+ 1 guestCount) :name name))
    (setf (aref guests guestCount) guest)
    ; (write guests)
    (incf guestCount)
    (princ "Guest Successfully Created")
)

; method to add room
(defun addRoom ()
    (terpri)
    (princ "Enter Room Number:")
    (setq roomNumber (read))
    (terpri)
    (princ "Enter Room Capacity:")
    (setq capacity (read))
    (setq room (make-Room :number roomNumber :capacity capacity))
    (setf (aref rooms roomCount) room)
    ; (write rooms)
    (incf roomCount)
    (princ "Room Successfully Added")
)

; method to add booking
(defun addBooking ()
    (terpri)
    (princ "Enter Guest ID:")
    (setq guestID (read))
    (write (findGuestByID guestID))
)

; method to view booking
(defun viewBooking ()
    (terpri)
    (princ "List of bookings:")

    (setq bcount bookingCount)
    (loop
        (setq bcount (- bcount 1))
        (write "Loop")
        (terpri)
        (when (< bcount 1) (return bcount))
    )
)



(princ "Welcome to hotel booking")
(setq iterations 100)      

(loop (= iterations -1)
(selectMenu)
(setq choice (read))

(if (or (> choice 0) (< choice 6) )
   (if (= choice 1)
           (addGuest)
           (if (= choice 2)
                (addRoom)
                (if (= choice 3)
                    (addBooking)
                    (if (= choice 4)
                        (viewBooking)
                        (if (= choice 5)
                            (quit))))))
   (format t "Invalid selection")))

