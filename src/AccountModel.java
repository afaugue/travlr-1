import java.sql.*;
/**
 * Description:
 *      Account objects can be created for users. These objects
 *      allow the application to keep user information on record.
 *      These accounts also allow for the accumulation of Booking object
 *      to allow user to get information about their purchases. CreditCard
 *      objects will also be stored in order for a customer to save
 *      a CreditCard on Account for quick future purchases.
 *
 */

public class AccountModel {
    String firstName;
    String lastName;
    String email;
    String username;
    String password;
    String login;
    String pass;
    int account_id;
    float rewardMiles;
    
    public AccountModel(){}
    

    static void addBooking(){}
    static BookingModel[] getBookings(){
        BookingModel[] books = {};
        return books;
    }
    static void addCreditCard(){}
    static CreditCardModel[] getCreditCards(){
        CreditCardModel[] cards = {};
        return cards;
    }
    static void setRewards(){}
    static float getRewards(){ return 0; }
    
    //** Calculates rewards points from all Bookings Items */
    static void updateRewards(){}
        //** foreach booking in getBookings()                       /*/
        //**         booking.getFlight()                            */
        //**         setRewards( getRewards() + flight.getMiles()   */
        
    static void clearRewards(){}
        //**         setRewards( 0.0 )   */
        
    
    static void paymentOnStoredCard(String four_digits, float pay_amnt){}
        //** find card with four_digits */
    
     //**Attempts to establishes an Account object using input credentials */
    AccountModel loginToAccount(){
        return new AccountModel();
    }
        //** query_result = queryDB( select * where user=username           */
        //**                                    and pass=password )         */
        //** return Account( query_result )                                 */
    
    static void addRewardsFromFlight(){}
    
	/*
	 * Reward program incorperated with miles 
	 * From 0 - 10,000 miles = regular member
	 * from 10,001 - 100,000 miles = gold member
	 * from 100,001 and greater = platinum member
	 */	
    
    public boolean checkLogin(String login, String pass) {
        this.login = login;
        this.pass = pass;
        boolean check = false;
        String query_string = ("select * from accounts where username = '"+login+"' and password = '"+pass+"';");
        ResultSet result_set = queryDB(query_string);
        try {
            while(result_set.next()) {
                check = true;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
    
    public boolean addAccountToDatabase(String firstName, String lastName, String email, String username, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        String query_string = ("select * from accounts where username = '"+username+"';");
        ResultSet result_set = queryDB(query_string);
        boolean status = handleInsertQuery(result_set);
        return status;
    }
    
    protected ResultSet queryDB(String query_string) {
        Connection connection = null;
        ResultSet result_set = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:db/travlr.db");
            statement = connection.createStatement();
            
            result_set = statement.executeQuery(query_string);

            return result_set;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                result_set.close();
                statement.close();
                connection.close();
            } catch (Exception f) {
                f.printStackTrace();
            }
        }
        return result_set;
    }

    private void insertDB(String insert_string) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:db/travlr.db");
            statement = connection.createStatement();

            statement.executeUpdate(insert_string);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                statement.close();
                connection.close();
            } catch (Exception f) {
                f.printStackTrace();
            }
        }
    }

    public boolean handleInsertQuery(ResultSet rs) {
        try {
            while(rs.next()) {
                System.out.println(username);
                return false;
            }
            
            String insert_string= ( "insert into accounts (fname, lname, email, username, password, reward_miles)"+
                                    " VALUES ('"+firstName+"', '"+lastName+"', '"+email+"', '"+username+"', '"+
                                    password+"', 0)");
            insertDB(insert_string);
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                rs.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    
    public static void deleteAccount(){}

    public int getAccountID() {
        return account_id;
    }

    public void setAccountID(int account_id) {
        this.account_id = account_id;
    }
}