package ui;

import java.util.Scanner;

import model.FunctionalGroup;
import model.SynthesisGraph;

public class SynthesisGraphApp {
    private SynthesisGraph graph;
    private Scanner input;

    public SynthesisGraphApp() {
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
        } else if (command.equals("s")) {
            doSolveRoute();
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
    
    private void doAddPaths() {
        int i;
        String command = null;
        FunctionalGroup node;
        boolean keepAdding = true;
        System.out.println("select which group to modify (0-" + Integer.toString(graph.getGroups().size()) + "):");
        for (i = 0; i < graph.getGroups().size(); i++) {
            System.out.println("\t" + Integer.toString(i) + ". " + graph.getGroups().get(i).getName());
        }
        command = input.next();
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
        System.out.println("\ts -> solve route");
        System.out.println("\tq -> quit");
    }
}
