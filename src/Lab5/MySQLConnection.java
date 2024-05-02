
package Lab5;


/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*<pre>
 * Class        MySQLConnection.java
 * Description  An interface representing the connections constants needed for
 *              the Address Book Application
 * Platform     jdk 1.8.0_241; NetBeans IDE 11.3; Mac OS Sonoma 14
 * Course       CS 143
 * Hourse       4 hours and 17 minutes
 * Date         5/2/2024
  History Log   7/18/2018, 5/7/2020
 * @author	<i>Steven Halla</i>
 * @version 	%1% %2%
 * @see     	javax.swing.JFrame
 * @see         java.awt.Toolkit 
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public interface MySQLConnection {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/javabook";
    public static final String USER = "root";
    public static final String PASS = "steve";// may need to change this to steve
    public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    
    
}