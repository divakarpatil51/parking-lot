
<h1><b>Parking Lot Ticketing System</b></h1>

<b>Requirements</b>:

1. Create a parking lot with the given size (Read from text file).
2. If parking slot available, register user vehicle details such as car number, color, and in time and allocate parking slot nearest to the entry.
3. At exit, deallocate and mark slot as available if the car was parked there and calculate total parking charge based on the time spent by car in the parking.
4. Print the status of parking slot at any given time.
   
<b>Assumptions</b>:

1.  Single-threaded application.
2.  User parks in the allocated slot only.
3.  User does not lose his/her ticket. 
4.  One hour charge will be added to the parking charge if time crosses the hour mark i.e if user parks for 3 hours and 10 minutes he/she will be charged for 4 hours.