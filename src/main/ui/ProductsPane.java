package ui;

import model.FunctionalGroup;
import model.SynthesisGraph;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.*;
import javax.swing.text.JTextComponent;

// each button within ReactionProductsManager should lead to a change in this Products Pane
public class ProductsPane extends JPanel implements ActionListener {
    private JTextField textField;
    private JList listProducts;
    private SynthesisGraphGUI gui;
    private JTextField nameField;
    private DefaultListModel listModel;
    private SynthesisGraph graph;

    public ProductsPane(SynthesisGraph graph) {
//        this.gui = gui;
        this.graph = graph; //ProductsPane and SynthesisGraphGUI should reference the same graph object.
        textField = new JTextField("Add new reaction product");
        textField.addActionListener(this);

        nameField = new JTextField("nameField");

        listModel = new DefaultListModel();
        listProducts = new JList(listModel);

        listProducts.setLayoutOrientation(JList.VERTICAL);

        setLayout(new BorderLayout());
        add(nameField, BorderLayout.NORTH);
        add(textField, BorderLayout.PAGE_START);
        add(listProducts, BorderLayout.CENTER);
    }

    private void addNewProduct(String name) {
        listModel.addElement(name);
    }

    public void actionPerformed(ActionEvent ae) {
        String newPath = textField.getText();
        String reactant = nameField.getText();
        addNewProduct(newPath);
//        gui.addNewPathway(reactant, newPath);
        try {
            graph.getGroupByName(reactant).addPathway(newPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Sets name field and list of pathways corresponding to group called from ReactionProductsManager
    public void setCurrentGroup(String name) {
        nameField.setText(name);
        FunctionalGroup fg;
        try {
            fg = graph.getGroupByName(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        DefaultListModel pathways = new DefaultListModel();
        pathways.addAll(fg.getPathways());
        listProducts.setModel(pathways);
    }

}
