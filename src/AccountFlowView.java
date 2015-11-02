import javax.swing.*;

/**
 * Name:    AccountFlowView
 * Description:
 *      AccountFlowView is responsible for displaying User Interface
 *      during the AccountFlow component of the application. The AccountFlow
 *      component will allow the user to see their Account information and
 *      Bookings attached to their account. The AccountFlowView will be responsible
 *      for allowing user interaction with the AccountFlowController which authorize
 *      connection to an Account object and allow retrieval of Account object data.
 *
 *      From this list AccountView will be able to view Account info.
 *      AccountView will be able to look at Booking info in a variety of view.
 *      These views include a Calander layout and List layout.
 *
 *
 */
public class AccountFlowView extends JPanel {
    public Account[] account;
    public String view_type;
    public int zoom_level;

    public AccountFlowView(){}

    public static void setCalander(){}
    /** Modifies the Containers to display items in a calander
     * Users will be able to view their search results in a calendar view
     *  pre:  flight_list, searchController
     *  post: view_type = 'calendar'
     */

    public static void zoomCurrentView(){}
    /** Changes the level depending on view_type, allows view to cycle between
     *  day, week, month.
     *  pre: view_type, zoom_level
     *  post: new zoom_level
     */

    public static void setList(){}
    /**
     * Modifies the Container to displayitems in a List
     * Users will be able to view their search results in a list view
     * pre: flight_list, searchContoller
     * post: view_type = 'list'
     */

    public static void displayHover(){}
    /**
     * Creates a new AccountView for a subset of flight_list.
     * AccountView is of view_type 'list',
     * This view overlays over the original view and can easily be quit to return to original view.
     * pre: flight_list
     * post: none
     */

}
