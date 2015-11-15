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
               if(card_view.visa.isSelected()) {
                   card_type = "Visa";
                   int countNum = 0;
                   int countCVV = 0;
                   for (int i = 0; i < card_num.length(); i++) {
                       if(Character.isDigit(card_num.charAt(i))) {
                           countNum++;
                       }
                   }
                   for (int i = 0; i < card_cvv.length(); i++) {
                       if(Character.isDigit(card_cvv.charAt(i))) {
                           countCVV++;
                       }
                   }
                   boolean check = false;
                   int month = Integer.parseInt(card_exp.substring(0,2));
                   int year = Integer.parseInt(card_exp.substring(3,5));
                   if (card_exp.matches("([0-1]{1})([0-9]{1})/([1-2]{1})([0-9]{1})"))
                   {
                       if(month > 13 || year < 15)
                       {
                           check = false;
                       }
                       else {
                           check = true;
                       }
                   }
                   if(((countNum == 13 && countCVV == 3) || (countNum == 16 && countCVV == 3)) && check) {
                        boolean success = card_model.addCardToDatabase(card_num, card_cvv, name_on_card, card_exp, card_type);
                        if(success) {
                             JOptionPane.showMessageDialog(card_view.card_pane, "Your Credit Card was added!");
                         }
                   }
                    else {
                       JOptionPane.showMessageDialog(card_view.card_pane, "Invalid Credit Card");
                   }
               }
               if (card_view.mastercard.isSelected()) {
                   card_type = "Mastercard";
                   int countNum = 0;
                   int countCVV = 0;
                   for (int i = 0; i < card_num.length(); i++) {
                       if(Character.isDigit(card_num.charAt(i))) {
                           countNum++;
                       }
                   }
                   for (int i = 0; i < card_cvv.length(); i++) {
                       if(Character.isDigit(card_cvv.charAt(i))) {
                           countCVV++;
                       }
                   }
                   boolean check = false;
                   int month = Integer.parseInt(card_exp.substring(0,2));
                   int year = Integer.parseInt(card_exp.substring(3,5));
                   if (card_exp.matches("([0-1]{1})([0-9]{1})/([1-2]{1})([0-9]{1})"))
                   {
                       if(month > 13 || year < 15)
                       {
                           check = false;
                       }
                       else {
                           check = true;
                       }
                   }
                   if(countNum == 16 && countCVV == 3 && check) {
                       boolean success = card_model.addCardToDatabase(card_num, card_cvv, name_on_card, card_exp, card_type);
                       if(success) {
                             JOptionPane.showMessageDialog(card_view.card_pane, "Your Credit Card was added!");
                        }
                   }
                    else {                  
                        JOptionPane.showMessageDialog(card_view.card_pane, "Invalid Credit Card");
                    }
               }
               if (card_view.amex.isSelected()) {
                   card_type = "Amex";
                   int countNum = 0;
                   int countCVV = 0;
                   for (int i = 0; i < card_num.length(); i++) {
                       if(Character.isDigit(card_num.charAt(i))) {
                           countNum++;
                       }
                   }
                   for (int i = 0; i < card_cvv.length(); i++) {
                       if(Character.isDigit(card_cvv.charAt(i))) {
                           countCVV++;
                       }
                   }
                   boolean check = false;
                   int month = Integer.parseInt(card_exp.substring(0,2));
                   int year = Integer.parseInt(card_exp.substring(3,5));
                   if (card_exp.matches("([0-1]{1})([0-9]{1})/([1-2]{1})([0-9]{1})"))
                   {
                       if(month > 13 || year < 15)
                       {
                           check = false;
                       }
                       else {
                           check = true;
                       }
                   }
                   if(countNum == 15 && countCVV == 4 && check) {
                       boolean success = card_model.addCardToDatabase(card_num, card_cvv, name_on_card, card_exp, card_type);
                       if(success) {
                             JOptionPane.showMessageDialog(card_view.card_pane, "Your Credit Card was added!");
                        }
                   }
                   else {
                        JOptionPane.showMessageDialog(card_view.card_pane, "Invalid Credit Card");
                   }
               }
               if (card_view.discover.isSelected()) {
                   card_type = "Discover";
                   int countNum = 0;
                   int countCVV = 0;
                   for (int i = 0; i < card_num.length(); i++) {
                       if(Character.isDigit(card_num.charAt(i))) {
                           countNum++;
                        }
                   }
                   for (int i = 0; i < card_cvv.length(); i++) {
                       if(Character.isDigit(card_cvv.charAt(i))) {
                           countCVV++;
                        }
                   }
                   boolean check = false;
                   int month = Integer.parseInt(card_exp.substring(0,2));
                   int year = Integer.parseInt(card_exp.substring(3,5));
                   if (card_exp.matches("([0-1]{1})([0-9]{1})/([1-2]{1})([0-9]{1})"))
                   {
                       if(month > 13 || year < 15)
                       {
                           check = false;
                       }
                       else {
                           check = true;
                       }
                   }
                   if(countNum == 16 && countCVV == 3 && check) {
                       boolean success = card_model.addCardToDatabase(card_num, card_cvv, name_on_card, card_exp, card_type);
                        if(success) {
                             JOptionPane.showMessageDialog(card_view.card_pane, "Your Credit Card was added!");
                        }
                   }
                    else {
                        JOptionPane.showMessageDialog(card_view.card_pane, "Invalid Credit Card");
                    }
               }
           }
       });
    }
}
