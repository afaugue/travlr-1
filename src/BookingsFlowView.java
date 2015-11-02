/**
 * Description:
 *      BookingsFlowView is responsible for displaying User Interface
 *      during the BookingsFlow component of the application. The BookingsFlow
 *      component begins when the user selects a flight they would like to purchase.
 *      The bookings flow will escort the user through a few pages necessary to form a
 *      Booking object of the Flight. User interactions with these pages will
 *      allow the controller to retrieve the Flight object, create a Bookings object,
 *      create Ancillary objects, and attach these two items to an Account.
 *
 *      The BookingsFlowView will contain a number of forms as well as specialized
 *      pages for selecting Ancillary objects.
 *
 *      Bookings AccountView allows for the creation of a Booking object.
 *      BookingsView will collect a flight object to initialize the Booking.
 *      Different displays are then passed to the user in order to get additional
 *      information that should be added to the Booking.
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BookingsFlowView extends JPanel {
    private Map<String, String> flight_data = new HashMap();

    protected JButton return_btn;

    public BookingsFlowView(){
    }

    /********************************************************************
     * Name:    displayView()   :   Method                              *
     * Purpose:                                                         *
     ********************************************************************/
    public void updateView(){
        this.removeAll();

        this.setLayout(new GridLayout(5, 1));
        JLabel booking_pane = new JLabel("Bookings flow for: ");
        this.add(booking_pane);
        JLabel fd = new JLabel(flight_data.toString());
        this.add(fd);
        return_btn = new JButton("Return to search.");
        this.add(return_btn);
        this.revalidate();
        this.repaint();
    }

    public Map<String, String> getFlightData() {
        return flight_data;
    }

    public void setFlightData(Map<String, String> flight_data) {
        this.flight_data = flight_data;
    }

    protected GridBagConstraints getConstraints(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        return gbc;
    }

    //public Flight getFlightObject(String id) { return new Flight(); }
    /**
     * Retrieves a flight object for a given id.
     */

    public static void updateBookingState(int state){}
    /**
     * Moves between different pages of the booking flow.
     * AccountView displayed_view = getStateView(flight, state);
     */

    public void addBookingToDatabase(){}
    /**
     * Adds the booking item to the booking database.
     */

    public int lockSeatOnFlight(Flight flight, String seat_id){ return 0; }
    /**
     * Creates a database lock on the selected seats on the Flight object.
     * This prevents multiple users from trying to Book the same seat.
     */

}