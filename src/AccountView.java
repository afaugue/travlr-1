import javax.swing.*;
import java.awt.*;

public class AccountView extends JPanel{
    String display;
    JPanel account_pane;
    JLabel first_name, last_name, email, user_name, password, conf_pass;
    JTextField fname_text, lname_text, email_text, user_text;
    JPasswordField password_text, confirm_pass;
    protected JButton submit, cancel;
    
    public AccountView()
    {
        this.display = "default";
    }
    
    protected void updateView(){
        this.removeAll();
        if (this.display == "default"){
            this.add(acctView());
        }
    }
    
    private JPanel acctView() {
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
        
        submit = new JButton("Sign-Up");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.insets = (new Insets(5,10,5,10));
        submit.setFont(font1);
        account_pane.add(submit, c);  
        
        cancel = new JButton("Cancel");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 6;
        c.gridwidth = 2;
        c.insets = (new Insets(5,10,5,10));
        cancel.setFont(font1);
        account_pane.add(cancel, c);
        
        return account_pane;
    }
    
}
