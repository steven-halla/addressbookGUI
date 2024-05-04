package Lab5;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*<pre>
 * Class        AddressBookGUI.java
 * Description  A class representing the GUI used in the Address Book 
 *              Application. This program that stores, retrieves, adds, and 
 *              updates addresses using SQL statements in Address Book DB.
 * Platform     jdk 1.8.0_241; NetBeans IDE 11.3; PC Windows 10
 * Course       CS 143
 * Hourse       4 hours and 17 minutes
 * Date         4/5/2021
  History Log   7/18/2018, 5/7/2020
 * @author	<i>Niko Culevski</i>
 * @version 	%1% %2%
 * @see     	javax.swing.JFrame
 * @see         java.awt.Toolkit 
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class AddressBookGUI extends JFrame implements MySQLConnection
{
    private final String PERSONS_TEXT_FILE = "src/Lab5/Addresses.txt";
    private  ArrayList<Person> friends = new ArrayList<Person>();
    private Person myPerson = new Person();
    private int currentID = 1, sizeOfDB;
    private final Color white = Color.WHITE;    // Default background color for input textfield
    private final Color pink = Color.PINK;      // Background color for bad input textfield
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  AddressBookGUI()-default constructor
     * Description  Create an instance of the GUI form and sets icon image.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Niko Culevski</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public AddressBookGUI() 
    {
        try
        {
            initComponents();        
            this.getRootPane().setDefaultButton(addJButton);
            this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/address_small.jpg"));
            //read from text file and fill ArrayList
//            readFromTextFile(PERSONS_TEXT_FILE);
//            createDB();
            String url = DB_URL;
            String user = USER;
            String password = PASS;
            
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("Select count(*) From AddressBook");
            rs.next();
            
            sizeOfDB = rs.getInt("count(*)");
            String query = "Select * FROM AddressBook";
            rs = stmt.executeQuery(query);
            rs.next();
            
            int firstIndex = rs.getInt("personId");
            Person tempPerson = searchPerson(firstIndex);
            display(tempPerson);
            
        }
        catch(SQLException exp)
        {
            // Show error message
//            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Input error -- SQL error.",
                    "SQL Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       display()
     * Description  Show information about the person passed as parameter.
     * @param       myPerson Person
     * @author      <i>Niko Culevski</i>
     * Date         4/3/2020
     * History Log  7/18/2018     
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void display(Person myPerson)
    {
        personNumberJLabel.setText(String.valueOf(myPerson.getID()));
        firstJTextField.setText(myPerson.getFirstName());
        lastJTextField.setText(myPerson.getLastName());
        ageJTextField.setText(String.valueOf(myPerson.getAge()));
        addressJTextField.setText(myPerson.getAddress());
        cityJTextField.setText(myPerson.getCity());
        statesJComboBox.setSelectedItem(myPerson.getState());
        zipJFormattedTextField.setText(myPerson.getZip());
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       readFromTextFile()
     * Description  Reads text file and creates an arraylist with persons.
     * Date:        4/23/2019
     * @author      <i>Niko Culevski</i>
     * @param       textFile String
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void readFromTextFile(String textFile)
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
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       createDB()
     * Description  Make connection, drop existing table, and create database 
     *              table.
     * Date:        4/23/2019
     * @author      <i>Niko Culevski</i>
     * @param       textFile String
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/   
    private void createDB()
            
            //5 min mark video 2 touches on this
    {   
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
     *<pre>
     * Method       searchPerson()
     * Description  Search Person in DB by id.
     * Date:        4/23/2019
     * @author      <i>Niko Culevski</i>
     * @param       id iny
     * @return      myPerson Person
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    private Person searchPerson(int id)
    {
        try
        { 
            //42:03
            String url = DB_URL;
            String user = USER;
            String password = PASS;
            Connection conn = DriverManager.getConnection(url, user, password);
            myPerson = new Person();
            
            String query = "SELECT * FROM AddressBook WHERE personID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet results = pstmt.executeQuery();
            results.next();
            myPerson.setID(results.getInt(1));
            myPerson.setFirstName(results.getString(2));
            myPerson.setLastName(results.getString(3));
            myPerson.setAge(results.getInt(4));
            myPerson.setAddress(results.getString(5));
            myPerson.setCity(results.getString(6));
            myPerson.setState(results.getString(7));
            myPerson.setZip(results.getString(8));
            
            results.close();
            pstmt.close();
            conn.close();
            return myPerson;
            
            
            
        }
        catch(SQLException exp)
        {
//            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error searching database for person", 
                    "Search Error", JOptionPane.ERROR_MESSAGE);
            return new Person();
        }        
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleJLabel = new javax.swing.JLabel();
        displayJPanel = new javax.swing.JPanel();
        firstJLabel = new javax.swing.JLabel();
        firstJTextField = new javax.swing.JTextField();
        lastJLabel = new javax.swing.JLabel();
        lastJTextField = new javax.swing.JTextField();
        ageJLabel = new javax.swing.JLabel();
        ageJTextField = new javax.swing.JTextField();
        addressJLabel = new javax.swing.JLabel();
        addressJTextField = new javax.swing.JTextField();
        cityJLabel = new javax.swing.JLabel();
        cityJTextField = new javax.swing.JTextField();
        stateJLabel = new javax.swing.JLabel();
        statesJComboBox = new javax.swing.JComboBox<>();
        zipJLabel = new javax.swing.JLabel();
        zipJFormattedTextField = new javax.swing.JFormattedTextField();
        controlJPanel = new javax.swing.JPanel();
        addJButton = new javax.swing.JButton();
        editJButton = new javax.swing.JButton();
        deleteJButton = new javax.swing.JButton();
        quitJButton = new javax.swing.JButton();
        navigationJPanel = new javax.swing.JPanel();
        firstJButton = new javax.swing.JButton();
        previousJButton = new javax.swing.JButton();
        nextJButton = new javax.swing.JButton();
        lastJButton = new javax.swing.JButton();
        personNumberJLabel = new javax.swing.JLabel();
        searchJTextField = new javax.swing.JTextField();
        searchJLabel = new javax.swing.JLabel();
        searchJButton = new javax.swing.JButton();
        personJMenuBar = new javax.swing.JMenuBar();
        fileJMenu = new javax.swing.JMenu();
        printMenuItem = new javax.swing.JMenuItem();
        quitJMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutJMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lab 5--Address Book Database");
        setResizable(false);

        titleJLabel.setFont(new java.awt.Font("Tempus Sans ITC", 2, 26)); // NOI18N
        titleJLabel.setForeground(new java.awt.Color(0, 102, 102));
        titleJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/address_small.jpg"))); // NOI18N
        titleJLabel.setText("Adress Book Database");

        displayJPanel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        displayJPanel.setLayout(new java.awt.GridLayout(7, 2, 3, 3));

        firstJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        firstJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        firstJLabel.setText("First Name");
        displayJPanel.add(firstJLabel);

        firstJTextField.setEditable(false);
        firstJTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        firstJTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        displayJPanel.add(firstJTextField);

        lastJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lastJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lastJLabel.setText("Last Name");
        displayJPanel.add(lastJLabel);

        lastJTextField.setEditable(false);
        lastJTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lastJTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        displayJPanel.add(lastJTextField);

        ageJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ageJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ageJLabel.setText("Age");
        displayJPanel.add(ageJLabel);

        ageJTextField.setEditable(false);
        ageJTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ageJTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        displayJPanel.add(ageJTextField);

        addressJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addressJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addressJLabel.setText("Address");
        displayJPanel.add(addressJLabel);

        addressJTextField.setEditable(false);
        addressJTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addressJTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        displayJPanel.add(addressJTextField);

        cityJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cityJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cityJLabel.setText("City");
        displayJPanel.add(cityJLabel);

        cityJTextField.setEditable(false);
        cityJTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cityJTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        displayJPanel.add(cityJTextField);

        stateJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        stateJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        stateJLabel.setText("State");
        displayJPanel.add(stateJLabel);

        statesJComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        statesJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY" }));
        displayJPanel.add(statesJComboBox);

        zipJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        zipJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        zipJLabel.setText("Zip");
        displayJPanel.add(zipJLabel);

        zipJFormattedTextField.setBackground(new java.awt.Color(240, 240, 240));
        try {
            zipJFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        zipJFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        zipJFormattedTextField.setToolTipText("Exactly 5 digits");
        zipJFormattedTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        zipJFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                zipJFormattedTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                zipJFormattedTextFieldFocusLost(evt);
            }
        });
        displayJPanel.add(zipJFormattedTextField);

        controlJPanel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        controlJPanel.setLayout(new java.awt.GridLayout(2, 2, 3, 3));

        addJButton.setBackground(new java.awt.Color(255, 255, 204));
        addJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addJButton.setMnemonic('A');
        addJButton.setText("Add");
        addJButton.setToolTipText("Add new record");
        addJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(addJButton);

        editJButton.setBackground(new java.awt.Color(255, 255, 204));
        editJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editJButton.setMnemonic('E');
        editJButton.setText("Edit");
        editJButton.setToolTipText("Edit current record");
        editJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(editJButton);

        deleteJButton.setBackground(new java.awt.Color(255, 255, 204));
        deleteJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteJButton.setMnemonic('D');
        deleteJButton.setText("Delete");
        deleteJButton.setToolTipText("Delete current record");
        deleteJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(deleteJButton);

        quitJButton.setBackground(new java.awt.Color(255, 255, 204));
        quitJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quitJButton.setMnemonic('Q');
        quitJButton.setText("Quit");
        quitJButton.setToolTipText("End application");
        quitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(quitJButton);

        navigationJPanel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        navigationJPanel.setLayout(new java.awt.GridLayout(1, 4, 3, 3));

        firstJButton.setBackground(new java.awt.Color(204, 255, 204));
        firstJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        firstJButton.setMnemonic('s');
        firstJButton.setText("<<");
        firstJButton.setToolTipText("First");
        firstJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstJButtonActionPerformed(evt);
            }
        });
        navigationJPanel.add(firstJButton);

        previousJButton.setBackground(new java.awt.Color(204, 255, 204));
        previousJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        previousJButton.setMnemonic('r');
        previousJButton.setText("<");
        previousJButton.setToolTipText("Previous");
        previousJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousJButtonActionPerformed(evt);
            }
        });
        navigationJPanel.add(previousJButton);

        nextJButton.setBackground(new java.awt.Color(204, 255, 204));
        nextJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nextJButton.setMnemonic('N');
        nextJButton.setText(">");
        nextJButton.setToolTipText("Next");
        nextJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextJButtonActionPerformed(evt);
            }
        });
        navigationJPanel.add(nextJButton);

        lastJButton.setBackground(new java.awt.Color(204, 255, 204));
        lastJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lastJButton.setMnemonic('L');
        lastJButton.setText(">>");
        lastJButton.setToolTipText("Last");
        lastJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastJButtonActionPerformed(evt);
            }
        });
        navigationJPanel.add(lastJButton);

        personNumberJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        searchJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJTextFieldActionPerformed(evt);
            }
        });

        searchJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        searchJLabel.setText("Search");

        searchJButton.setText("search");
        searchJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJButtonActionPerformed(evt);
            }
        });

        fileJMenu.setMnemonic('F');
        fileJMenu.setText("File");

        printMenuItem.setMnemonic('P');
        printMenuItem.setText("Print Form");
        printMenuItem.setToolTipText("Print Form as GUI");
        printMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printMenuItem);

        quitJMenuItem.setMnemonic('Q');
        quitJMenuItem.setText("Quit");
        quitJMenuItem.setToolTipText("");
        quitJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(quitJMenuItem);

        personJMenuBar.add(fileJMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText("Help");

        aboutJMenuItem.setMnemonic('A');
        aboutJMenuItem.setText("About");
        aboutJMenuItem.setToolTipText("Show About form");
        aboutJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutJMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutJMenuItem);

        personJMenuBar.add(helpMenu);

        setJMenuBar(personJMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(controlJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(navigationJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                    .addComponent(displayJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(searchJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(titleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(personNumberJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchJButton))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(searchJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(personNumberJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchJLabel)
                            .addComponent(searchJButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(displayJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(navigationJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(controlJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       quitJButtonActionPerformed()
     * Description  Event handler to end the application.
     * @author      <i>Niko Culevski</i>
     * Date         4/3/2020
     * History Log  7/18/2018     
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void quitJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_quitJButtonActionPerformed
    {//GEN-HEADEREND:event_quitJButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitJButtonActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       nextJButtonActionPerformed()
     * Description  Move to next address record. 
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void nextJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_nextJButtonActionPerformed
    {//GEN-HEADEREND:event_nextJButtonActionPerformed
        try
            //47.26
        {
               int passing = 0;
            currentID = Integer.parseInt(personNumberJLabel.getText());
            
            String url = DB_URL;
            String user = USER;
            String password = PASS;
            
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM AddressBook";
            
            ResultSet rs = stmt.executeQuery(query);
            
            rs.next();
            passing = rs.getInt("personID");
            while(passing != currentID)
            {
//                System.out.println(rs.getInt("personID"));
                if(currentID == rs.getInt("personID"))
                {
                    rs.next();
                    System.out.println(rs.getInt("personID"));
                    break;
                }
                rs.next();
                passing = rs.getInt("personID");
                
            }
            if(rs.isLast())
            {
                rs.first();
                currentID = rs.getInt("personID");
            }
            else
            {
                
            rs.next();
            currentID = rs.getInt("personID");
            }
            Person foundPerson = searchPerson(currentID);
            display(foundPerson);
                
        }
        catch(NumberFormatException | SQLException exp)
        {
            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error in . moving to next database", 
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }        
    }//GEN-LAST:event_nextJButtonActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       previousJButtonActionPerformed()
     * Description  Move to previous address record. 
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void previousJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_previousJButtonActionPerformed
    {//GEN-HEADEREND:event_previousJButtonActionPerformed
        try
        {
            currentID = Integer.parseInt(personNumberJLabel.getText());
            int passing = 0;
            String url = DB_URL;
            String user = USER;
            String password = PASS;
            
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM AddressBook";
            
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            passing = rs.getInt("personID");
             while(passing != currentID)
            {
//                System.out.println(rs.getInt("personID"));
                if(currentID == rs.getInt("personID"))
                {
                    rs.previous();
                    System.out.println(rs.getInt("personID"));
                    break;
                }
                rs.next();
                passing = rs.getInt("personID");
                
            }
            if(rs.isFirst())
            {
                rs.last();
                currentID = rs.getInt("personID");
            }
            else
            {
                
            rs.previous();
            currentID = rs.getInt("personID");
            }
            Person foundPerson = searchPerson(currentID);
            display(foundPerson);
        
            
            
        }
        
        catch(NumberFormatException | SQLException exp)
        {
            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating to database", 
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }        
    }//GEN-LAST:event_previousJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       lastJButtonActionPerformed()
     * Description  Move to last address record. 
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void lastJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_lastJButtonActionPerformed
    {//GEN-HEADEREND:event_lastJButtonActionPerformed
        // move to first AddressBook 54:23
        try
        {
              String url = DB_URL;
            String user = USER;
            String password = PASS;
            
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM AddressBook";
            
            ResultSet rs = stmt.executeQuery(query);
            rs.last();
            currentID = rs.getInt("personID");
            Person myPerson = searchPerson(currentID);
            display(myPerson);
        }
        catch(Exception exp)
        {
//            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating to database", 
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lastJButtonActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       firstJButtonActionPerformed()
     * Description  Move to last address record. 
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void firstJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_firstJButtonActionPerformed
    {//GEN-HEADEREND:event_firstJButtonActionPerformed
        // move to first AddressBook 45:53
        try
        {
            String url = DB_URL;
            String user = USER;
            String password = PASS;
            
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM AddressBook";
            
            ResultSet rs = stmt.executeQuery(query);
            
            rs.next();
            currentID = rs.getInt("personID");
            Person myPerson = searchPerson(currentID);
            display(myPerson);
        }
        catch(Exception exp)
        {
//            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating to database", 
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_firstJButtonActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       addJButtonActionPerformed()
     * Description  Add a new person. 
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void addJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addJButtonActionPerformed
    {//GEN-HEADEREND:event_addJButtonActionPerformed
        String message = "Person not added.";
        try
        {
            //video 2 12:43
            AddPerson myAddForm = new AddPerson(this, true);
            myAddForm.setVisible(true);
            int lastIndex = 0;
            Person newPerson = myAddForm.getPerson();
            
            if(newPerson != null && !exists(newPerson))
            {
                String url = DB_URL;
                String user = USER;
                String password = PASS;
                
                Connection con = DriverManager.getConnection(url, user, password);
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                String query = "SELECT * FROM AddressBook";
                
                ResultSet rs = stmt.executeQuery(query);
                rs.last();
                
                lastIndex = sizeOfDB;
                newPerson.setID(lastIndex + 1);
                
                stmt.executeUpdate
                        ("INSERT INTO AddressBook VALUES (" + newPerson.getID() + ",'" +
                                newPerson.getFirstName() + "','" + newPerson.getLastName() +
                                "'," + newPerson.getAge() + ",'" + newPerson.getAddress() +
                                "','" + newPerson.getCity() + "','" + newPerson.getState() +
                                "','" + newPerson.getZip() + "')"

                                );
//                friends.add(newPerson);
                display(newPerson);
                sizeOfDB++;
                con.close();
                        
                
            }
            else 
            {
                message = "Person not added";
                newPerson.setID(lastIndex);
                display(newPerson);
            }
        }
        catch(NullPointerException exp)
        {
            JOptionPane.showMessageDialog(null, message, "Input Error",
                    JOptionPane.WARNING_MESSAGE);            
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating to database", 
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addJButtonActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       deleteJButtonActionPerformed()
     * Description  Delete the displayed person. 
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void deleteJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteJButtonActionPerformed
    {//GEN-HEADEREND:event_deleteJButtonActionPerformed
        
        //25.28 video 2
        int index = Integer.parseInt(personNumberJLabel.getText());
        Person personToDelete = searchPerson(index);
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delte "
        + personToDelete.getFirstName() + " " + personToDelete.getLastName()
        + "?", "Delete person ", JOptionPane.YES_NO_OPTION);
        
        if(result == JOptionPane.YES_OPTION)
        {
            try
            {
                String url = DB_URL;
                String user = USER;
                String password = PASS;
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                
                String query = "DELETE FROM AddressBook WHERE personID = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, index);
                pstmt.execute();  
                query = "SELECT * FROM AddressBook";
                ResultSet rs = stmt.executeQuery(query);
                rs.next();
                index = rs.getInt("personID");
                
                display(searchPerson(index));
                conn.close();
                
            }
            catch(SQLException exp)
            {
                exp.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error deleting.",
                        "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteJButtonActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       editJButtonActionPerformed()
     * Description  Edit the displayed person. 
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void editJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editJButtonActionPerformed
    {//GEN-HEADEREND:event_editJButtonActionPerformed
        String message = "Person not edited.";
        try
        {
           currentID = Integer.parseInt(personNumberJLabel.getText());
           myPerson = searchPerson(currentID);
           
           AddPerson myEditForm = new AddPerson(myPerson);
           myEditForm.setVisible(true);
           
           Person editPerson = myEditForm.getPerson();
           editPerson.setID(currentID);
           
           if(editPerson != null)
           {
               myPerson.setID(currentID);
               myPerson.setFirstName(editPerson.getFirstName());
               myPerson.setLastName(editPerson.getLastName());
               myPerson.setAge(editPerson.getAge());
               myPerson.setAddress(editPerson.getAddress());
               myPerson.setCity(editPerson.getCity());
               myPerson.setState(editPerson.getState());
               myPerson.setZip(editPerson.getZip());
               
               String url = DB_URL;
               String user = USER;
               String password = PASS;
               Connection conn = DriverManager.getConnection(url, user, password);
               Statement stmt = conn.createStatement();
                    
               
               String query = "UPDATE AddressBook SET firstName = " + "'" +
                       myPerson.getFirstName() + "', lastName = '" +
                       myPerson.getLastName() + "', age = " +
                       myPerson.getAge() + ", address = '" +
                       myPerson.getAddress() + "', city = '" +
                       myPerson.getCity() + "', state = '" +
                       myPerson.getState() + "', zip = '" +
                       myPerson.getZip() + "' where personID = " +
                       myPerson.getID();
                
                stmt.executeUpdate(query);
                display(myPerson);
                stmt.close();
                conn.close();
                
                
               
           }
           else 
           {
                 JOptionPane.showMessageDialog(null, message, 
                    "Error!", JOptionPane.ERROR_MESSAGE);
           }
        }
        catch(SQLException exp)
        {
//            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, message, 
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editJButtonActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       zipJFormattedTextFieldFocusGained()
     * Description  Highlight zipJFormattedTextField as focus is gained.
     * @parem       java.awt.event.FocusEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History log  8/24/2016, 4/3/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void zipJFormattedTextFieldFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_zipJFormattedTextFieldFocusGained
    {//GEN-HEADEREND:event_zipJFormattedTextFieldFocusGained
        zipJFormattedTextField.selectAll();
    }//GEN-LAST:event_zipJFormattedTextFieldFocusGained
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       zipJFormattedTextFieldFocusLost()
     * Description  Change color of zipJTextField according to valid input.
     * @parem       java.awt.event.FocusEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History log  8/24/2016, 4/3/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void zipJFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_zipJFormattedTextFieldFocusLost
    {//GEN-HEADEREND:event_zipJFormattedTextFieldFocusLost
        String input = zipJFormattedTextField.getText();
        if(Validation.isValidZip(input))
            zipJFormattedTextField.setBackground(white);
        else
            zipJFormattedTextField.setBackground(pink);
    }//GEN-LAST:event_zipJFormattedTextFieldFocusLost
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       quitJMenuItemActionPerformed()
     * Description  Event handler to end the application. Calls the quitJButton
     *              doClick event handler,
     * @author      <i>Niko Culevski</i>
     * Date         4/3/2020
     * History Log  7/18/2018     
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void quitJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_quitJMenuItemActionPerformed
    {//GEN-HEADEREND:event_quitJMenuItemActionPerformed
        quitJButton.doClick();
    }//GEN-LAST:event_quitJMenuItemActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       printJMenuItemActionPerformed()
     * Description  Event handler to print the for as a GUI. Calls the
     *              PrintUtilities class printComponent method.
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History Log  7/18/2018, 4/3/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void printMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_printMenuItemActionPerformed
    {//GEN-HEADEREND:event_printMenuItemActionPerformed
        PrintUtilities.printComponent(this);
    }//GEN-LAST:event_printMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       aboutJMenuItemActionPerformed()
     * Description  Create an About form and show it. 
     * @param       evt java.awt.event.KeyEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void aboutJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_aboutJMenuItemActionPerformed
    {//GEN-HEADEREND:event_aboutJMenuItemActionPerformed
        About aboutWindow = new About(this, true);
        aboutWindow.setVisible(true);
    }//GEN-LAST:event_aboutJMenuItemActionPerformed

    private void searchJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJTextFieldActionPerformed
        // TODO add your handling code here:
           String searchText = searchJTextField.getText().trim();
    if (searchText.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter search criteria!", "No Search Criteria", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // SQL Query to search the database
    String query = "SELECT * FROM AddressBook WHERE firstName LIKE ? OR lastName LIKE ? OR city LIKE ? OR state LIKE ?";

    try {
        Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement pstmt = con.prepareStatement(query);

        // Setting the parameters for the prepared statement
        pstmt.setString(1, "%" + searchText + "%");
        pstmt.setString(2, "%" + searchText + "%");
        pstmt.setString(3, "%" + searchText + "%");
        pstmt.setString(4, "%" + searchText + "%");

        ResultSet rs = pstmt.executeQuery();

        // Check if there are results
        if (!rs.next()) {
            JOptionPane.showMessageDialog(null, "No results found!", "No Results", JOptionPane.INFORMATION_MESSAGE);
            rs.close();
            pstmt.close();
            con.close();
            return;
        }

        // If there are results, process and display them
        rs.beforeFirst(); // Reset cursor position
        while (rs.next()) {
            // You might want to display the results in a different component or update an existing table
            System.out.println("Found: " + rs.getString("firstName") + " " + rs.getString("lastName"));
        }
        rs.close();
        pstmt.close();
        con.close();

    } catch (SQLException exp) {
        JOptionPane.showMessageDialog(null, "SQL error: " + exp.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        exp.printStackTrace();
    }
    }//GEN-LAST:event_searchJTextFieldActionPerformed

    private void searchJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJButtonActionPerformed
         String searchText = searchJTextField.getText().trim();
    if (searchText.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter some text to search.", "No Search Criteria", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String query = "SELECT * FROM AddressBook WHERE firstName LIKE ? OR lastName LIKE ? OR city LIKE ? OR state LIKE ?";

    try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement pstmt = con.prepareStatement(query)) {
        
        pstmt.setString(1, "%" + searchText + "%");
        pstmt.setString(2, "%" + searchText + "%");
        pstmt.setString(3, "%" + searchText + "%");
        pstmt.setString(4, "%" + searchText + "%");
        ResultSet rs = pstmt.executeQuery();

        if (!rs.isBeforeFirst()) {  // rs.isBeforeFirst() is false if there are no rows in the ResultSet
            JOptionPane.showMessageDialog(null, "No results found for: " + searchText, "No Results", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder results = new StringBuilder("Search Results:\n");
        while (rs.next()) {
            results.append(rs.getString("firstName")).append(" ")
                   .append(rs.getString("lastName")).append(", ")
                   .append(rs.getString("city")).append(", ")
                   .append(rs.getString("state")).append("\n");
        }

        JOptionPane.showMessageDialog(null, results.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException exp) {
        JOptionPane.showMessageDialog(null, "SQL Error: " + exp.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        exp.printStackTrace();
    }
    }//GEN-LAST:event_searchJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       exists()
     * Description  Check if parameter-given person exists in the DB. 
     * @param       myPerson Person
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean exists(Person myPerson)
    {
        boolean found = false;
        try
        {
            
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error in exists.", 
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
        return found;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
     * Method       main()
     * Description  Displays splash screen and the main Address Book DB GUI form.
     * @param       args are the command line strings
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static void main(String args[])
    {
        // Show splash screen
//        Splash mySplash = new Splash(4000);     // duration = 5 seconds
//        mySplash.showSplash();                  // show splash screen   
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(AddressBookGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(AddressBookGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(AddressBookGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(AddressBookGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new AddressBookGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutJMenuItem;
    private javax.swing.JButton addJButton;
    private javax.swing.JLabel addressJLabel;
    private javax.swing.JTextField addressJTextField;
    private javax.swing.JLabel ageJLabel;
    private javax.swing.JTextField ageJTextField;
    private javax.swing.JLabel cityJLabel;
    private javax.swing.JTextField cityJTextField;
    private javax.swing.JPanel controlJPanel;
    private javax.swing.JButton deleteJButton;
    private javax.swing.JPanel displayJPanel;
    private javax.swing.JButton editJButton;
    private javax.swing.JMenu fileJMenu;
    private javax.swing.JButton firstJButton;
    private javax.swing.JLabel firstJLabel;
    private javax.swing.JTextField firstJTextField;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton lastJButton;
    private javax.swing.JLabel lastJLabel;
    private javax.swing.JTextField lastJTextField;
    private javax.swing.JPanel navigationJPanel;
    private javax.swing.JButton nextJButton;
    private javax.swing.JMenuBar personJMenuBar;
    private javax.swing.JLabel personNumberJLabel;
    private javax.swing.JButton previousJButton;
    private javax.swing.JMenuItem printMenuItem;
    private javax.swing.JButton quitJButton;
    private javax.swing.JMenuItem quitJMenuItem;
    private javax.swing.JButton searchJButton;
    private javax.swing.JLabel searchJLabel;
    private javax.swing.JTextField searchJTextField;
    private javax.swing.JLabel stateJLabel;
    private javax.swing.JComboBox<String> statesJComboBox;
    private javax.swing.JLabel titleJLabel;
    private javax.swing.JFormattedTextField zipJFormattedTextField;
    private javax.swing.JLabel zipJLabel;
    // End of variables declaration//GEN-END:variables
}
