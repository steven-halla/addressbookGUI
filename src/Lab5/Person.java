package Lab5;
import java.util.Objects;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* <pre>
 * Class        Person 
 * Project      Address Book DB
 * Description  This is class that encasolute a very simple version of a Person
 *              used in the Address Book DB.
 * Environment  PC, Windows 10, jdk1.8.0_241, NetBeans 11.3
 * Date         4/30/2021
 * History Log  2/15/2016, 5/15/2016, 5/10/2020
 * @author      <i>Niko Culevski</i>
 * @version     1.3.2
* </pre>
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Person
{
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String city;
    private String state;
    private String zip;

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  Person()-default constructor
     * Description  Assigns default values to instance variables.
     * @author      <i>Niko Culevski</i>
     * Date         4/30/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public Person()
    {
        id = age = 0;
        firstName = lastName = address = city = state = zip = "";
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       HashCode
     * Description  Overriden hash code.
     * @author      <i>Niko Culevski</i>
     * @return      hash value int
     * Date         4/30/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public int hashCode()
    {
        int hash = 5;
        return hash;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  Person()-overloaded constructor
     * Description  Assigns parameter values to instance variables.
     * @param       id int
     * @param       first String
     * @param       last String
     * @param       age int
     * @param       address String
     * @param       city String
     * @param       state String
     * @param       zip String
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public Person(int id, String first, String last, int age, String address, 
            String city, String state, String zip)
    {
        this.id = id;
        this.firstName = first;
        this.lastName = last;
        this.age = age;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }    

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       equals()
     * Description  Overridden method to check equality between persons.
     *              Two person are the same if they have the same first name,
     *              same last name, same age, and same zip.
     * @return      true or flase boolean
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public boolean equals(Object obj)
    {
//        if (this == obj)
//        {
//            return true;
//        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Person other = (Person) obj;
        if(Objects.equals(this.firstName, other.firstName) &&
            Objects.equals(this.lastName, other.lastName) &&
            this.age == other.age && Objects.equals(this.zip, other.zip))
        {
            return true;
        }   
        else
            return false;
//        if (this.age != other.age)
//        {
//            return false;
//        }
//        if (!Objects.equals(this.firstName, other.firstName))
//        {
//            return false;
//        }
//        if (!Objects.equals(this.lastName, other.lastName))
//        {
//            return false;
//        }
//        if (!Objects.equals(this.address, other.address))
//        {
//            return false;
//        }
//        if (!Objects.equals(this.city, other.city))
//        {
//            return false;
//        }
//        if (!Objects.equals(this.state, other.state))
//        {
//            return false;
//        }
//        if (!Objects.equals(this.zip, other.zip))
//        {
//            return false;
//        }        
    }    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getID()
     * Description  Getter method to return id.
     * @return      id int
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int getID()
    {
        return id;
    }
    
    public void setID(int id)
    {
        this.id = id;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getFirstName()
     * Description  Getter method to return first name.
     * @return      firstName String
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getLastName()
     * Description  Getter method to return last name.
     * @return      firstName String
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getAge()
     * Description  Getter method to return age.
     * @return      age int
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int getAge()
    {
        return age;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setAge()
     * Description  Setter method to set age.
     * @param       age int
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setAge(int age)
    {
        this.age = age;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getAddress()
     * Description  Getter method to return address.
     * @return      address String
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getAddress()
    {
        return address;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setAddress()
     * Description  Setter method to set address.
     * @param       address String
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getCity()
     * Description  Getter method to return city.
     * @return      city String
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getCity()
    {
        return city;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setCity()
     * Description  Setter method to set city.
     * @param       city String
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public void setCity(String city)
    {
        this.city = city;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getState()
     * Description  Getter method to return state.
     * @return      state String
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getState()
    {
        return state;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setState()
     * Description  Setter method to set state.
     * @param       state String
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    public void setState(String state)
    {
        this.state = state;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getZip()
     * Description  Getter method to return zip.
     * @return      zip String
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getZip()
    {
        return zip;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setZip()
     * Description  Setter method to set zip.
     * @param       zip String
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    public void setZip(String zip)
    {
        this.zip = zip;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       toString()
     * Description  Overridden method to retrun person's's fields
     * @return      person String
     * @author      <i>Niko Culevski</i>
     * Date         5/10/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override    
    public String toString()
    {
        return "Person{" + "id=" + id + ", firstName=" + firstName + ", lastName=" 
                + lastName + ", age=" + age + ", address=" + address + ", city=" 
                + city + ", state=" + state + ", zip=" + zip + '}';
    }
    
}
