import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/****************************************************************************
* Name:    SearchContoller
* Purpose:  The search controller will handle any user interaction
* within the SearchFlow component of the application. This will involve
* interactions with Search and Flight objects.  SearchController will
* create a Basic search object from inputs on the SearchFlowView to build
* a query, collect Flight objects from the DB and pass those Flight objects
* back to the user trough the SearchFlowView formatting.
*
******************************************************************************/
public class SearchFlowController {
    SearchFlowView search_view;
    Container parent_container;
    Travlr parent_frame;
    SearchModel search_model;
    String[] locations;
    ArrayList<Map> flight_data;
    Boolean needs_return_flight = false;

    /*******************************************************************
    * Name:    SearchFlowController()    :   Constructor               *
    * Purpose:                                                         *
    * Params:                                                          *
    * Postcondition:                                                   *
    *      SearchFlowController() object has been created.             *
    ********************************************************************/
    public SearchFlowController(Travlr pframe, Container pcontain ){
        parent_frame = pframe;
        parent_container = pcontain;
        search_model = new SearchModel();
        locations = search_model.getAllAirports();
        search_view = new SearchFlowView();
        search_view.setAirports(locations);
        search_view.updateView();
        addSearchControls();
        parent_container.add(search_view, search_view.getConstraints());
    }

    public void addSearchControls() {
        search_view.one_way_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                toggleReturnFlight(actionEvent.getActionCommand());
            }
        });

        search_view.two_way_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                toggleReturnFlight(actionEvent.getActionCommand());
            }
        });

        search_view.search_submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performBasicSearch();
            }
        });

    }

    private void addBookingControls(){
        for (int i = 0; i < search_view.booking_buttons.length; i++){
            final int j = i;
            if (search_view.booking_buttons[i] != null) {
                search_view.booking_buttons[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        parent_frame.startBookingsFlow(search_view.getFlightData().get(j));
                    }
                });
            }
        }
    }

    private String pullShortName(String full_text){
        String pattern = ".*\\((\\w+)\\)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(full_text);
        if (m.find()){
            return m.group(1);
        } else {
            return full_text;
        }
    }

    private void toggleReturnFlight(String btn_value){
        if ("two".equals(btn_value)){
            needs_return_flight = true;
            search_view.one_way_btn.setEnabled(true);
            search_view.two_way_btn.setEnabled(false);
            search_view.two_way_btn.setBackground(Color.BLUE);
            search_view.one_way_btn.setBackground(null);
        } else {
            needs_return_flight = false;
            search_view.one_way_btn.setEnabled(false);
            search_view.two_way_btn.setEnabled(true);
            search_view.one_way_btn.setBackground(Color.BLUE);
            search_view.two_way_btn.setBackground(null);
        }
        System.out.print(needs_return_flight);
    }

    private void performBasicSearch(){
        System.out.println("Performing Basic Search");
        String src = pullShortName(search_view.getSrc());
        String dest = pullShortName(search_view.getDest());
        String date = search_view.getDate();
        String time = search_view.getTime();
        search_model = new SearchModel(src, dest, date, time);
        flight_data = search_model.getFlightData();
        search_view.setFlightData(flight_data);
        search_view.setDisplay("list");
        addBookingControls();
    }
}