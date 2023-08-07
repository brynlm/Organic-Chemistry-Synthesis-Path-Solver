package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class SynthesisGraphGUI extends JFrame implements ActionListener {
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;

    private ReactionProductsManager rxnMenu;

    private MainMenuPanel mp;
    private JMenu reactants;
    private JMenuBar menuBar;
    private JMenuItem menuItem;

    public SynthesisGraphGUI() {
        super("Synthesis Graph");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

//        JButton testButton = new JButton("test button");
//        testButton.setLocation(50, 50);
//        add(testButton);

//        b1 = new JButton("Load Graph Data");
//        b2 = new JButton("Save Graph");
//        b3 = new JButton("Edit Graph"); // may not need this button
//        b4 = new JButton("Find Route");
//        b1.addActionListener(this);
//        add(b1);
//        add(b2);
//        add(b3);
//        add(b4);

        mp = new MainMenuPanel();
        rxnMenu = new ReactionProductsManager();
        add(mp, BorderLayout.PAGE_START);
//        add(rxnMenu);
//        rxnMenu.setVisible(false);

//        menuBar = new JMenuBar();
//        reactants = new JMenu("List of Reactants:");
//        menuItem = new JMenuItem("Test JMenuItem?");
//        reactants.add(menuItem);
//        menuBar.add(reactants);
////
//        menuBar.setSize(10, 10);
//        menuBar.setLocation(50,50);
////        menuBar.add(b1);
////        menuBar.add(b2);
////        menuBar.add(b3);
////        menuBar.add(b4);
////        menuBar.setBackground(Color.BLACK);
//        add(menuBar);
//        setJMenuBar(menuBar);

//        menuBar.setBackground(Color.BLACK);

        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setMinimumSize(new Dimension(scrn.width / 2, scrn.height / 2));

        centreOnScreen(); // place JFrame window at center of laptops screen
        setVisible(true); // overrides Component.setVisible() method

    }

    public void actionPerformed(ActionEvent ae) {
        ;//stub
    }

    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    public static void main(String[] args) {
        new SynthesisGraphGUI();
    }
}


