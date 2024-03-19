package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JCheckBox;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;

public class ParametresView extends JPanel{
    private MainFrame frame;
    public ParametresView(MainFrame frame){
        this.frame = frame;
        setLayout(new BorderLayout());        

        JPanel squarePanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.blue);
                g.fillRect(0, 0, getWidth(), getHeight()); // Fill the entire panel
            }
        };
        squarePanel.setLayout(new BorderLayout(1, 2)); // So we can add components to it

        JPanel leftSide = new JPanel() {
            DefaultListModel<String> listModel = new DefaultListModel<>();
            JList<String> list = new JList<>(listModel);
            {
                listModel.addElement("Item 1");
                listModel.addElement("Item 2");
                listModel.addElement("Item 3");
                
                list.setFont(new Font("Arial", Font.PLAIN, 20));
                list.setForeground(Color.white);
                list.setBackground(Color.green);
                add(list);
            }

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.green);
                g.fillRect(0, 0, this.getWidth(), this.getHeight()); // Fill the entire panel
            }
        }; 

            JCheckBox checkbox1 = new JCheckBox("Option 1");
            
            JCheckBox checkbox2 = new JCheckBox("Option 2");

            JCheckBox checkbox3 = new JCheckBox("Option 3");
         
            JCheckBox[] list = new JCheckBox[3];
            list[0] = checkbox1;
            list[1] = checkbox2;
            list[2] = checkbox3;
        JPanel rightSide = new JPanel() {
            {   
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                add(list[0]);
                add(list[1]);
                add(list[2]);
            }

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.yellow);
                g.fillRect(0, 0, getWidth(), getHeight()); // Fill the entire panel
            }
        };

        squarePanel.add(leftSide, BorderLayout.WEST);
        squarePanel.add(rightSide, BorderLayout.EAST);

        JPanel bottomButtons = new JPanel();
        bottomButtons.setLayout(new BorderLayout());
        JButton retour = new JButton("Retour");
        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Retour button clicked");
                for (JCheckBox elem : list) {
                    if (elem.isSelected()) {
                        System.out.println(elem.getText());
                    
                    }
                }
                frame.dispose();
            }
        });

        bottomButtons.add(retour);

        // Add some elements to the squarePanel

        squarePanel.add(bottomButtons, BorderLayout.SOUTH);

        this.add(squarePanel);

        frame.add(this); // Add the mainPanel to the frame
        frame.setVisible(true); // Make the frame visible
    }
}
