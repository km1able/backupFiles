/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posttoinfixtree;

/**
 *
 * @author suzy
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PostToInfixGUI extends JFrame implements ActionListener {

    //labels panels and text field variables
    private JTextField userInput;
    private JLabel inputDescLbl;
    private JLabel resultLbl;
    private JPanel inputPanel;
    private JPanel resultPanel;

    // Constructor
    private PostToInfixGUI() {
        //GUI Setup
         super("Tree Address Generator");
         inputPanel = new JPanel(new FlowLayout());
         resultPanel = new JPanel(new FlowLayout());
         setLayout(new GridLayout(2, 1));
         setSize(300,200);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         
         userInput = new JTextField(20);
         
         inputDescLbl = new JLabel("Enter Postfix Expression:");
         resultLbl = new JLabel("Infix Expression:", SwingConstants.LEFT);

         add(inputPanel);
         add(resultPanel);

         inputPanel.add(inputDescLbl);
         inputPanel.add(userInput);
         inputPanel.add(evlBtn);
         resultPanel.add(resultLbl);
         
         //action button listener
         evlBtn.addActionListener(this);         
    }
    
    //Button's set of instructions
    private JButton evlBtn = new JButton(new AbstractAction("Construct Tree"){
        @Override
        public void actionPerformed(ActionEvent constructTree) {
            PostToInfixTree postToIn = new PostToInfixTree();
            char[] input = userInput.getText().replace(" ","").toCharArray();
            Node root = postToIn.constructTree(input);
            resultLbl.setText("Infix Expression: " + root);
        }
    });

   @Override
    public void actionPerformed(ActionEvent e) {
      
    }
    
    // Start GUI
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new PostToInfixGUI().setVisible(true);
        });         
    }
}


