import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AccountController {
    AccountView account_view;
    Container parent_container;
    Travlr parent_frame;
    AccountModel account_model;
    String fname, lname, email, user, pass, conf_pass, login, password;
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
        account_view.signup_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                fname = account_view.fname_text.getText();
                lname = account_view.lname_text.getText();
                email = account_view.email_text.getText();
                user = account_view.user_text.getText();
                pass = account_view.password_text.getText();
                conf_pass = account_view.confirm_pass.getText();
                boolean check = true;
                if(!email.contains("@") && !email.contains(".")) {
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
                parent_frame.startAccountFlow();
            }  
        });
        
        account_view.cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(parent_frame.search_flow == null) {
                       parent_frame.startSearchFlow();
                } else {
                       parent_frame.returnSearchFlow();
                }
                parent_frame.setAccountSessionStatus(false);
                parent_frame.account_flow = null;
            }
        });

        account_view.login_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                login = account_view.login_user_input.getText();
                password = account_view.password_input.getText();
                boolean success = account_model.checkLogin(login, password);
                if(success) {
                    account_model = new AccountModel(account_view.login_user_input.getText());
                    parent_frame.returnSearchFlow();
                    JOptionPane.showMessageDialog(account_view.login_pane, "Welcome back " + login);
                    parent_frame.setAccountSessionStatus(true);
                    parent_frame.logo_panel.removeAll();
                    parent_frame.logo_panel.addAccountButton(success);
                    parent_frame.logo_panel.addAccountButtonControls();
                    parent_frame.logo_panel.revalidate();
                    parent_frame.logo_panel.repaint();
                    parent_frame.returnSearchFlow();
                }
                else {
                    JOptionPane.showMessageDialog(account_view.login_pane, "Invalid Login");
                }
            }
        });

    }
}