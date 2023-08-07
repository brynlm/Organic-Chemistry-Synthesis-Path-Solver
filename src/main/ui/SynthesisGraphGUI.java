package ui;

import model.FunctionalGroup;
import model.SynthesisGraph;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class SynthesisGraphGUI extends JFrame implements ActionListener {
    private SynthesisGraph graph;
    private ReactionProductsManager rxnMenu;
    private MainMenuPanel mp;
    private ProductsPane products;

    private JMenuItem addGroupButton;


    public SynthesisGraphGUI() {
        super("Synthesis Graph");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        addGroupButton = new JMenuItem("Add new reactant");
        addGroupButton.addActionListener(this);

        graph = new SynthesisGraph("new_graph");
        mp = new MainMenuPanel();
        products = new ProductsPane(this.graph);
        rxnMenu = new ReactionProductsManager(this, this.products, this.graph);

        add(mp, BorderLayout.PAGE_START);
        add(rxnMenu, BorderLayout.LINE_START);
        add(products, BorderLayout.LINE_END);

        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setMinimumSize(new Dimension(scrn.width / 2, scrn.height / 2));

        centreOnScreen(); // place JFrame window at center of laptops screen
        setVisible(true); // overrides Component.setVisible() method

    }

    // EFFECTS: creates and adds new functional group to this.group. Method is called from rxnMenu.
    public void addNewGroup(String name) {
        this.graph.addGroup(new FunctionalGroup(name));
    }

    public void addNewPathway(String reactant, String newPath) {
        FunctionalGroup group;
        try {
            group = graph.getGroupByName(reactant);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        group.addPathway(newPath);
    }


    public void actionPerformed(ActionEvent ae) {
//        FunctionalGroup newGroup = new FunctionalGroup("test name");
//        graph.addGroup(newGroup);
//        rxnMenu.addNewGroup(newGroup.getName());
    }

    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    public static void main(String[] args) {
        new SynthesisGraphGUI();
    }
}


