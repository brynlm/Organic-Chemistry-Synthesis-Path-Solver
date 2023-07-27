package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.*;
import model.SynthesisGraph;
import model.FunctionalGroup;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonReaderTest {
    private SynthesisGraph graph;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/synthesisgraph.json";

    @BeforeEach
    public void RunBefore() {
        this.jsonReader = new JsonReader(JSON_STORE);
    }

    @Test
    public void readTest() {
        try {
            graph = jsonReader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String groups = graph.getGroups().toString();
        System.out.println(groups);
        List<String> path = graph.findPathway("a", "c");
        assertEquals("[a, b, c]", path.toString());
    }

}
