/**
 * Author: Kevin Abrahams
 * Date: June 25, 2020
 * Class Description: This is the main class of the application
 * containing the GUI to run the program.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Main implements ActionListener {
    private JTextField outPutField;// Output field
    private JTextField treeField;// Tree expression field
    private BinaryTree tree;// Binary tree

    /**
     * Main method of the application
     *
     * @param args - Array of the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Main main = new Main();
        main.tree = new BinaryTree();
        main.initComponents(frame);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Initialize the component
     *
     * @param frame - Main frame
     */
    private void initComponents(JFrame frame) {
        JPanel mainPanel = new JPanel();
        JPanel expressionPanel = new JPanel();
        JLabel treeLabel = new JLabel();
        treeField = new JTextField();
        JPanel buttonPanel = new JPanel();
        JButton b1 = new JButton();
        JButton b2 = new JButton();
        JButton b3 = new JButton();
        JButton b4 = new JButton();
        JButton b5 = new JButton();
        JButton b6 = new JButton();
        JButton b7 = new JButton();
        JPanel resultPanel = new JPanel();
        JLabel outPutLabel = new JLabel();
        outPutField = new JTextField();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Binary Tree Category");
        frame.getContentPane().setLayout(new GridLayout(1, 1));
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(1014, 150));
        mainPanel.setLayout(new GridLayout(3, 1));

        expressionPanel.setLayout(null);
        Font font = new Font("Arial", 1, 15);

        treeLabel.setFont(font);
        treeLabel.setText("Enter tree");
        expressionPanel.add(treeLabel);
        treeLabel.setBounds(160, 2, 140, 40);
        expressionPanel.add(treeField);
        treeField.setBounds(310, 2, 420, 40);

        mainPanel.add(expressionPanel);

        buttonPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        buttonPanel.setLayout(new GridLayout(1, 7, 10, 0));

        b1.setFont(font);
        b1.setText("Make Tree");
        b1.setActionCommand("1");
        b1.addActionListener(this);
        buttonPanel.add(b1);

        b2.setFont(font);
        b2.setText("is Balanced?");
        b2.setActionCommand("2");
        b2.addActionListener(this);
        buttonPanel.add(b2);

        b3.setFont(font);
        b3.setText("is Full?");
        b3.setActionCommand("3");
        b3.addActionListener(this);
        buttonPanel.add(b3);

        b4.setFont(font);
        b4.setText("is Proper?");
        b4.setActionCommand("4");
        b4.addActionListener(this);
        buttonPanel.add(b4);

        b5.setFont(font);
        b5.setText("Height");
        b5.setActionCommand("5");
        b5.addActionListener(this);
        buttonPanel.add(b5);

        b6.setFont(font);
        b6.setText("Nodes");
        b6.setActionCommand("6");
        b6.addActionListener(this);
        buttonPanel.add(b6);

        b7.setFont(font);
        b7.setText("Inorder");
        b7.setActionCommand("7");
        b7.addActionListener(this);
        buttonPanel.add(b7);

        mainPanel.add(buttonPanel);

        resultPanel.setLayout(null);

        outPutLabel.setFont(font);
        outPutLabel.setText("Output");
        resultPanel.add(outPutLabel);
        outPutLabel.setBounds(160, 2, 140, 40);
        resultPanel.add(outPutField);
        outPutField.setBounds(310, 2, 420, 40);

        mainPanel.add(resultPanel);

        frame.getContentPane().add(mainPanel);

        frame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "1":
                try {
                    tree.makeTree(treeField.getText());
                } catch (InvalidTreeSyntax e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "2": {
                boolean isBalanced = tree.isBalanced();
                String message = "Tree is balanced";
                if (!isBalanced)
                    message = "Tree is not balanced";
                outPutField.setText(message);
            }
            break;
            case "3": {
                boolean isFull = tree.isFull();
                String message = "Tree is full";
                if (!isFull)
                    message = "Tree is not full";
                outPutField.setText(message);
            }
            break;
            case "4": {
                boolean isProper = tree.isProper();
                String message = "Tree is proper";
                if (!isProper)
                    message = "Tree is not proper";
                outPutField.setText(message);
            }
            break;
            case "5":
                outPutField.setText("Height of the tree:" + tree.getHeight());
                break;
            case "6":
                outPutField.setText("Nodes in the tree:" + tree.getNodes());
                break;
            case "7":
                outPutField.setText(tree.inorder());
                break;
            default:
                break;
        }

    }
}