import java.util.Date;
import java.util.Map;

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

public class FlightModel {
    public String flight_id, start_location, destination_location, flight_datetime;
    public String[] available_seats;
    public double baggage_price, seat_price;
    public int miles;

    public FlightModel(String id, String start, String end, String datetime, String[] seats,
                       float bags, float seat, int inMiles){
        flight_id = id;
        start_location = start;
        destination_location = end;
        flight_datetime = datetime;
        available_seats = seats;
        baggage_price = bags;
        seat_price = seat;
        miles = inMiles;
    }

    public FlightModel(Map<String, String> fdata){
        this.flight_id = fdata.get("flight_id");
        this.start_location = fdata.get("source");
        this.destination_location = fdata.get("destination");
        this.flight_datetime = fdata.get("datetime");
        this.seat_price = Double.parseDouble(fdata.get("price"));
    }

    //** Getter/Setter/Add funcions for Flight
    protected void setFlightID(){}
    protected String getFlightID(){ return flight_id; }
    protected void setStartLocation(String s){
        start_location = s;
    }
    protected String getStartLocation(){ return start_location; }
    protected void setDestLocation(String d){
        destination_location = d;
    }
    protected String getDestLocation(){ return destination_location; }
    protected void setDateTime(){}
    protected String getDateTime(){ return flight_datetime; }
    protected void setAvailSeats(){}
    protected String getAvailSeats(){ return ""; }
    protected void setBagPrice(){}
    protected String getBagPrice(){ return "";}
    protected void setSeatPrice(){}
    protected Double getSeatPrice(){ return seat_price; }
    protected void setMiles(){}
    protected String getMiles(){ return ""; }

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
