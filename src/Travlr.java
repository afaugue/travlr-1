import javax.swing.*;
import java.awt.*;
import java.util.Map;

/***********************************************************************
* Name:    Travlr      :   Class                                       *
* Purpose:
************************************************************************/
public class Travlr extends JFrame {
    static Container middle_view, frame_content;
    static Travlr main_frame;
    static SearchFlowController search_flow;
    static BookingsFlowController bookings_flow;
    static AccountController account_flow;
    /*******************************************************************
    * Name:     Travlr()   :   Constructor                             *
    * Purpose:  Initializes Application Frame.                         *
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

        startSearchFlow();

        //frame_content.removeAll();
        //account_flow = new AccountController(main_frame,frame_content);
        main_frame.showFrame();
    }

    public static void startSearchFlow(){
        frame_content.removeAll();
        search_flow = new SearchFlowController(main_frame, frame_content);
        main_frame.setSize(new Dimension(800,800));
        main_frame.setMinimumSize(new Dimension(800,800));
        main_frame.revalidate();
        main_frame.repaint();
    }

    public void returnSearchFlow(){
        frame_content.removeAll();
        frame_content.add(search_flow.search_view,search_flow.search_view.getConstraints());
        frame_content.validate();
        frame_content.repaint();
    }

    public void startBookingsFlow(Map<String, String> flight_data){
        frame_content.removeAll();
        bookings_flow = new BookingsFlowController(main_frame, frame_content,
                flight_data);
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

    /*******************************************************************
    * Name:    main()  :   Method                                      *
    * Purpose:                                                         *
    ********************************************************************/
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run(){
                runApplication();
            }
        });
    }
}
