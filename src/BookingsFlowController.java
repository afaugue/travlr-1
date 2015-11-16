import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingsFlowController {
    BookingsFlowView bookings_view;
    Container parent_container;
    Travlr parent_frame;
    BookingModel booking_model;
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
        addReturnControl();
        addContinueControl();
        //card_control = new CreditCardController(parent_frame, pcontain);
        parent_container.revalidate();
        parent_container.repaint();
    }

    public BookingsFlowController(Travlr pframe, Container pcontain, FlightModel f1, FlightModel f2){
        parent_frame = pframe;
        parent_container = pcontain;
        booking_model = new BookingModel();
        bookings_view = new BookingsFlowView(f1, f2);
        addReturnControl();
        //card_control = new CreditCardController(parent_frame, pcontain);
    }

    private void addContinueControl(){
        bookings_view.continue_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (bookings_view.getBookingState() == 2){
                /*    booking_model.setSeatNumbers(bookings_view.getSeatNumbers());
                    booking_model.setBags(bookings_view.getBags());
                    booking_model.setAncillaries(bookings_view.getAncillaries());*/
                    booking_model.setBookingId(booking_model.reserveBookingID());
                    booking_model.buildBookingInsert();
                } else if (bookings_view.getBookingState() ==3){
                    booking_model.setName(bookings_view.info_panel.getFName().getText()+" "+
                                          bookings_view.info_panel.getMI().getText()+" "+
                                          bookings_view.info_panel.getLName().getText());
                    booking_model.setDOB(bookings_view.info_panel.getDOB_Month().getSelectedItem().toString()+"/"+
                                         bookings_view.info_panel.getDOB_Day().getSelectedItem().toString()+"/"+
                                         bookings_view.info_panel.getDOB_Year().getSelectedItem().toString());
                    if (bookings_view.info_panel.getGender_Male().isCursorSet()){
                        booking_model.setGender("Male");
                    } else {
                        booking_model.setGender("Female");
                    }
                    booking_model.setAddress(bookings_view.info_panel.getAddress1().getText());
                    booking_model.setState(bookings_view.info_panel.getState().getSelectedItem().toString());
                    booking_model.setCity(bookings_view.info_panel.getCity().getText());
                    booking_model.setZip(bookings_view.info_panel.getZip().getText());
                    booking_model.setEmail(bookings_view.info_panel.getEmail().getText());
                    booking_model.setPhone(bookings_view.info_panel.getPhone().getText());

                    booking_model.setPersonalInfoId(booking_model.reservePersonalInfoID());
                    booking_model.buildPersonalInsert();
                    booking_model.setBookingsPersonalFKID(booking_model.reserveBookingsPersonalFK());
                    booking_model.buildBookingsPersonalFKInsert();

                    if (parent_frame.active_account_session){
                        bookings_view.addPaymentToAccount();
                    }
                    bookings_view.addPaymentToBooking(booking_model.booking_id);
                    //booking_model.executePersonalInfoInsert();
                    //booking_model.executeBookingInsert();
                }
                bookings_view.setBookingState(bookings_view.getBookingState() + 1);
                bookings_view.updateView();
                bookings_view.revalidate();
                bookings_view.repaint();
            }
        });
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
