package ui;

import model.SynthesisGraph;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new SynthesisGraphApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
