import java.sql.*;

/**#######################################################################
 * # Class Name:    CreditCard
 * # Descriptions:  A CreditCard allows users to store their card information 
 *					in order to make flight purchases easier. If user does not
 *					insert valid card info. the card will prompt user that the 
 *					card was declined
 *
 */

public class CreditCardModel
{
    String cardNum;
    String cardCVV;
    String cardType;
    String nameOnCard;
    String cardExpiration;

	protected static void cardProcess(){}
	/*
	 * Card will be able to purchase flights once user inputs valid card info
	 * If user card info is not valid, the card will decline and prompt user
	 */
        
        public boolean addCardToDatabase(String cardNum, String cardCVV, String nameOnCard, String cardExpiration, String cardType) {
            this.cardNum = cardNum;
            this.cardCVV = cardCVV;
            this.nameOnCard = nameOnCard;
            this.cardExpiration = cardExpiration;
            this.cardType = cardType;
            String query_string = ("select * from card where number = '"+cardNum+"';");
            ResultSet result_set = queryDB(query_string);
            boolean status = handleInsertQuery(result_set);
            return status;
        }
        
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
        return result_set;
    }
        
    public boolean handleInsertQuery(ResultSet rs) {
        try {
            while(rs.next()) {
                System.out.println(nameOnCard);
                return false;
            }
            
            String query_string2 = ("insert into card (number, cvv, name, expiration, type) VALUES ('"+cardNum+"', '"+cardCVV+"', '"+nameOnCard+"', '"+cardExpiration+"', '"+cardType+"')");
            queryDB(query_string2);
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
}


