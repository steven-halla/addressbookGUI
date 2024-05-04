
package Lab5;

//30min video  2 java docs

import static Lab5.MySQLConnection.DB_URL;
import static Lab5.MySQLConnection.PASS;
import static Lab5.MySQLConnection.USER;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
//33:44 imports



/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class        CreateAddressBookDB.java
 * Description  A class used to create the Address book DB
 * Platform     jdk 1.8.0_241; NetBeans IDE 11.3; PC Windows 10
 * Course       CS 143
 * Hourse       4 hours and 17 minutes
 * Date         4/5/2021
  History Log   7/18/2018, 5/7/2020
 * @author	<i>Niko Culevski</i>
 * @version 	%1% %2%
 * @see     	javax.swing.JFrame
 * @see         java.awt.Toolkit 
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class CreateAddressBookDB implements MySQLConnection {
    
    private static final String PERSONS_TEXT_FILE = "src/Lab5/Addresses.txt";
    private static final ArrayList<Person> friends = new ArrayList<Person>();
    private Person myPerson = new Person();

    
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Method       main().
 * Description  Reads data from external text file, creates the address table
 * Platform     jdk 1.8.0_241; NetBeans IDE 11.3; PC Windows 10
 * Course       CS 143
 * Hourse       4 hours and 17 minutes
 * Date         4/5/2021
  History Log   7/18/2018, 5/7/2020
 * @author	<i>Niko Culevski</i>
 * @param        args are the command line strings
 
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static void main(String[] args) {
        // TODO code application logic here
        try
        {            
            String url = DB_URL;
            String user = USER;
            String password = PASS;
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet table;
            
            table = dbm.getTables(null, null, "AddressBook", null);
            if(table.next())
            {
                stmt.executeUpdate("DROP TABLE AddressBook");
            }
            stmt.executeUpdate("CREATE TABLE AddressBook (personID"
                    + " SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT, firstName"
                    + " VARCHAR(20), lastName VARCHAR(20), age INTEGER,"
                    + " address VARCHAR(30), city"
                    + " VARCHAR(20), state VARCHAR(2), zip VARCHAR(5),"
                    + " PRIMARY KEY (personID))");
            
            for(int i = 0; i < friends.size(); i++)
            {
                stmt.executeUpdate
                        (
                        "INSERT INTO AddressBook VALUES("
                        + friends.get(i).getID() + ","
                        + "'" + friends.get(i).getFirstName() + "',"
                        + "'" + friends.get(i).getLastName() + "',"
                        + friends.get(i).getAge() + ","
                        + "'" + friends.get(i).getAddress() + "'"  + ","
                        + "'" + friends.get(i).getCity() + "'"  + ","
                        + "'" + friends.get(i).getState() + "'"  + ","
                        + "'" + friends.get(i).getZip() + "')"
                        );                       
                                                                 
            }
            stmt.close();
        }
        catch(SQLException exp)
        {
//            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQL error",
                    "SQL Error!", JOptionPane.ERROR_MESSAGE);
        }
    
}
      /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       readFromTextFile()
     * Description  Reads text file and creates an arraylist with persons.
     * Date:        4/23/2019
     * @author      <i>Niko Culevski</i>
     * @param       textFile String
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private static void readFromTextFile(String textFile)
    {        
        try
        {            
            FileReader freader = new FileReader(textFile);
            BufferedReader input = new BufferedReader(freader);
            String line = input.readLine();
            
            while(line != null)
            {
                Person tempPerson = new Person();
                StringTokenizer token = new StringTokenizer(line, ",");
                while(token.hasMoreElements())
                {
                    tempPerson.setID(Integer.parseInt(token.nextToken()));
                    tempPerson.setFirstName(token.nextToken());
                    tempPerson.setLastName(token.nextToken());
                    tempPerson.setAge(Integer.parseInt(token.nextToken()));
                    tempPerson.setAddress(token.nextToken());
                    tempPerson.setCity(token.nextToken());
                    tempPerson.setState(token.nextToken());
                    tempPerson.setZip(token.nextToken());

                    
                }
                friends.add(tempPerson);
                line = input.readLine();
            }
            input.close();
        }
        catch(FileNotFoundException fnfexp)
        {
            fnfexp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Input error -- File not found.",
                    "File Not Found Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException | NumberFormatException exp)
        {
//  do we need this??????          exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Input error -- File could not be read.",
                    "File Read Error!", JOptionPane.ERROR_MESSAGE);
        }
    }    
    
    
}