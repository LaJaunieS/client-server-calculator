package app;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import edu.uweo.java2.client.Client;
import edu.uweo.java2.guiButtons.AbstractButton;
import edu.uweo.java2.guiButtons.AddButton;
import edu.uweo.java2.guiButtons.ButtonReceiver;
import edu.uweo.java2.guiButtons.DivButton;
import edu.uweo.java2.guiButtons.MulButton;
import edu.uweo.java2.guiButtons.SubButton;

public class GUI implements Runnable {

    /*The components, initalized in prepareLayout()*/
    /*The buttons*/
     private AddButton addButton_;
     private SubButton subButton_;
     private MulButton mulButton_;
     private DivButton divButton_;
     
     /*The textBox labels*/
     private JLabel value1Label_;
     private JLabel value2Label_;
     private JLabel resultLabel_;
     
     /*The input boxes*/
     private JTextField value1TextBox;
     private JTextField value2TextBox;
     private JLabel resultsDisplay;
         
     /*The container panels*/
     private JPanel mainPanel;
     private JPanel valuePanel;
     private JPanel resultsPanel;
     private JPanel buttonPanel;
     
     private Client client = new Client(4885);
     
     public static void main(String[] args) {
        GUI gui = new GUI();
        SwingUtilities.invokeLater( gui );
        }
     
     @Override
     public void run() {
         createGUI();
     }
    
     private void createGUI() {
        JFrame frame = new JFrame("Value Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prepareLayout(frame);
        frame.setLocationRelativeTo(null);
        
        ButtonListener listener = new ButtonListener();
        addButton_.addActionListener(listener);
        subButton_.addActionListener(listener);
        mulButton_.addActionListener(listener);
        divButton_.addActionListener(listener);
        
        frame.setVisible(true);
    }
    
    private void prepareLayout(JFrame frame) {
        /*Initialize the components*/
        
        /*The input boxes*/
        value1TextBox = new JTextField("0");
        value2TextBox = new JTextField("0");
        resultsDisplay = new JLabel();
       
        /*The buttons*/
        addButton_ = new AddButton("Add",
                        Double.parseDouble(value1TextBox.getText()),
                        Double.parseDouble(value2TextBox.getText()));
        subButton_ = new SubButton("Subtract",
                        Double.parseDouble(value1TextBox.getText()),
                        Double.parseDouble(value2TextBox.getText()));
        mulButton_ = new MulButton("Multiply",
                        Double.parseDouble(value1TextBox.getText()),
                        Double.parseDouble(value2TextBox.getText()));
        divButton_ = new DivButton("Divde",
                        Double.parseDouble(value1TextBox.getText()),
                        Double.parseDouble(value2TextBox.getText()));
        /*The textBox labels*/
        value1Label_ = new JLabel("Value 1");
        value2Label_ = new JLabel("Value 2");
        resultLabel_ = new JLabel("Result");
        
        
        /*The containers*/
        mainPanel = new JPanel(new GridLayout(3,1,3,3));
        valuePanel = new JPanel(new GridLayout(1,4,3,3));
        resultsPanel = new JPanel(new GridLayout(1,2,3,3));
        buttonPanel = new JPanel(new GridLayout(1,4,3,3));
        
        GridBagConstraints mpc = new GridBagConstraints();
        mpc.insets = new Insets(25,25,25,25);
        
        /*Add the components*/
        Container container = frame.getContentPane();
        
        
        valuePanel.add(value1Label_, mpc);
        valuePanel.add(value1TextBox,mpc);
        valuePanel.add(value2Label_,mpc);
        valuePanel.add(value2TextBox,mpc);
        
        resultsPanel.add(resultLabel_);
        resultsPanel.add(resultsDisplay);
        
        buttonPanel.add(addButton_,mpc);
        buttonPanel.add(subButton_,mpc);
        buttonPanel.add(mulButton_,mpc);
        buttonPanel.add(divButton_,mpc);
        
        mainPanel.add(valuePanel,mpc);
        mainPanel.add(resultsPanel,mpc);
        mainPanel.add(buttonPanel,mpc);
    
        Dimension defaultSize = new Dimension(500,50);
        mainPanel.setPreferredSize(new Dimension(500,175));
        container.setLayout(new GridBagLayout());
        container.add(mainPanel,mpc);
        
        frame.pack();
    }
    
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            /*when a particular button is pressed, call corresponding commands-
             * call client.execute(command)?
             * parse value1 and value2 values as command parameters
             * setText of resultText to command.getResult()
             */
            AbstractButton obj = (AbstractButton) evt.getSource();
            ButtonReceiver buttonReceiver = new ButtonReceiver();
            Double value1 = Double.parseDouble(value1TextBox.getText());
            Double value2 = Double.parseDouble(value2TextBox.getText());
            
            String serverResponse = null;
            
            obj.setValue1(value1);
            obj.setValue2(value2);
            obj.setReceiver(buttonReceiver);
            /*obj.execute will overload abstract method with appropriate button obj
             * and return a string that is the serverResponse/calculation result*/
            serverResponse = obj.execute();
            resultsDisplay.setText(serverResponse);
            
        }
        
    }

}
