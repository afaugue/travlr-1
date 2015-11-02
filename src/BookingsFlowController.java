import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by lbontecou on 10/26/15.
 */
public class BookingsFlowController {
    BookingsFlowView bookings_view;
    Container parent_container;
    Travlr parent_frame;
    BookingModel booking_model;
    String[] locations;
    Map<String, String> individual_flight;

    /*******************************************************************
     * Name:    SearchFlowController()    :   Constructor               *
     * Purpose:                                                         *
     * Params:                                                          *
     * Postcondition:                                                   *
     *      SearchFlowController() object has been created.             *
     ********************************************************************/
    public BookingsFlowController(Travlr pframe, Container pcontain, Map<String, String> fdata){
        this.individual_flight = fdata;
        parent_frame = pframe;
        parent_container = pcontain;
        booking_model = new BookingModel();
        bookings_view = new BookingsFlowView();
        parent_container.add(bookings_view, bookings_view.getConstraints());
        bookings_view.setFlightData(this.individual_flight);
        bookings_view.updateView();
        addControls();
    }

    private void addControls(){
        bookings_view.return_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                parent_frame.returnSearchFlow();

            }
        });
    }
}
