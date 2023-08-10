package ui;

import model.EventLog;
import model.Event;
import model.FunctionalGroup;
import model.SynthesisGraph;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

public class SynthesisGraphGUI extends JFrame implements ActionListener, WindowListener {
    private SynthesisGraph graph;
    private ReactionProductsManager rxnMenu;
    private MainMenuPanel mp;
    private ProductsPane products;

    private JMenuItem addGroupButton;
    private static final String JSON_STORE = "./data/synthesisgraph.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    public SynthesisGraphGUI() {
        super("Synthesis Graph");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        addGroupButton = new JMenuItem("Add new reactant");
        addGroupButton.addActionListener(this);

        graph = new SynthesisGraph("new_graph");
        mp = new MainMenuPanel(this);
        products = new ProductsPane(this.graph);
        rxnMenu = new ReactionProductsManager(this, this.products, this.graph);

        add(mp, BorderLayout.PAGE_START);
        add(rxnMenu, BorderLayout.LINE_START);
        add(products, BorderLayout.LINE_END);

        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setMinimumSize(new Dimension(scrn.width / 2, scrn.height / 2));

        centreOnScreen(); // place JFrame window at center of laptops screen
        setVisible(true); // overrides Component.setVisible() method

        addWindowListener(this);

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

    public void saveGraph() {
        try {
            jsonWriter.open();
            jsonWriter.write(graph);
            jsonWriter.close();
            System.out.println("Saved " + graph.getTitle() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadGraph() {
        try {
            graph = jsonReader.read();
            System.out.println("Loaded " + graph.getTitle() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        // update ProductsPane and ReactionProductsManager fields with new graph.
        products.setGraph(graph);
        rxnMenu.loadGraph(graph);
    }

    public void windowClosed(WindowEvent e) {
        EventLog el = EventLog.getInstance();
        for (Event next: el) {
            System.out.println(next.toString());
        }
    }

    public void windowClosing(WindowEvent e) {
//        System.out.println("WindowListener method called: windowClosing");
        EventLog el = EventLog.getInstance();
        for (Event next: el) {
            System.out.println(next.toString());
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("Application opened");
    }

    public void windowIconified(WindowEvent e) {
        System.out.println("WindowListener method called: windowIconified.");
    }

    public void windowDeiconified(WindowEvent e) {
        System.out.println("WindowListener method called: windowDeiconified.");
    }

    public void windowActivated(WindowEvent e) {
        System.out.println("WindowListener method called: windowActivated.");
    }

    public void windowDeactivated(WindowEvent e) {
        System.out.println("WindowListener method called: windowDeactivated.");
    }


    public static void main(String[] args) {
        new SynthesisGraphGUI();
    }
}


