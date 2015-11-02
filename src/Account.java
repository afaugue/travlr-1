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

public class Account {
    String firstName;
    String lastName;
    String phoneNum;
    String address;
    String email;
    String username;
    String password;
    String dob;
    BookingModel[] listOfBookings;
    CreditCard[] cardInfo;
    float rewardMiles;
    
    //** Account Construstors TBD
    protected Account(){}

    //** Getter/Setter/Add funcions for Account
    static void setFirstName(){}
    static String getFirstName(){ return ""; }
    static void setLastName(){}
    static String getLastName(){ return ""; }
    static void setPhoneNum(){}
    static String getPhoneNum(){ return ""; }
    static void setAddress(){}
    static String getAddress(){ return ""; }
    static void setEmail(){}
    static String getEmail(){ return ""; }
    static void setUsername(){}
    static String getUsername(){ return ""; }
    static void setPassword(){}
    static String getPassword(){ return ""; }
    static void setDOB(){}
    static String getDOB(){ return ""; }
    static void addBooking(){}
    static BookingModel[] getBookings(){
        BookingModel[] books = {};
        return books;
    }
    static void addCreditCard(){}
    static CreditCard[] getCreditCards(){
        CreditCard[] cards = {};
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
        
    //** Enters or Updates entry for this Account in database */
    static void updateDatabase(){}
        //** if not inDB() -> queryDB( insert <all fields> where            */ 
        //**                                user=username and               */
        //**                                pass=password)                  */ 
        //** else queryDB( insert <all fields> )                            */
    
    //** Removes this Account if it exists in database. */
    static void removeDatabase(){}
        //** if inDB() -> queryDB( delete <all fields> where                */ 
        //**                                user=username and               */
        //**                                pass=password)                  */
        //** else RemoveException()                                         */
    
    static void paymentOnStoredCard(String four_digits, float pay_amnt){}
        //** find card with four_digits */
    
     //**Attempts to establishes an Account object using input credentials */
    Account loginToAccount(){
        return new Account();
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
    
    public static void addAccountToDatabase(){}
	/*
	 * Adds user information to the database
	 */
    
    public static void deleteAccount(){}
    /*
	 * User will be able to delete account from database
	 */
}