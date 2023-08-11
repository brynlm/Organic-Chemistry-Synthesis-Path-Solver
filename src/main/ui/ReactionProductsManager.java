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
    private JTextField textField;
    private JTextField titleField;
    private JToolBar reactants;
    private DefaultListModel listModel;

    public ReactionProductsManager(SynthesisGraphGUI gui, ProductsPane productsPane) {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        this.gui = gui;
        this.productsPane = productsPane;
        setLayout(new BorderLayout());

//        reactants = new JList();
//        this.menuItem = new JMenuItem("Add New Group");
        textField = new JTextField("Add new Reactant");
        textField.addActionListener(this);
        titleField = new JTextField("Reactants:");
        titleField.setEditable(false);

//        menuBar = new JMenuBar();
//        listModel = new DefaultListModel();
//        listModel.addAll(graph.getGroups());
//        reactants.setModel(listModel);
        reactants = new JToolBar(JToolBar.VERTICAL);


//        init();

//        this.menuItem.setAction(new AddNewReactantAction("Add new reactant"));
//        reactants.add(textField);

//        menuBar.add(reactants);

//        JMenu submenu = new JMenu("reactant 1");
//        submenu.add(new JMenuItem("list of reaction products..."));
//        reactants.add(submenu);

        add(titleField, BorderLayout.PAGE_START);
        add(reactants, BorderLayout.CENTER);
        add(textField, BorderLayout.PAGE_END);

    }

    public void loadGraph(SynthesisGraph graph) {
//        this.graph = graph;
        reactants.removeAll();
        for (FunctionalGroup g : graph.getGroups()) {
            addNewGroup(g.getName());
        }
    }


    // EFFECTS: adds new functional group to list of reactants.
    // need to add action to each newly added reactant, to list the reactants pathways
    public void addNewGroup(String name) {
        JButton newGroup = new JButton(new SetPathwayViewAction(name, productsPane));
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
