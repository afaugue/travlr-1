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
import java.util.ArrayList;
import java.text.DecimalFormat;

public class BookingsFlowView extends JPanel {
    private ArrayList<FlightModel> flights = new ArrayList();
    private int booking_state;

    protected JButton return_btn, continue_btn;
    protected InfoPanel info_panel;

    private CreditCardController credit_card_controller;

    private Border empty_border = BorderFactory.createEmptyBorder(10,10,10,10);
    private Border border = BorderFactory.createLineBorder(Color.BLACK);
    private Border inner_border = BorderFactory.createCompoundBorder(
            empty_border,
            border);


    public BookingsFlowView(){}

    public BookingsFlowView(FlightModel f1){
        this.setFlightData(f1);
        this.setBookingState(1);
        this.updateView();
    }

    public BookingsFlowView(FlightModel f1, FlightModel f2){
        this.setFlightData(f1);
        this.setFlightData(f2);
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
            return_btn = new JButton("Return to search");
            continue_btn = new JButton("Continue");
            GridBagConstraints gbc = new GridBagConstraints();
            this.setLayout(new GridBagLayout());

            this.add(pageOneView(), gbc);
            gbc.gridy = 1;
            this.add(continue_btn,gbc);
            gbc.gridy = 2;
            this.add(return_btn,gbc);
        } else if (this.booking_state == 2) {
            pageTwoView();
            this.add(continue_btn);
            this.add(return_btn);
        } else if (this.booking_state == 3) {
            pageThreeView();
        } else if (this.booking_state == 4) {
            this.credit_card_controller = null;
            pageFourView();
        }
    }

    private JPanel pageOneView(){
        JPanel outer_panel = new JPanel();
        outer_panel.setLayout(new GridBagLayout());

        GridBagConstraints right_gbc = new GridBagConstraints();
        GridBagConstraints left_gbc = new GridBagConstraints();

        right_gbc.gridx = 1;
        right_gbc.gridy = 0;
        right_gbc.weightx = .3;

        left_gbc.gridx = 0;
        left_gbc.gridy = 0;
        left_gbc.weightx = .7;

        for (int i=0; i<flights.size(); i++) {
            outer_panel.add(generateFlightPanel(flights.get(i)), left_gbc);
            left_gbc.gridy++;
            outer_panel.add(buildPrice(flights.get(i)), right_gbc);
            right_gbc.gridy++;
            System.out.println(flights.get(i).toString());
        }
        outer_panel.add(buildTax(),right_gbc);
        right_gbc.gridy++;
        outer_panel.add(buildPriceSum(),right_gbc);
        right_gbc.gridy++;


        outer_panel.setBorder(inner_border);
        return outer_panel;
    }

    private JPanel buildPrice(FlightModel input_flight){
        JPanel price_sum = new JPanel();

        JLabel price_info = new JLabel("Price of flight: " + "$"+input_flight.getSeatPrice().toString());
        System.out.println(input_flight.getSeatPrice().toString());

        price_info.setBorder(empty_border);

        price_sum.add(price_info);
        price_sum.setBorder(empty_border);

        return price_sum;
    }

    private JPanel buildTax(){
        JPanel tax_sum = new JPanel();
        DecimalFormat decFor = new DecimalFormat("0.00");

        Double tax_value = 0.00;
        for (int i=0; i<flights.size(); i++) {
            tax_value += (flights.get(i).getSeatPrice() * 0.1);
        }
        JLabel tax_info = new JLabel("Tax: " + "$" + decFor.format(tax_value));

        tax_info.setBorder(empty_border);

        tax_sum.add(tax_info);

        return tax_sum;
    }

    private JPanel buildPriceSum(){
        JPanel price_summary = new JPanel();
        DecimalFormat decFor = new DecimalFormat("0.00");

        Double total_price = 0.0;
        for (int i=0; i<flights.size(); i++){
            total_price += (flights.get(i).getSeatPrice());
            total_price += (flights.get(i).getSeatPrice() * 0.1);
        }
        JLabel final_price = new JLabel("Final Price: " + "$" + decFor.format(total_price));

        final_price.setBorder(empty_border);

        price_summary.add(final_price);

        return price_summary;
    }


    private JPanel generateFlightPanel(FlightModel input_flight){
        JPanel flight_panel = new JPanel();

        JLabel flight_id = new JLabel(input_flight.getFlightID());
        JLabel airport_info = new JLabel(input_flight.getStartLocation() + " -- "+input_flight.getDateTime()+" --> "+input_flight.getDestLocation());
        //JLabel date_info = new JLabel(input_flight.getDateTime());
        //JLabel price_info = new JLabel(input_flight.getSeatPrice().toString());

        flight_id.setBorder(empty_border);
        airport_info.setBorder(empty_border);
        //date_info.setBorder(empty_border);
        //price_info.setBorder(empty_border);

        flight_panel.add(flight_id);
        flight_panel.add(airport_info);
        //flight_panel.add(date_info);
        //flight_panel.add(price_info);
        flight_panel.setBorder(inner_border);

        return flight_panel;
    }

    private JPanel pageTwoView() {
        JPanel outer_panel = new JPanel();
        JLabel flight_source = new JLabel(flights.get(0).getStartLocation());

        flight_source.setBorder(inner_border);


        outer_panel.add(flight_source);



        return outer_panel;
    }

    private void pageThreeView() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        credit_card_controller = new CreditCardController(Travlr.main_frame);
        info_panel = new InfoPanel();
        info_panel.setBorder(inner_border);
        gbc.gridy = 0;
        this.add(info_panel, gbc);

        JPanel card_panel = new JPanel();
        card_panel.add(credit_card_controller.card_view);
        card_panel.setBorder(inner_border);
        gbc.gridy++;
        this.add(card_panel, gbc);

        JPanel btn_panel = new JPanel();
        btn_panel.add(return_btn);
        btn_panel.add(continue_btn);
        gbc.gridy++;
        this.add(btn_panel, gbc);

    }

    private void pageFourView(){

    }

    protected void addPaymentToAccount(){
        credit_card_controller.addCardToAccount();
    }

    protected void addPaymentToBooking(int booking_number){
        credit_card_controller.addCardToBooking(booking_number);
    }

    public ArrayList<FlightModel> getFlightData() {
        return flights;
    }


    public void setFlightData(FlightModel flight_data) {
        this.flights.add(flight_data);
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

    public int lockSeatOnFlight(FlightModel flight, String seat_id){ return 0; }
    /**
     * Creates a database lock on the selected seats on the Flight object.
     * This prevents multiple users from trying to Book the same seat.
     */

}