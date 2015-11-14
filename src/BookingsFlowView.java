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
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BookingsFlowView extends JPanel {
    private Map<String, String> flight_data = new HashMap();
    private int booking_state;

    protected JButton return_btn;

    private Border empty_border = BorderFactory.createEmptyBorder(10,10,10,10);
    private Border border = BorderFactory.createLineBorder(Color.BLACK);
    private Border inner_border = BorderFactory.createCompoundBorder(
            empty_border,
            border);


    public BookingsFlowView(){}

    public BookingsFlowView(Map<String, String> fdata){
        this.setFlightData(fdata);
        this.setBookingState(1);
        this.updateView();
    }

    /********************************************************************
     * Name:    displayView()   :   Method                              *
     * Purpose:                                                         *
     ********************************************************************/
    public void updateView() {
        this.removeAll();

        if (this.booking_state == 1) {
            this.add(pageOneView());
            return_btn = new JButton("Return to search.");
            this.add(return_btn);
        } else if (this.booking_state == 2) {
            pageTwoView();
        } else if (this.booking_state == 3) {
            pageThreeView();
        }
    }

    private JPanel pageOneView(){
        JPanel flight_panel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel flight_id = new JLabel(flight_data.get("flight_id"));
        JLabel airport_info = new JLabel(flight_data.get("source") + " --> " + flight_data.get("destination"));
        JLabel date_info = new JLabel(flight_data.get("datetime"));
        JLabel price_info = new JLabel("$"+flight_data.get("price"));

        flight_id.setBorder(empty_border);
        airport_info.setBorder(empty_border);
        date_info.setBorder(empty_border);
        price_info.setBorder(empty_border);

        flight_panel.add(flight_id);
        flight_panel.add(airport_info);
        flight_panel.add(date_info);
        flight_panel.add(price_info);
        flight_panel.setBorder(inner_border);
        return flight_panel;
    }

    private void pageTwoView() {}

    private void pageThreeView() {}

    public Map<String, String> getFlightData() {
        return flight_data;
    }

    public void setFlightData(Map<String, String> flight_data) {
        this.flight_data = flight_data;
    }

    public int getBookingState() {
        return booking_state;
    }

    public void setBookingState(int booking_state) {
        this.booking_state = booking_state;
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