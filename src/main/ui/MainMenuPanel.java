package ui;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;



// Represents the menu panel shown when GUI is started.
public class MainMenuPanel extends JPanel implements ActionListener {
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton testButton;

    private SynthesisGraphGUI gui;

    public MainMenuPanel(SynthesisGraphGUI gui) {
//        setPreferredSize(new Dimension(50, 50));
        setSize(new Dimension(50, 50));
        setBackground(Color.BLACK);
        setLayout(new FlowLayout());

        b1 = new JButton("Load Graph Data");
        b2 = new JButton("Save Graph");
        b3 = new JButton("Edit Graph"); // may not need this button
        b4 = new JButton("Find Route");
        this.gui = gui;



//        menuBar = new JMenuBar();
//        menu = new JMenu("List of Reactants:");
//        menuItem = new JMenuItem("Test JMenuItem?");
//        menu.add(menuItem);
//        menuBar.add(menu);
//        menu.setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);

        b1.addActionListener(this);
        b2.addActionListener(this);
        add(b1, BorderLayout.PAGE_START);
        add(b2,BorderLayout.PAGE_START);
        add(b3,BorderLayout.PAGE_START);
        add(b4,BorderLayout.PAGE_START);
//        add(menu);
//        add(menuBar);

    }

    public void actionPerformed(ActionEvent ae) {
        System.out.println(ae.getActionCommand());
        String command = ae.getActionCommand();
        if (command.equals("Load Graph Data")) {
            this.gui.loadGraph();
        } else if (command.equals("Save Graph")) {
            this.gui.saveGraph();
        }
    }
}
