import com.sun.xml.internal.ws.util.StringUtils;

import java.sql.*;

/**#######################################################################
 * # Class Name:    Booking
 * # Descriptions:  Booking object contains all necessary data relavent to a booking. 
 *     				It creates the connection between the Flight, a (optional) Users Account,
 *        		    and all (optional) Ancillaries
 *
 *  Invariants:
 *                  Credit card is declined.
 *
 *
 */
public class BookingModel {
    String fullname, dob, gender, address, state, city, zip, email, phone;
    int account_id, personal_info_id, booking_id, ancillaries_id;
    int bookings_personal_fk_id;
    int[] flight_ids;
    Ancillary[] ancillaries;
    String[] seat_numbers;
    int bags;

    private String bookings_insert, personal_info_insert, booking_personal_fk_insert;
    private String booking_flights_fk_insert, booking_account_fk_insert;

    public BookingModel() {}


    protected static void addAccount(AccountModel acc){}
    /**
     * Adds the Account to the Booking
     */
    
    protected static void addFlight(FlightModel flight){}
    /**
     * Adds the Flight to the Booking. 
     */
    
    protected static void addAncillary(Ancillary anc){}
    /**
     * Adds an Ancillary object to the booking.
     */
    
    protected static void calculateTotalPrice(FlightModel f, Ancillary[] a){}
    /**
     * Iterates through the objects connected to the Booking in order to determine 
     * prices for each component. Component prices are then added together and 
     * set to the total_price of Bookings object. Then will promt the user for 
	 * credit card infomation.
	 * Precondtions: Credit card infomation is valid.
	 * Postconditions: Charge credit card.
     */

    protected void buildBookingInsert(){
        this.bookings_insert = ("update bookings set seat_numbers='1A,2B', bags=2, ancillary_pkg_id=1"+
                " where id="+Integer.toString(this.booking_id)+";");
        System.out.println(this.bookings_insert);
        this.insertDB(this.bookings_insert);
    }


    protected int reserveBookingID(){
        String insert_string = ("insert into bookings(seat_numbers, bags, ancillary_pkg_id) VALUES (null, null, null);");
        this.insertDB(insert_string);
        String query_string = ("select max(id) from bookings;");
        ResultSet rs = queryDB(query_string);
        int id = Integer.parseInt(handleReserveInsert(rs));
        return id;
    }

    protected void buildPersonalInsert(){
        this.personal_info_insert = ("update personal_info set fullname='"+this.fullname+"', DOB='"+this.dob+"', gender"+
                "='"+this.gender+"', address1='"+this.address+"', city='"+this.city+"', state='"+this.state+"', "+
                "zipcode='"+this.zip+"', phone='"+this.phone + "', email='" + this.email + "' "+
                "where id="+Integer.toString(this.personal_info_id)+";");
        System.out.println(this.personal_info_insert);
        this.insertDB(this.personal_info_insert);
    }

    protected int reservePersonalInfoID(){
        System.out.println(fullname+" : "+dob+" : "+gender+" : "+address+" : "+state+" : "+city+" : "+zip+" : "+email+" : "+phone);
        String insert_string = ("insert into personal_info"+
                                "(fullname, DOB, gender, address1, address2, city, state, zipcode, phone, email) "+
                                "VALUES (null, null, null, null, null, null, null, null, null, null);");
        this.insertDB(insert_string);
        String query_string = ("select max(id) from personal_info;");
        ResultSet rs = queryDB(query_string);
        int id = Integer.parseInt(handleReserveInsert(rs));
        return id;
    }

    protected void buildBookingsPersonalFKInsert(){
        this.booking_personal_fk_insert = ("update bookings_personalinfo set booking_id="+Integer.toString(this.booking_id)+
                ", personal_info_id="+Integer.toString(this.personal_info_id) +
                " where id="+Integer.toString(this.bookings_personal_fk_id)+";");
        System.out.println(this.booking_personal_fk_insert);
        this.insertDB(this.booking_personal_fk_insert);

    }
    protected int reserveBookingsPersonalFK(){
        String insert_string = ("insert into bookings_personalinfo(personal_info_id, booking_id) VALUES (null, null);");
        insertDB(insert_string);
        String query_string = ("select max(id) from bookings_personalinfo;");
        ResultSet rs = queryDB(query_string);
        int id = Integer.parseInt(handleReserveInsert(rs));
        return id;
    }


    protected String retrieveOutput(ResultSet rs){
        return rs.toString();
    }
    protected static void cancelBooking(){}
	/*
	 * Users will be able to cancel their selected booking
	 * under the right circumstances, users will be able to cancel flight and get full refund
	 */


    /********************************************************************************
     * Query Handlers                                                               *
     * Purpose:     These methods extract and return data from various ResultSets.  *
     ********************************************************************************/

    private String handleReserveInsert(ResultSet result_set) {

        try{
            while (result_set.next()) {
                return result_set.getObject(1).toString();
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                result_set.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /****************************************************************************
     * Name:    queryDB()                                                       *
     * Purpose: Utility Method to connect to SQLite Databse, returns ResultSet. *
     ****************************************************************************/
    private ResultSet queryDB(String query_string) {
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
        return null;
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

    public String getName() {
        return fullname;
    }

    public void setName(String fullname) {
        this.fullname = fullname;
    }

    public String getDOB() {
        return dob;
    }

    public void setDOB(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAccountId() {
        return account_id;
    }

    public void setAccountId(int account_id) {
        this.account_id = account_id;
    }

    public int getPersonalInfoId() {
        return personal_info_id;
    }

    public void setPersonalInfoId(int personal_info_id) {
        this.personal_info_id = personal_info_id;
    }

    public int getBookingId() {
        return booking_id;
    }

    public void setBookingId(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getBookingsPersonalFKID() {
        return bookings_personal_fk_id;
    }

    public void setBookingsPersonalFKID(int bookings_personal_fk_id) {
        this.bookings_personal_fk_id = bookings_personal_fk_id;
    }

    public int[] getFlightIds() {
        return flight_ids;
    }

    public void setFlightIds(int[] flight_ids) {
        this.flight_ids = flight_ids;
    }

    public Ancillary[] getAncillaries() {
        return ancillaries;
    }

    public void setAncillaries(Ancillary[] ancillaries) {
        this.ancillaries = ancillaries;
    }

    public String[] getSeatNumbers() {
        return seat_numbers;
    }

    public void setSeatNumbers(String[] seat_ids) {
        this.seat_numbers = seat_ids;
    }

    public int getBags() {
        return bags;
    }

    public void setBags(int bags) {
        this.bags = bags;
    }
}
