import java.util.Date;

/**#######################################################################
 * # Class Name:    Booking
 * # Descriptions:  Booking object contains all necessary data relavent to a booking. 
 *     				It creates the connection between the Flight, a (optional) Users Account,
 *        		    and all (optional) Ancillaries
 *
 *  Invariants:
 *                  Credit card is declined.
 *
 *
 */
public class BookingModel {
    String booking_id;
    AccountModel account;
    Flight flight;
    Ancillary[] ancillaries;
    String[] seat_ids;
    float total_price;
    String seating_class;
    int baggage;
    Date booking_datetime;

    protected static void addAccount(AccountModel acc){}
    /**
     * Adds the Account to the Booking
     * @param acc 
     */
    
    protected static void addFlight(Flight flight){}
    /**
     * Adds the Flight to the Booking. 
     */
    
    protected static void addAncillary(Ancillary anc){}
    /**
     * Adds an Ancillary object to the booking.
     */
    
    protected static void calculateTotalPrice(Flight f, Ancillary[] a){}
    /**
     * Iterates through the objects connected to the Booking in order to determine 
     * prices for each component. Component prices are then added together and 
     * set to the total_price of Bookings object. Then will promt the user for 
	 * credit card infomation.
	 * Precondtions: Credit card infomation is valid.
	 * Postconditions: Charge credit card.
     */
    
    protected static void cancelBooking(){}
	/*
	 * Users will be able to cancel their selected booking
	 * under the right circumstances, users will be able to cancel flight and get full refund
	 */
}
