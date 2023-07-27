package model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;

// represents a functional group that forms a unique node on the SynthesisGraph.
// has fields defining the name and list of connected child nodes.
public class FunctionalGroup {
    private String name;
    private List<String> pathways;

    // EFFECTS: initializes FunctionalGroup object, sets this.name to name,
    //          and this.pathways to an empty list.
    public FunctionalGroup(String name) {
        this.name = name;
        this.pathways = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<String> getPathways() {
        return this.pathways;
    }

    // MODIFIES: this
    // EFFECTS: adds reaction product to list of potential pathways
    public void addPathway(String product) {
        this.pathways.add(product);
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray(this.pathways);
        json.put("name", this.name);
        json.put("paths", jsonArray);
        return json;
    }

}
