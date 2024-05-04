package Lab5;
import java.awt.Color;
import javax.swing.JOptionPane;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class        AddPerson.java
 * Description  A class used to both add and edit a person into the DB.
 * Course       CS 143
 * Hourse       1 hours and 12 minutes
 * Date         5/1/2021
  History Log   7/18/2018, 5/7/2020, 4/5/2021
 * @author	<i>Niko Culevski</i>
 * @version 	%1% %2%
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class AddPerson extends javax.swing.JDialog
{
    // class instance variables
    private Person myPerson = null;
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String city;
    private String state;
    private String zip;
    private final Color white = Color.WHITE;    // Default background color for input textfield
    private final Color pink = Color.PINK;      // Background color for bad input textfield
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Constructor  AddPerson()--default constructor
     * Description  Create an instance of the GUI form and sets icon image.
     *              Used for Add Person.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Niko Culevski</i>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public AddPerson()
    {
        initComponents();
        // Center the form
        this.setLocationRelativeTo(null);        
        // Set the default button
        this.getRootPane().setDefaultButton(addJButton);   
        // Center the form
        addJButton.setText("Edit");
        // Set the modal to true
        setModal(true);        
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Constructor  AddPerson()--overloaded constructor
     * Description  Create an instance of the GUI form and sets icon image.
     *              Used for Edit Person.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Niko Culevski</i>
     * @param       friend Person
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public AddPerson(Person friend)
    {
        this();     //call default constructor to cuild GUI
        myPerson = friend;
        firstJTextField.setText(friend.getFirstName());
        lastJTextField.setText(friend.getLastName());
        ageJTextField.setText(String.valueOf(friend.getAge()));
        addressJTextField.setText(friend.getAddress());
        cityJTextField.setText(friend.getCity());
        statesJComboBox.setSelectedItem(friend.getState());
        zipJFormattedTextField.setText(friend.getZip());
        titleJLabel.setText("Edit Person");        
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Constructor  AddPerson()--overloaded constructor
     * Description  Create an instance of the GUI form and sets icon image.
     *              Used for Edit Person.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Niko Culevski</i>
     * @param       parent Frame
     * @param       modal  boolean
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public AddPerson(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        // Center the form
        this.setLocationRelativeTo(null);        
        // Set the default button
        this.getRootPane().setDefaultButton(addJButton);                        
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method:      getPerson()
     * Description: Returns the person added or edited.
     * Date:        5/12/16
     * @author      Niko Culevski
     * @return      Person
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Person getPerson()
    {
        return myPerson;
    }    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

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
        quitJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Employee Add Form");
        setType(java.awt.Window.Type.UTILITY);

        titleJLabel.setFont(new java.awt.Font("Tempus Sans ITC", 2, 30)); // NOI18N
        titleJLabel.setForeground(new java.awt.Color(0, 102, 102));
        titleJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/address_small.jpg"))); // NOI18N
        titleJLabel.setText("Add Person");

        displayJPanel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        displayJPanel.setLayout(new java.awt.GridLayout(7, 2, 3, 3));

        firstJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        firstJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        firstJLabel.setText("First Name");
        displayJPanel.add(firstJLabel);

        firstJTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        firstJTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        firstJTextField.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                firstJTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                firstJTextFieldFocusLost(evt);
            }
        });
        displayJPanel.add(firstJTextField);

        lastJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lastJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lastJLabel.setText("Last Name");
        displayJPanel.add(lastJLabel);

        lastJTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lastJTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lastJTextField.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                lastJTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                lastJTextFieldFocusLost(evt);
            }
        });
        displayJPanel.add(lastJTextField);

        ageJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ageJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ageJLabel.setText("Age");
        displayJPanel.add(ageJLabel);

        ageJTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ageJTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ageJTextField.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                ageJTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                ageJTextFieldFocusLost(evt);
            }
        });
        displayJPanel.add(ageJTextField);

        addressJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addressJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addressJLabel.setText("Address");
        displayJPanel.add(addressJLabel);

        addressJTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addressJTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        displayJPanel.add(addressJTextField);

        cityJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cityJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cityJLabel.setText("City");
        displayJPanel.add(cityJLabel);

        cityJTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cityJTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cityJTextField.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                cityJTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                cityJTextFieldFocusLost(evt);
            }
        });
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

        try
        {
            zipJFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####")));
        } catch (java.text.ParseException ex)
        {
            ex.printStackTrace();
        }
        zipJFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        zipJFormattedTextField.setToolTipText("Exactly 5 digits");
        zipJFormattedTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        zipJFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                zipJFormattedTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                zipJFormattedTextFieldFocusLost(evt);
            }
        });
        displayJPanel.add(zipJFormattedTextField);

        controlJPanel.setLayout(new java.awt.GridLayout(1, 2, 3, 3));

        addJButton.setBackground(new java.awt.Color(255, 255, 204));
        addJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addJButton.setMnemonic('A');
        addJButton.setText("Add");
        addJButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(addJButton);

        quitJButton.setBackground(new java.awt.Color(255, 255, 204));
        quitJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quitJButton.setMnemonic('Q');
        quitJButton.setText("Quit");
        quitJButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                quitJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(quitJButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(controlJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addComponent(displayJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(28, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(titleJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
                .addComponent(controlJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(123, 123, 123)
                    .addComponent(displayJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(102, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       quitJButtonActionPerformed()
     * Description  Dispose the Add form. Uses the Validation class to
     *              validate input fields.
     * Date         4/26/2021
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Niko Culevski</i>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void quitJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_quitJButtonActionPerformed
    {//GEN-HEADEREND:event_quitJButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_quitJButtonActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       addJButtonActionPerformed()
     * Description  Add new person.
     * Date         4/26/2021
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Niko Culevski</i>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void addJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addJButtonActionPerformed
    {//GEN-HEADEREND:event_addJButtonActionPerformed
        String message = "Error!";
        try
        {
            // Get the first name and check to see if it is valid
            firstName = firstJTextField.getText();
            if(!Validation.isLettersOnly(firstName))
            {
                message = "Please enter a valid first name for the person.";
                firstJTextField.requestFocus();
                throw new IllegalArgumentException();
            }
            // Get the last name and check to see if it is valid
            lastName = lastJTextField.getText();
            if(!Validation.isLettersOnly(lastName))
            {
                message = "Please enter a valid last name for the person.";
                lastJTextField.requestFocus();
                throw new IllegalArgumentException();
            }
            // Get the age and check to see if it is valid
            if(!Validation.isNumeric(ageJTextField.getText()) || 
                    Integer.parseInt(ageJTextField.getText()) < 0)
            {
                message = "Please enter a valid integer age for the person";
                ageJTextField.requestFocus();
                throw new IllegalArgumentException();
            }
            age = Integer.parseInt(ageJTextField.getText());
            address = addressJTextField.getText();
            city = cityJTextField.getText();
            
            if(!Validation.isLettersOnly(city))
            {
                message = "Please enter a valid city for the person.";
                cityJTextField.requestFocus();
                throw new IllegalArgumentException();
            }
            state = statesJComboBox.getSelectedItem().toString();
            if(!Validation.isLettersOnly(city) || state.length() > 2)
            {
                message = "Please enter a valid 2-letters state for the person.";
                statesJComboBox.requestFocus();
                throw new IllegalArgumentException();
            }
            zip = zipJFormattedTextField.getText();
            if(!Validation.isValidZip(zip))
            {
                message = "Please enter a valid zip for the person.";
                zipJFormattedTextField.requestFocus();
                throw new IllegalArgumentException();
            }
            // create a new person and set the persons's information
            myPerson = new Person(0, firstName, lastName, age, address,
                    city, state, zip);

            // Close the form
            this.dispose();
        }
        catch(IllegalArgumentException exp)
        {
            JOptionPane.showMessageDialog(null, message, "Invalid Input", 
                    JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception exp)
        {
            // Show unusual error message
            throw new RuntimeException("Error reading input.");
        }
    }//GEN-LAST:event_addJButtonActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       zipJFormattedTextFieldFocusGained()
     * Description  Highlight zipJFormattedTextField as focus is gained.
     * @parem       java.awt.event.FocusEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History log  8/24/2016, 4/3/2020
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void zipJFormattedTextFieldFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_zipJFormattedTextFieldFocusGained
    {//GEN-HEADEREND:event_zipJFormattedTextFieldFocusGained
        zipJFormattedTextField.selectAll();
    }//GEN-LAST:event_zipJFormattedTextFieldFocusGained
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       zipJFormattedTextFieldFocusLost()
     * Description  Change color of zipJTextField according to valid input.
     * @parem       java.awt.event.FocusEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History log  8/24/2016, 4/3/2020
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
     * Method       firstJTextFieldFocusGained()
     * Description  Highlight firstJTextFieldFocusGained as focus is gained.
     * @parem       java.awt.event.FocusEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History log  8/24/2016, 4/3/2020
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void firstJTextFieldFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_firstJTextFieldFocusGained
    {//GEN-HEADEREND:event_firstJTextFieldFocusGained
        firstJTextField.selectAll();
    }//GEN-LAST:event_firstJTextFieldFocusGained
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       firstJTextFieldFocusLost()
     * Description  Change color of firstJTextField according to valid input.
     * @parem       java.awt.event.FocusEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History log  8/24/2016, 4/3/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void firstJTextFieldFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_firstJTextFieldFocusLost
    {//GEN-HEADEREND:event_firstJTextFieldFocusLost
        String input = firstJTextField.getText();
        if(Validation.isValidName(input))
            firstJTextField.setBackground(white);
        else
            firstJTextField.setBackground(pink);
    }//GEN-LAST:event_firstJTextFieldFocusLost
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       lastJTextFieldFocusGained()
     * Description  Highlight lastJTextField as focus is gained.
     * @parem       java.awt.event.FocusEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History log  8/24/2016, 4/3/2020
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void lastJTextFieldFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_lastJTextFieldFocusGained
    {//GEN-HEADEREND:event_lastJTextFieldFocusGained
        lastJTextField.selectAll();
    }//GEN-LAST:event_lastJTextFieldFocusGained
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       lastJTextFieldFocusLost()
     * Description  Change color of lastJTextField according to valid input.
     * @parem       java.awt.event.FocusEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History log  8/24/2016, 4/3/2020
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void lastJTextFieldFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_lastJTextFieldFocusLost
    {//GEN-HEADEREND:event_lastJTextFieldFocusLost
        String input = lastJTextField.getText();
        if(Validation.isValidName(input))
            lastJTextField.setBackground(white);
        else
            lastJTextField.setBackground(pink);
    }//GEN-LAST:event_lastJTextFieldFocusLost
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       ageJTextFieldFocusGained()
     * Description  Highlight ageJTextField as focus is gained.
     * @parem       java.awt.event.FocusEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History log  8/24/2016, 4/3/2020
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void ageJTextFieldFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_ageJTextFieldFocusGained
    {//GEN-HEADEREND:event_ageJTextFieldFocusGained
        ageJTextField.selectAll();
    }//GEN-LAST:event_ageJTextFieldFocusGained
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       ageJTextFieldFocusLost()
     * Description  Change color of lastJTextField according to valid input.
     * @parem       java.awt.event.FocusEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History log  8/24/2016, 4/3/2020
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void ageJTextFieldFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_ageJTextFieldFocusLost
    {//GEN-HEADEREND:event_ageJTextFieldFocusLost
        String input = ageJTextField.getText();
        if(Validation.isInteger(input, 5, 140))     //valid age: [5,140]
            ageJTextField.setBackground(white);
        else
            ageJTextField.setBackground(pink);
    }//GEN-LAST:event_ageJTextFieldFocusLost
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       cityJTextFieldFocusGained()
     * Description  Highlight cityJTextField as focus is gained.
     * @parem       java.awt.event.FocusEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History log  8/24/2016, 4/3/2020
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void cityJTextFieldFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_cityJTextFieldFocusGained
    {//GEN-HEADEREND:event_cityJTextFieldFocusGained
        cityJTextField.selectAll();
    }//GEN-LAST:event_cityJTextFieldFocusGained
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       cityJTextFieldFocusLost()
     * Description  Change color of cityJTextField according to valid input.
     * @parem       java.awt.event.FocusEvent
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History log  8/24/2016, 4/3/2020
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void cityJTextFieldFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_cityJTextFieldFocusLost
    {//GEN-HEADEREND:event_cityJTextFieldFocusLost
        String input = cityJTextField.getText();
        if(Validation.isValidName(input))
            cityJTextField.setBackground(white);
        else
            cityJTextField.setBackground(pink);
    }//GEN-LAST:event_cityJTextFieldFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addJButton;
    private javax.swing.JLabel addressJLabel;
    private javax.swing.JTextField addressJTextField;
    private javax.swing.JLabel ageJLabel;
    private javax.swing.JTextField ageJTextField;
    private javax.swing.JLabel cityJLabel;
    private javax.swing.JTextField cityJTextField;
    private javax.swing.JPanel controlJPanel;
    private javax.swing.JPanel displayJPanel;
    private javax.swing.JLabel firstJLabel;
    private javax.swing.JTextField firstJTextField;
    private javax.swing.JLabel lastJLabel;
    private javax.swing.JTextField lastJTextField;
    private javax.swing.JButton quitJButton;
    private javax.swing.JLabel stateJLabel;
    private javax.swing.JComboBox<String> statesJComboBox;
    private javax.swing.JLabel titleJLabel;
    private javax.swing.JFormattedTextField zipJFormattedTextField;
    private javax.swing.JLabel zipJLabel;
    // End of variables declaration//GEN-END:variables
}
