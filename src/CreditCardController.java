import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CreditCardController {
    CreditCardView card_view;
    Container parent_container;
    Travlr parent_frame;
    CreditCardModel card_model;
    String card_num, card_cvv, name_on_card, card_exp, card_type;
    
    /*******************************************************************
    * Name:    CreditCardController()    :   Constructor               *
    * Purpose:                                                         *
    * Params:                                                          *
    * Postcondition:                                                   *
    *      CreditCardController() object has been created.             *
    ********************************************************************/
    
    public CreditCardController(Travlr pframe, Container pcontain) {
        parent_frame = pframe;
        parent_container = pcontain;
        card_model = new CreditCardModel();
        card_view = new CreditCardView();
        card_view.updateView();
        parent_container.add(card_view);
        addCreditCardControls();
        
    }
    
    public void addCreditCardControls() {
       card_view.submit.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent actionEvent) {
               card_num = card_view.cardNum.getText();
               card_cvv = card_view.cardCVV.getText();
               name_on_card = card_view.nameCard.getText();
               card_exp = card_view.cardExp.getText();
               if(card_view.visa.isSelected())
                   card_type = "Visa";
               else if (card_view.mastercard.isSelected())
                   card_type = "Mastercard";
               else if (card_view.amex.isSelected())
                   card_type = "Amex";
               else if (card_view.discover.isSelected())
                   card_type = "Discover";
               boolean success = card_model.addCardToDatabase(card_num, card_cvv, name_on_card, card_exp, card_type);
               if(success) {
                    JOptionPane.showMessageDialog(card_view.card_pane, "Your Credit Card was added!");
                }
                else {
                    JOptionPane.showMessageDialog(card_view.card_pane, "Card Number already exists");
                }
           }
       });
    }
}
