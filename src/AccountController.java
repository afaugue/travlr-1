import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AccountController {
    AccountView account_view;
    Container parent_container;
    Travlr parent_frame;
    AccountModel account_model;
    String fname, lname, email, user, pass, conf_pass;
    CreditCardController card_control;
    SearchFlowController search_control;
    
    /*******************************************************************
    * Name:    AccountController()    :   Constructor               *
    * Purpose:                                                         *
    * Params:                                                          *
    * Postcondition:                                                   *
    *      AccountController() object has been created.             *
    ********************************************************************/
    
    public AccountController(Travlr pframe, Container pcontain){
        parent_frame = pframe;
        parent_container = pcontain;
        account_model = new AccountModel();
        account_view = new AccountView();
        account_view.updateView();
        parent_container.add(account_view);
        addAccountControls();
    }
    
    public void addAccountControls() {
        account_view.submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                fname = account_view.fname_text.getText();
                lname = account_view.lname_text.getText();
                email = account_view.email_text.getText();
                user = account_view.user_text.getText();
                pass = account_view.password_text.getText();
                conf_pass = account_view.confirm_pass.getText();
                boolean check = true;
                if(!email.contains("@")) {
                    JOptionPane.showMessageDialog(account_view.account_pane, "Invalid email address");
                    check = false;
                }
                if(!pass.equals(conf_pass)) {
                    JOptionPane.showMessageDialog(account_view.account_pane, "Passwords do not match");
                    check = false;
                }
                if(check){
                    boolean success = account_model.addAccountToDatabase(fname, lname, email, user, pass);
                    if(success) {
                        JOptionPane.showMessageDialog(account_view.account_pane, "Thank You for Registering!");
                        if(parent_frame.search_flow == null) {
                            parent_frame.startSearchFlow();
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(account_view.account_pane, "Username already exists");
                    }
                }
            }  
        });
        
        account_view.cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(parent_frame.search_flow == null) {
                       parent_frame.startSearchFlow();
                } else {
                       parent_frame.returnSearchFlow();
                }
                parent_frame.account_flow = null;
            }
        });


    }
}
