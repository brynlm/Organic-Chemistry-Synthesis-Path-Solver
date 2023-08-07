package ui;

import java.awt.*;
import model.SynthesisGraph;
import model.FunctionalGroup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import ui.SetPathwayViewAction;
import javax.swing.*;

public class ReactionProductsManager extends JPanel implements ActionListener {
    private SynthesisGraphGUI gui;
    private ProductsPane productsPane;

    private SynthesisGraph graph;

    private JTextField textField; // represents single reactant (instantiation will be automated later)
    private JMenu reactants;
    private JMenuBar menuBar;

    public ReactionProductsManager(SynthesisGraphGUI gui, ProductsPane productsPane) {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        this.gui = gui;
        this.productsPane = productsPane;

        reactants = new JMenu("List of Reactants:");
//        this.menuItem = new JMenuItem("Add New Group");
        textField = new JTextField("Add new Reactant");
        textField.addActionListener(this);

        menuBar = new JMenuBar();

//        this.menuItem.setAction(new AddNewReactantAction("Add new reactant"));
        reactants.add(textField);

        menuBar.add(reactants);

//        JMenu submenu = new JMenu("reactant 1");
//        submenu.add(new JMenuItem("list of reaction products..."));
//        reactants.add(submenu);

        add(menuBar);

    }

    // EFFECTS: adds new functional group to list of reactants.
    // need to add action to each newly added reactant, to list the reactants pathways
    public void addNewGroup(String name) {
        JMenuItem newGroup = new JMenuItem(new SetPathwayViewAction(name, productsPane));
        reactants.add(newGroup);


    }

    // EFFECTS: New functional group is added to graph object by GUI, and also added to reactants JMenu object.
    public void actionPerformed(ActionEvent ae) {
//        String name = "NewGroup Test";
        String name = textField.getText();
        addNewGroup(name);
        this.gui.addNewGroup(name);
    }


}
