import javax.swing.*;
import java.awt.*;
import java.awt.print.Book;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/***********************************************************************
* Name:    Travlr      :   Class                                       *
* Purpose:
************************************************************************/
public class Travlr extends JFrame {
    static ImagePanel logo_panel;
    static JPanel image_panel;
    static Container middle_view, frame_content;
    static Travlr main_frame;
    static SearchFlowController search_flow;
    static BookingsFlowController bookings_flow;
    static AccountController account_flow;

    static boolean active_account_session = false;

    /*******************************************************************
    * Name:     Travlr()   :   Constructor                             *

    ********************************************************************/
    public Travlr(){this.setTitle("Travlr");}

    /*******************************************************************
    * Name:     runApplication()   :   Method                          *
    * Purpose:                                                         *
    ********************************************************************/
    private static void runApplication(){
        main_frame = new Travlr();
        main_frame.setDefaults();

        frame_content = main_frame.getContentPane();
        frame_content.setLayout(new GridBagLayout());

        logo_panel = new ImagePanel(Travlr.class.getResource("images/logo.png"));
        logo_panel.addAccountButton(active_account_session);
        logo_panel.addAccountButtonControls();
        frame_content.add(logo_panel, getBannerConstraints());

        startSearchFlow();

        //frame_content.removeAll();
        //account_flow = new AccountController(main_frame,frame_content);

        image_panel = new IconPanel();/*new JPanel();
        ImagePanel image1 = new ImagePanel(Travlr.class.getResource("images/0.jpg"));
        ImagePanel image2 = new ImagePanel(Travlr.class.getResource("images/1.jpg"));
        ImagePanel image3 = new ImagePanel(Travlr.class.getResource("images/2.jpg"));
        ImagePanel image4 = new ImagePanel(Travlr.class.getResource("images/3.jpg"));
        image_panel.setLayout(new GridBagLayout());
        image_panel.add(image1, getFooterImageConstraints());
        image_panel.add(image2, getFooterImageConstraints());
        image_panel.add(image3, getFooterImageConstraints());
        image_panel.add(image4, getFooterImageConstraints());*/
        frame_content.add(image_panel,getFooterConstraints());

        main_frame.showFrame();
    }

    public static void startSearchFlow(){
        search_flow = new SearchFlowController(main_frame, frame_content);
        frame_content.add(search_flow.search_view, getViewContstraints());
        //main_frame.setSize(new Dimension(800,800));
        //main_frame.setMinimumSize(new Dimension(800,800));
        main_frame.revalidate();
        main_frame.repaint();
    }

    public void returnSearchFlow(){
        frame_content.remove(1);
        frame_content.add(search_flow.search_view, getViewContstraints());
        frame_content.validate();
        frame_content.repaint();
    }

    public void startBookingsFlow(FlightModel f1){
        frame_content.remove(search_flow.search_view);
        bookings_flow = new BookingsFlowController(main_frame, frame_content, f1);
        frame_content.add(bookings_flow.bookings_view, getViewContstraints());
        frame_content.revalidate();
        frame_content.repaint();
    }

    public void startBookingsFlow(FlightModel f1, FlightModel f2) {
        frame_content.remove(search_flow.search_view);
        bookings_flow = new BookingsFlowController(main_frame, frame_content, f1, f2);
        frame_content.add(bookings_flow.bookings_view, getViewContstraints());
        frame_content.revalidate();
        frame_content.repaint();
    }

    public void startAccountFlow(){
        for (int i=1; i<frame_content.getComponentCount(); i++){
            frame_content.remove(i);
        }
        account_flow = new AccountController(main_frame, frame_content);
        frame_content.add(account_flow.account_view, getViewContstraints());
        frame_content.revalidate();
        frame_content.repaint();
    }


    private static GridBagConstraints getBannerConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.15;
        return gbc;
    }

    protected static GridBagConstraints getViewContstraints(){
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.7;
        return gbc;
    }

    protected static GridBagConstraints getFooterConstraints(){
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.15;
        return gbc;
    }

    protected static GridBagConstraints getFooterImageConstraints(){
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.weightx = 0.25;
        gbc.weighty = 1.0;
        return gbc;
    }

    /*******************************************************************
    * Name:    setDefaults()   :   Method                              *
    * Purpose:                                                         *
    ********************************************************************/
    public void setDefaults(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 800));
        JPanel root = new JPanel();
        root.setLayout(new GridBagLayout());
        this.setContentPane(root);
    }

    /*******************************************************************
    * Name:    showFrame()  :   Method                                 *
    * Purpose:                                                         *
    *******************************************************************/
    public void showFrame(){
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    protected boolean getAccountSessionStatus(){
        return active_account_session;
    }

    protected void setAccountSessionStatus(boolean new_val){
        active_account_session = new_val;
    }

    /*******************************************************************
    * Name:    main()  :   Method                                      *
    * Purpose:                                                         *
    ********************************************************************/
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Travlr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Travlr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Travlr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Travlr.class.getName()).log(Level.SEVERE, null, ex);
        }
        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run(){
                runApplication();
            }
        });
    }
}
