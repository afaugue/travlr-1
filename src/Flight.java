import java.util.Date;

/**#######################################################################
 * # Class Name:    Flight
 * # Descriptions:  A Flight is an object and a Start Location and a Destination Location.
 *                  Flights will have Time, Baggage, and a list of Available Seats.
 *                  Seats and Baggage Pricing Ratios will also be included. User should be
 *                  able to create an individual Booking using the variables within a Flight object.
 *
 *  Invariants:
 *                  Baggage is capped to a weight limit.
 *
 *                  getPassengers() < availableSeats
 *                     Number of passengers should never be greater than seats available
 *
 *
 */

public class Flight {
    public String flight_id, start_location, destination_location;
    public Date flight_date;
    public String flight_time;
    public String[] available_seats;
    public float baggage_price, seat_price;
    public int miles;

    public Flight( String id, String start, String end, Date date, String[] seats,
                   String time, float bags, float seat, int inMiles){
        flight_id = id;
        start_location = start;
        destination_location = end;
        flight_time = time;
        flight_date = date;
        available_seats = seats;
        baggage_price = bags;
        seat_price = seat;
        miles = inMiles;
    }

    //** Getter/Setter/Add funcions for Flight
    static void setFlightID(){}
    static String getFlightID(){ return ""; }
    static void setStartLocation(){}
    static String getStartLocation(){ return ""; }
    static void setDestLocation(){}
    static String getDestLocation(){ return ""; }
    static void setFlightTime(){}
    static String getFlightTime(){ return ""; }
    static void setAvailSeats(){}
    static String getAvailSeats(){ return ""; }
    static void setBagPrice(){}
    static String getBagPrice(){ return "";}
    static void setSeatPrice(){}
    static String getSeatPrice(){ return ""; }
    static void setMiles(){}
    static String getMiles(){ return ""; }

    static void bookSeat(){}
    /**

    /** Calculates rewards points from all Bookings Items */
    static void updateRewards() {}

    /** Enters or Updates entry for this Account in database */
    static void updateDatabase() {}

    /** Removes this Account if it exists in database. */
    static void removeDatabase() {}

    /** Attempts to establishes an Account object using input credentials */
    //Account loginToAccount() { return new Account(); }

    public String[] getAvailableSeats() {
        String[] strings = {};
        return strings;
    }
    /** This function returns a list of available seats */

    public void bookSeat(String seat) {}
    /** Removes input seat from available seats
     * pre: Input seats AvailableSeats list.
     * post: Input seat has been removed from Available Seat.
     */

}
