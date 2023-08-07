package ui;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class ReactionProductsManager extends JPanel implements ActionListener {
    private JMenuItem menuItem; // represents single reactant (instantiation will be automated later)
    private JMenu menu;

    public ReactionProductsManager() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();

        menu = new JMenu("List of Reactants:");
        menuItem = new JMenuItem("Test JMenuItem?");
        menu.add(menuItem);
        menu.setSize(new Dimension(10, 10));
        add(menu);
//        menu.setLocation((scrn.width - getWidth()) / 2, 15);
        ;//stub
    }

    public void actionPerformed(ActionEvent ae) {
        ;//stub
    }


}
