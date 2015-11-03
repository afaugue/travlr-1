import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**********************************************************************
* Name:    SearchFlowView
* Description:
*      SearchFlowView is responsible for formatting the UserInterface
*      during the SearchFlow component of the application. This component
*      will take user input and use the SearchController to send queries to
*      the database for a list of flights. Once the SearchController returns
*      this list of Flights they will be displayed by the SearchFlowView.  The user
*      will be able to interact with this View in order to retrieve different data
*      about the Flight.
*
*      SearchView will be given a list of Flight objects.
*                  From this list SearchView will be able to rearrange the Flight objects
*                  into a variety of different layouts.
*                  These view include a Calander layout and List layout.
*
*

**********************************************************************/
public class SearchFlowView extends JPanel {
    // Global Variable Init of Class Base Properties
    private String display;
    private String[] airports;
    private ArrayList<Map> flight_data;
    private String src, dest, time, date;
    private JPanel search_pane, content_pane;

    // Interactive Elements to be Accessed by Controller
    protected JButton search_submit_button, one_way_btn, two_way_btn;
    protected JButton[] booking_buttons = {};

    // Global Selection Variables
    private JComboBox src_select, dest_select;
    private JTextField date_select;
    private JSpinner time_select;

    // Global Border Variables
    private Border empty_border = BorderFactory.createEmptyBorder(10,10,10,10);
    private Border border = BorderFactory.createLineBorder(Color.BLACK);
    private Border inner_border = BorderFactory.createCompoundBorder(
            empty_border,
            border);

    /********************************************************************
     * Name:    SearchFlowView()    :   Constructor                     *
     * Purpose:                                                         *
     * Params:                                                          *
     * Postcondition:                                                   *
     *      SearchFlowView() object has been created.                   *
     ********************************************************************/
    public SearchFlowView(){
        this.display = "default";
    }

    /********************************************************************
     * Name:    displayView()   :   Method                              *
     * Purpose:                                                         *
     ********************************************************************/
    protected void updateView(){
        this.removeAll();

        // Determine Displays to Add.
        if ( this.display == "default" ){
            this.add( searchView() );
        } else if ( this.display == "calendar" ){
            this.add( calendarView() );
        } else if ( this.display == "list" ){
            this.add( search_pane );
            this.add( listView() );
            this.revalidate();
            this.repaint();
        } else {
            System.out.println(" Invalid view type. ");
        }
    }

    /*******************************************************************
    * Name:    searchView()   :   Method                               *
    * Purpose:                                                         *
    ********************************************************************/
    private JPanel searchView() {
        search_pane = new JPanel();

        // Round-Trip vs One-Way Toggle
        one_way_btn = new JButton("One-Way");
        two_way_btn = new JButton("Two-Way");
        one_way_btn.setActionCommand("one");
        two_way_btn.setActionCommand("two");
        one_way_btn.setEnabled(false);
        one_way_btn.setBackground(Color.BLUE);

        // Add Trip Toggle Buttons to Panel
        search_pane.add(one_way_btn);
        search_pane.add(two_way_btn);

        // Init Search Input Displays
        src_select = new JComboBox(this.airports);
        dest_select = new JComboBox(this.airports);
        date_select = new JTextField(10);
        time_select = new JSpinner();
        search_submit_button = new JButton("submit");

        // Add Input Displays to Panel
        search_pane.add(src_select);
        search_pane.add(dest_select);
        search_pane.add(date_select);
        search_pane.add(time_select);
        search_pane.add(search_submit_button);

        return search_pane;
    }

    /*******************************************************************
    * Name:    calendarView()   :   Method                             *
    * Purpose:                                                         *
    ********************************************************************/
    private JPanel calendarView() {
        content_pane = new JPanel();
        return content_pane;
    }

    /*******************************************************************
    * Name:    listView()   :   Method                                 *
    * Purpose:                                                         *
    ********************************************************************/
    private JPanel listView() {
        booking_buttons = new JButton[flight_data.size()];
        Boolean matching_values = false;

        // Initialize Outer Panel
        content_pane = new JPanel();
        content_pane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;

        for(int i=0; i < flight_data.size(); i++){
            Map<String, String> data = new HashMap(flight_data.get(i));

            // Filter Results by Date
            Boolean date_filter = dateFilter(data.get("datetime"));

            // Create Displays for Matching Flights
            if (date_filter){
                content_pane.add( generateFlightListing(data, i), gbc);
                matching_values = true;
            } else if (parseDate(this.getDate()) == null){
                content_pane.add( generateFlightListing(data, i), gbc);
                matching_values = true;
            }
            gbc.gridy++;
        }

        if (!matching_values){
            content_pane.add(new JLabel("No Flights Matching Your Criteria"));
        }
        return content_pane;
    }

    private JPanel generateFlightListing(Map<String, String> data_map, int position){
        JPanel flight_panel = new JPanel();

        // Create Information Displays
        JLabel flight_id = new JLabel(data_map.get("flight_id"));
        JLabel airport_info = new JLabel(data_map.get("source") + " --> " + data_map.get("destination"));
        JLabel date_info = new JLabel(data_map.get("datetime"));
        JLabel price_info = new JLabel("$"+data_map.get("price"));
        JButton book_btn = new JButton("Book Flight");
        booking_buttons[position] = book_btn;

        // Provide Information Spacing
        flight_id.setBorder(empty_border);
        airport_info.setBorder(empty_border);
        date_info.setBorder(empty_border);
        price_info.setBorder(empty_border);
        book_btn.setBorder(empty_border);

        // Add Individual Information Dispalys to Panel
        flight_panel.add(flight_id);
        flight_panel.add(airport_info);
        flight_panel.add(date_info);
        flight_panel.add(price_info);
        flight_panel.add(book_btn);
        flight_panel.setBorder(inner_border);

        return flight_panel;
    }

    private Boolean dateFilter(String input_date){
        // Initialize Selected Date Variables
        Date selected_date = parseDate(this.getDate() + " " + this.getTime());
        Calendar date1 = dateToCalendar(selected_date);

        // Initialize Flight Date Variables
        Date flight_date = parseDate(input_date);
        Calendar date2 = dateToCalendar(flight_date);

        if ( date1 != null && date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) &&
                date1.get(Calendar.DAY_OF_YEAR) == date2.get(Calendar.DAY_OF_YEAR)) {
            return true;
        } else {
            return false;
        }
    }

    /********************************************************************
     *  Utility Functions                                               *
     ********************************************************************/
    protected GridBagConstraints getConstraints(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        return gbc;
    }

    private Date parseDate(String input_date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date parsed_date = null;
        try {
            parsed_date = sdf.parse(input_date);
        } catch (ParseException e){}
        return parsed_date;
    }

    private Calendar dateToCalendar(Date d){
        if (d != null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            return cal;
        } else {
            return null;
        }
    }


    /********************************************************************
     *  Getter and Setter Functions.                                    *
     ********************************************************************/
    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
        this.updateView();
    }

    public String[] getAirports() {
        return airports;
    }

    public void setAirports(String[] airports) {
        this.airports = airports;
    }

    public ArrayList<Map> getFlightData() {
        return flight_data;
    }

    public void setFlightData(ArrayList<Map> flight_list) {
        this.flight_data = flight_list;
    }

    public String getSrc() {
        this.setSrc( (String) src_select.getSelectedItem());
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDest() {
        this.setDest((String) dest_select.getSelectedItem());
        return this.dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getTime() {
        this.setTime(Integer.toString((int) time_select.getValue()));
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        this.setDate(date_select.getText());
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
