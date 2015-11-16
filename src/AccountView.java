import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class AccountView extends JPanel{
    String display;
    JPanel account_pane, login_pane, info_pane;
    JLabel first_name, last_name, email, user_name, password, conf_pass;
    JTextField fname_text, lname_text, email_text, user_text, login_user_input;
    JPasswordField password_text, confirm_pass, password_input;
    Font font1 = new Font("SansSerif", Font.BOLD, 20);
    GridBagConstraints gbc;
    protected JButton signup_btn, cancel_btn, login_btn;
    AccountModel acct_model;

    private Border empty_border = BorderFactory.createEmptyBorder(10,10,10,10);
    private Border border = BorderFactory.createLineBorder(Color.BLACK);
    private Border inner_border = BorderFactory.createCompoundBorder(
            empty_border,
            border);

    public AccountView()
    {
        this.display = "default";
        this.setLayout(new GridBagLayout());
        this.gbc = new GridBagConstraints();
    }
    
    protected void updateView(){
        this.removeAll();
        if (this.display == "default"){
            JLabel divider = new JLabel(" -- OR -- ");
            divider.setFont(font1);

            this.gbc.gridy = 0;
            this.add(generateCancelButton(), this.gbc);
            this.gbc.gridy = 1;
            this.add(loginView(), this.gbc);
            this.gbc.gridy = 2;
            this.add(divider, this.gbc);
            this.gbc.gridy = 3;
            this.add(signupView(), this.gbc);
        }
    }

    private JButton generateCancelButton(){
        cancel_btn = new JButton("Return to Search");
        cancel_btn.setFont(font1);
        return cancel_btn;
    }

    private JPanel loginView() {
        login_pane = new JPanel();
        login_pane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel login_user_label = new JLabel("Username: ");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,10,5,10);
        login_user_label.setFont(font1);
        login_pane.add(login_user_label, gbc);

        login_user_input = new JTextField(15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(5, 80, 5, 10);
        login_user_input.setFont(font1);
        login_pane.add(login_user_input, gbc);

        JLabel password_label = new JLabel("Password: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5,10,5,10);
        password_label.setFont(font1);
        login_pane.add(password_label, gbc);

        password_input = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(5,80,5,10);
        password_input.setFont(font1);
        login_pane.add(password_input, gbc);

        login_btn = new JButton("Login");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = (new Insets(5,80,5,10));
        login_btn.setFont(font1);
        login_pane.add(login_btn, gbc);

        login_pane.setBorder(inner_border);

        return login_pane;
    }

    private JPanel signupView() {
        account_pane = new JPanel();
        Font font1 = new Font("SansSerif", Font.BOLD,20);
        account_pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        first_name = new JLabel("First Name:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = (new Insets(5,10,5,10));
        first_name.setFont(font1);
        account_pane.add(first_name, c);
      
        fname_text = new JTextField(15);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 4;
        c.insets = (new Insets(5,80,5,10));
        fname_text.setFont(font1);
        account_pane.add(fname_text, c);
        
        last_name = new JLabel("Last Name:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = (new Insets(5,10,5,10));
        last_name.setFont(font1);
        account_pane.add(last_name, c);
        
        lname_text = new JTextField(15);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 4;
        c.insets = (new Insets(5,80,5,10));
        lname_text.setFont(font1);
        account_pane.add(lname_text, c);
        
        email = new JLabel("Email:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = (new Insets(5,10,5,10));
        email.setFont(font1);
        account_pane.add(email, c);
        
        email_text = new JTextField(15);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 4;
        c.insets = (new Insets(5,80,5,10));
        email_text.setFont(font1);
        account_pane.add(email_text, c);
        
        user_name = new JLabel("Username:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = (new Insets(5,10,5,10));
        user_name.setFont(font1);
        account_pane.add(user_name, c);
        
        user_text = new JTextField(15);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 4;
        c.insets = (new Insets(5,80,5,10));
        user_text.setFont(font1);
        account_pane.add(user_text, c);
        
        password = new JLabel("Password:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = (new Insets(5,10,5,10));
        password.setFont(font1);
        account_pane.add(password, c);
        
        password_text = new JPasswordField(15);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 4;
        c.insets = (new Insets(5,80,5,10));
        password_text.setFont(font1);
        account_pane.add(password_text, c);
        
        conf_pass = new JLabel("Confirm Password:");
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        c.insets = (new Insets(5,10,5,10));
        conf_pass.setFont(font1);
        account_pane.add(conf_pass, c);
        
        confirm_pass = new JPasswordField();
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 4;
        c.insets = (new Insets(5,80,5,10));
        confirm_pass.setFont(font1);
        account_pane.add(confirm_pass, c);
        
        signup_btn = new JButton("Sign-Up");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 2;
        c.insets = (new Insets(5,80,5,10));
        signup_btn.setFont(font1);
        account_pane.add(signup_btn, c);
        
        account_pane.setBorder(inner_border);

        return account_pane;
    }
    
    protected JPanel accountInfo() {
        info_pane = new JPanel();
        acct_model = new AccountModel();
        JTable table = new JTable();
        String query_string = ("select * from accounts");
        ResultSet rs = acct_model.queryDB(query_string);
        ArrayList columnNames = new ArrayList();
        try {
            while(rs.next()) {
                
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return info_pane;
    }
    
}
