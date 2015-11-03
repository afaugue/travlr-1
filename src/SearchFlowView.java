import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private String display;
    private String[] airports;
    private ArrayList<Map> flight_data;
    private String src, dest, time, date, results;
    private JPanel search_pane, content_pane;

    protected JButton search_submit_button, one_way_btn, two_way_btn;
    protected JButton[] booking_buttons = {};

    protected JComboBox src_select, dest_select;
    private JTextField date_select;
    private JSpinner time_select;
    private JTextArea results_area;
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
    public void updateView(){
        this.removeAll();

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
        one_way_btn = new JButton("One-Way");
        two_way_btn = new JButton("Two-Way");
        one_way_btn.setActionCommand("one");
        two_way_btn.setActionCommand("two");
        one_way_btn.setEnabled(false);
        one_way_btn.setBackground(Color.BLUE);
        this.add(one_way_btn);
        this.add(two_way_btn);
        
        search_pane = new JPanel();
        src_select = new JComboBox(this.airports);
        dest_select = new JComboBox(this.airports);
        date_select = new JTextField(10);
        time_select = new JSpinner();
        results_area = new JTextArea();
        search_submit_button = new JButton("submit");

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
        content_pane = new JPanel();
        content_pane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        for(int i=0; i < flight_data.size(); i++){
            JPanel flight_panel = new JPanel();
            Map<String, String> data_map = new HashMap(flight_data.get(i));
            JLabel flight_id = new JLabel(data_map.get("flight_id"));
            JLabel airport_info = new JLabel(data_map.get("source") + " --> " + data_map.get("destination"));
            JLabel date_info = new JLabel(data_map.get("datetime"));
            JLabel price_info = new JLabel("$"+data_map.get("price"));
            JButton book_btn = new JButton("Book Flight");
            booking_buttons[i] = book_btn;
            flight_id.setBorder(empty_border);
            airport_info.setBorder(empty_border);
            date_info.setBorder(empty_border);
            price_info.setBorder(empty_border);
            book_btn.setBorder(empty_border);
            flight_panel.add(flight_id);
            flight_panel.add(airport_info);
            flight_panel.add(date_info);
            flight_panel.add(price_info);
            flight_panel.add(book_btn);
            flight_panel.setBorder(inner_border);
            content_pane.add(flight_panel, gbc);
            gbc.gridy++;
        }

        return content_pane;
    }

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
        for(int i=0; i<flight_data.size(); i++){
            this.results_area.append(flight_data.get(i).toString()+"\n");
        }
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

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
        this.results_area.setText(results);
    }

    protected GridBagConstraints getConstraints(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        return gbc;
    }
}

/**
    public static void setCalander(){..}
    /** Modifies the Containers to display items in a calander
     *  pre:  flight_list
     *  post: view_type = 'calendar'
     /

    public static void zoomCurrentView(){..}
    /** Changes the level depending on view_type, allows view to cycle between
     *  day, week, month.
     *  pre: view_type, zoom_level
     *  post: new zoom_level
     /

    public static void setList(){..}
    /**
     * Modifies the Container to displayitems in a List
     * pre: flight_list
     * post: view_type = 'list'
     /

    public static void displayHover(){..}
    /**
     * Creates a new SearchView for a subset of flight_list.
     * SearchView is of view_type 'list',
     * This view overlays over the original view and can easily be quit to return to original view.
     * pre: flight_list
     * post: none
     /
*/
