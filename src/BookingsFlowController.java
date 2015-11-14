import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class BookingsFlowController {
    BookingsFlowView bookings_view;
    Container parent_container;
    Travlr parent_frame;
    BookingModel booking_model;
    String[] locations;
    Map<String, String> individual_flight;
    CreditCardController card_control;
  

    /*******************************************************************
     * Name:    SearchFlowController()    :   Constructor               *
     * Purpose:                                                         *
     * Params:                                                          *
     * Postcondition:                                                   *
     *      SearchFlowController() object has been created.             *
     ********************************************************************/
    public BookingsFlowController(Travlr pframe, Container pcontain, FlightModel f1){
        parent_frame = pframe;
        parent_container = pcontain;
        booking_model = new BookingModel();
        bookings_view = new BookingsFlowView(f1);
        parent_container.add(bookings_view, bookings_view.getConstraints());
        addReturnControl();
        //card_control = new CreditCardController(parent_frame, pcontain);
        parent_container.revalidate();
        parent_container.repaint();
    }

    public BookingsFlowController(Travlr pframe, Container pcontain, FlightModel f1, FlightModel f2){
        parent_frame = pframe;
        parent_container = pcontain;
        booking_model = new BookingModel();
        bookings_view = new BookingsFlowView(f1, f2);
        parent_container.add(bookings_view, bookings_view.getConstraints());
        addReturnControl();
        card_control = new CreditCardController(parent_frame, pcontain);
        parent_container.revalidate();
        parent_container.repaint();
    }

    private void addReturnControl(){
        bookings_view.return_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                parent_frame.returnSearchFlow();

            }
        });
    }
    
    public void addCreditView() {
        
    }
}
