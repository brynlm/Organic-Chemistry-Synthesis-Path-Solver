package ui;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import model.FunctionalGroup;
import model.SynthesisGraph;
import persistence.JsonReader;
import persistence.JsonWriter;

public class SynthesisGraphApp {
    private SynthesisGraph graph;
    private Scanner input;
    private static final String JSON_STORE = "./data/synthesisgraph.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public SynthesisGraphApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE); //refactor to support saving/loading different multiple different files
        runGraphApp();
    }

    private void runGraphApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    private void processCommand(String command) {
        if (command.equals("r")) {
            doRename();
        } else if (command.equals("g")) {
            doAddGroups();
        } else if (command.equals("p")) {
            doAddPaths();
        } else if (command.equals("f")) {
            doSolveRoute();
        } else if (command.equals("s")) {
            saveGraph();
        } else if (command.equals("o")) {
            loadGraph();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void doSolveRoute() {
        int i;
        String start;
        String last;
        String result;
        String command = null;
        System.out.println("select initial reactant (0-" + Integer.toString(graph.getGroups().size()) + "):");
        for (i = 0; i < graph.getGroups().size(); i++) {
            System.out.println("\t" + Integer.toString(i) + ". " + graph.getGroups().get(i).getName());
        }
        command = input.next();
        i = Integer.parseInt(command);
        start = graph.getGroups().get(i).getName();
        System.out.println("select final product");
        command = input.next();
        i = Integer.parseInt(command);
        last = graph.getGroups().get(i).getName();
        System.out.println("\n" + graph.findPathway(start, last).toString());
    }

    // need to refactor to meet max line criteria
    private void doAddPaths() {
        int i;
        String command = null;
        FunctionalGroup node;
        boolean keepAdding = true;
        System.out.println("select which group to modify (0-" + Integer.toString(graph.getGroups().size() - 1) + "):");
        for (i = 0; i < graph.getGroups().size(); i++) {
            System.out.println("\t" + Integer.toString(i) + ". " + graph.getGroups().get(i).getName());
        }
        command = input.next();
        if (command == "q") {
            return;
        } else {
            node = graph.getGroups().get(Integer.parseInt(command));
            while (keepAdding) {
                System.out.println("select which paths to add to " + node.getName() + ", or q to quit");
                command = input.next();
                if (command.equals("q")) {
                    keepAdding = false;
                } else {
                    i = Integer.parseInt(command);
                    node.addPathway(graph.getGroups().get(i).getName());
                }
            }
        }
    }

    private void doAddGroups() {
        boolean keepAdding = true;
        String command = null;
        while (keepAdding) {
            System.out.println("enter name for new group. To quit, type q");
            command = input.next();
            if (command.equals("q")) {
                keepAdding = false;
            } else {
                graph.addGroup(new FunctionalGroup(command));
            }
        }
    }

    private void doRename() {
        String rename = "";
        System.out.println("enter new name for graph:");
        rename = input.next();
        graph.setTitle(rename);

    }

    private void saveGraph() {
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
    private void loadGraph() {
        try {
            graph = jsonReader.read();
            System.out.println("Loaded " + graph.getTitle() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    private void init() {
        graph = new SynthesisGraph("new_graph");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tr -> rename");
        System.out.println("\tg -> add functional group");
        System.out.println("\tp -> add paths to group");
        System.out.println("\tf -> solve route");
        System.out.println("\ts -> save graph");
        System.out.println("\to -> load graph from file");
        System.out.println("\tq -> quit");
    }
}
