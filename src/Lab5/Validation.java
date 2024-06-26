package Lab5;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class        Validation
 * File         Validation.java
 * Description  A class that validates information passed to it.
 * Environment  PC, Windows 10 jdk 1.8.0_241, Netbeans 11.3
 * Date         4/30/2021
 * History log  5/8/2016
 * @author      <i>Niko Culevski</i>
 * @version     1.2
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Validation 
{   
    public static final double DOUBLE_RANGE = 1E-9;      
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       isLettersOnly()
     * Description  Returns true if the String is made of only letters.
     * Date         4/30/2021
     * History log  5/8/2016
     * @author      <i>Niko Culevski</i>
     * @param       myString String
     * @return      true/false boolean
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isLettersOnly(String myString)
    {
        // Check if the String is empty
        if(myString.isEmpty())
            return false;
        // Make a char array for the string and check each character
        char[] charArray = myString.toCharArray();
        for(int i = 0; i < charArray.length; i++)
        {
            if(!Character.isLetter(charArray[i]) && !Character.isSpaceChar(charArray[i]))
                return false;
        }
        // It is letters and space only!
        return true;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       isNumeric()
     * Description  Returns true if the String is made of only numbers
     * Date         4/30/2021
     * History log  5/8/2016
     * @author      <i>Niko Culevski</i>
     * @param       myString String
     * @return      true/false boolean
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isNumeric(String myString)
    {
        // Check if the string contains only numbers ( or a decimal or negative sign)
        return myString.matches("-?\\d+(\\.\\d+)?");
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       isInteger()
     * Description  Validates that entered value is an integer
     * @return      boolean
     * @param       fieldValue String, input
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * Date         4/5/2021
     * History log  6/10/2013, 4/7/2020
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isInteger(String fieldValue)
    {
        Pattern pat = Pattern.compile("\\d+");
        Matcher mat = pat.matcher(fieldValue);
        return mat.matches();   
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       isInteger()
     * Description  Overloaded, validates that entered value is an integer
     *              within the required range
     * @return      boolean
     * @param       fieldValue String, input
     * @param       lower int, lower bound
     * @param       upper int, upper bound
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * Date         4/5/2021
     * History log  6/10/2013, 4/7/2020
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isInteger(String fieldValue, int lower, int upper)
    {
        boolean result = true;
        Pattern pat = Pattern.compile("\\d+");
        Matcher mat = pat.matcher(fieldValue);
        if(mat.matches())
        {
            try
            {
                int num = Integer.parseInt(fieldValue);
                if(num < lower || num > upper)      //check range
                    result = false;
            }
            catch(NumberFormatException ex)
            {
                result = false;
            }
        }
        else
        {
            result = false;
        }
        return result;           
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       isPhoneNumber()
     * Description  Returns true if the String is a valid phone number
     * Date         4/30/2021
     * History log  5/8/2016
     * @author      <i>Niko Culevski</i>
     * @param       phoneNum String
     * @return      true/false boolean
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isPhoneNumber(String phoneNum)
    {
        // Check if string is of format "1234567890"
        if(phoneNum.matches("\\d{10}"))
            return true;
        // Check if string is phone number with -, ., or spaces
        else if(phoneNum.matches("\\d{3}[1\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))  
            return true;
        // Check if string is phone number with extension length
        else if(phoneNum.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
            return true;
        // Check if string is phone number with braces around area code
        else if(phoneNum.matches("\\(\\d{3}\\)\\d{3}-\\d{4}"))
            return true;
        // It is not valid
        else 
            return false;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       isValidPhone()
     * Description  Validates that JTextField is a valid North American phone
     *              number with the following phone formats:
     *              1234567890: true
     *              123-456-7890: true
     *              123.456.7890: true
     *              123 456 7890: true
     *              (123) 456 7890: true
     *              12345678: false
     *              12-12-111: false
     * Date         4/7/2020
     * History log  6/10/2013
     * @return      true/false boolean
     * @param       fieldValue JTextField
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * @see         javax.swing.JTextField
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isValidPhone(String fieldValue)
    {
        String regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(fieldValue);
        return mat.matches();
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       isEmail()
     * Description  Returns true if the String is a valid email.
     * Date         4/30/2021
     * History log  5/8/2016
     * @author      <i>Niko Culevski</i>
     * @param       myString String
     * @return      true/false boolean
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isEmail(String myString)
    {
        // Check if the string contains only numbers ( or a decimal or negative sign)
        return myString.matches("^[\\w-_\\.+]*[\\w-_\\.]@([\\w]+\\.)+[\\w]+[\\w]$");
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       isValidEmail()
     * Description  Validates that JTextField is a valid email with the 
     *              following email formats:
     *              user@domain.com true
     *              user@domain.co.in true
     *              user.name@domain.com true
     *              user?name@domain.co.in true
     *              user'name@domain.co.in true
     * Date         4/7/2020
     * History log  6/10/2013, 1/4/2014
     * @return      boolean
     * @param       fieldValue JTextField
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * @see         javax.swing.JTextField
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isValidEmail(String fieldValue)
    {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(fieldValue);
        return mat.matches();
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       isValidZip()
     * Description  Validates that JTextField is a valid zip code consisting of 
     *              only 5 digits or 5 digits plus - and 4 more digits.
     * @return      true or false boolean
     * @param       input JTextField
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * @see         javax.swing.JTextField
     * Date         4/5/2021
     * History log  6/10/2013, 4/7/2020
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isValidZip(String input)
    {
        boolean result = true;
        if(input.equals("") || input.equals(null))
            return false;
        String regx =  "^[0-9]{5}(?:-[0-9]{4})?$";  
        Pattern pat = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher mat = pat.matcher(input);    
        result = mat.matches();
        return result;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       toNameFormat()
     * Description  Returns a string in proper name format.
     * Date         4/30/2021
     * History log  5/8/2016
     * @author      <i>Niko Culevski</i>
     * @param       str String
     * @return      String
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static String toNameFormat(String str)
    {
        str = str.trim();
        str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        char[] charArray = new char[str.length()];
        charArray = str.trim().toCharArray();
        for(int i = 0; i < charArray.length; i++)
        {
            if(charArray[i] == (' '))
            {
                charArray[i+1] = Character.toUpperCase(charArray[i+1]);
            }
        }
        return new String(charArray);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       isValidName()
     * Description  Validates that JTextField is a valid name consisting of 
     *              only letters and spaces with minimum 2 and maximum 40
     *              letters.
     * @return      boolean
     * @param       input: JTextField, input
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * @see         javax.swing.JTextField
     * Date         4/5/2021
     * History log  6/10/2013, 4/7/2020
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isValidName(String input)
    {
        final short MAX_LENGTH = 32;
        final short MIN_LENGTH = 2;
        boolean result = true;
        if(input.equals("") || input.length() < MIN_LENGTH || 
                input.length() > MAX_LENGTH || input.equals(null))
            return false;
        //Letters and spaces in unicode only
        String regx =  "^[\\p{L} _.'-]+$";     //"^\\p{Lu}\\p{L}*(?:[\\s-]\\p{Lu}\\p{L}*)*$";
        Pattern pat = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher mat = pat.matcher(input);    
        result = mat.matches();
        return result;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       isValidName()--overloaded
     * Description  Validates that JTextField is a valid name consisting of 
     *              only letters and spaces with minimum and maximum as provide
     *              parameters.
     * @return      true or false boolean
     * @param       input JTextField
     * @param       lower int
     * @param       upper int
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * @see         javax.swing.JTextField
     * Date         4/5/2021
     * History log  6/10/2013, 4/7/2020
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isValidName(String input, int lower, int upper)
    {
        boolean result = true;
        if(input.equals("") || input.length() < lower || 
                input.length() > upper || input.equals(null))
            return false;
        //Letters and spaces in unicode only
        String regx =  "^[\\p{L} _.'-]+$";
        Pattern pat = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher mat = pat.matcher(input);    
        result = mat.matches();
        return result;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       removeSpaces()
     * Description  Returns string with all spaces removed
     * Date         4/30/2021
     * History log  5/8/2016
     * @author      <i>Niko Culevski</i>
     * @param       str String
     * @return      String
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static String removeSpaces(String str)
    {
        return str.replace("\\s", "");
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       toPhoneFormat()
     * Description  Returns string with all (yyy) yyy-yyyy format
     * Date         4/30/2021
     * History log  5/8/2016
     * @author      <i>Niko Culevski</i>
     * @param       str String
     * @return      String
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static String toPhoneFormat(String str)
    {
        if(str.length() == 10 )
        {
            char[] c = str.toCharArray();
            char[] phoneFormat = {'(',c[0],c[1],c[2],')',c[3],c[4],c[5],'-',
                c[6],c[7],c[8],c[9]};
            return new String(phoneFormat);
        }
        return str;
    }
        
   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       isCloseEnough()
     * Description  Determines if a double value is equal to another value 
     *              considering the inaccuracy of a double value.
     * Date         4/30/2021
     * History log  5/8/2016
     * @author      <i>Niko Culevski</i>
     * @param       checkedVal  value being compared
     * @param       closeToVal value being compared to
     * @return      boolean
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isCloseEnough(double checkedVal, double closeToVal)
    {
        return (Math.abs(checkedVal - closeToVal) < DOUBLE_RANGE);
    }    
}
