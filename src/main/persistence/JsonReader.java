package persistence;

import model.FunctionalGroup;
import model.SynthesisGraph;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// reads and loads SynthesisGraph data from json file
public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SynthesisGraph read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGraph(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses data from JSON object and returns as Graph.
    private SynthesisGraph parseGraph(JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        SynthesisGraph sg = new SynthesisGraph(title);
        addFuncGroups(sg, jsonObject);
        return sg;
    }

    // MODIFIES: wr
    // EFFECTS: parses funcGroups from JSON object and adds them to workroom
    private void addFuncGroups(SynthesisGraph sg, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("funcGroups");
        for (Object json : jsonArray) {
            JSONObject nextGroup = (JSONObject) json;
            addGroup(sg, nextGroup);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses paths from JSON object and adds it to workroom
    private void addGroup(SynthesisGraph sg, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray jsonArray = jsonObject.getJSONArray("paths");
        FunctionalGroup g = new FunctionalGroup(name);
        for (Object s : jsonArray) {
            g.addPathway(s.toString());
        }
        sg.addGroup(g);
    }
}





