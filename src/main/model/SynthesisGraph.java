package model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// keeps record of FunctionalGroup objects comprising the graph.
// contains methods for adding FunctionalGroup objects to the SynthesisGraph,
// and for finding synthetic routes connecting two functional groups.
public class SynthesisGraph {
    private String title;
    private List<FunctionalGroup> funcGroups;
    private List<List<String>> result;

    /* EFFECTS: initializes SynthesisGraph object.
       sets title field to title, and
       initializes nodes as empty list.          */
    public SynthesisGraph(String title) {
        this.title = title;
        this.funcGroups = new ArrayList<>();
        this.result = new ArrayList<>();
    }

    // REQUIRES: funcGroups list contains objects with names matching the arguments.
    public List<String> findPathway(String f1, String f2) {
        this.result = new ArrayList<>();
        List<String> todo = new ArrayList<>();
        List<String> path = new ArrayList<>();
        List<String> visited = new ArrayList<>();
        List<List<String>> pathwl = new ArrayList<>();


        // First just return whether not object found; then
        // find way to record and return visited path; then
        //  search for shortest route

        try {
            List<String> pathway = searchFG(getGroupByName(f1), f2, todo, path, visited, pathwl);
            return pathway;
        } catch (NoMatchingGroup err) {
            System.out.println("no groups with those names!");
        }


        return path; //stub
    }


    public List<String> searchFG(FunctionalGroup f, String end, List<String> todo,
                                 List<String> path, List<String> visited,
                                 List<List<String>> pathwl) {
//        List<List<String>> result = new ArrayList<>(); // REFACTOR TEST
        if (f.getName().equals(end)) { // did you find it?
            path.add(end);
            this.result.add(path);
//            result.add(path);
            if (pathwl.size() != 0) {
                int index1 = todo.size() - 1;
                int index2 = pathwl.size() - 1;
//                String f1 = todo.get(index1);
                path = pathwl.get(index2);
                visited = copyList(1, path).get(0);
                searchPaths(end, todo, path, visited, pathwl);
            } else {
                return getShortest(result);
            }
        } else if (visited.contains(f.getName())) { // no? then check if already visited
            //yes? then continue through unsearched paths
            searchPaths(end, todo, path, visited, pathwl); // "take one off the top (path-wl)"

        } else { //no? then add to visited and add same-level paths to to-do list. Copy visited by # nodes add to pathwl
            int size = f.getPathways().size();
            todo.addAll(f.getPathways());
            path.add(f.getName());
            visited.add(f.getName());
            pathwl.addAll(copyList(size, path));
            searchPaths(end, todo, path, visited, pathwl);
        }

        return getShortest(this.result);
//        return getShortest(result);
    }

    public List<String> searchPaths(String end, List<String> todo, List<String> path,
                               List<String> visited, List<List<String>> pathwl) {

        if (pathwl.size() == 0 && todo.size() == 0) { // reached a dead end?
            return getShortest(this.result);
        } else {
            int index1 = todo.size() - 1;
            int index2 = pathwl.size() - 1;
            String f = todo.get(index1);
            path = pathwl.get(index2);
            todo.remove(index1);   // "take one off the top (to-do)"
            pathwl.remove(index2);
            try {
                searchFG(getGroupByName(f), end, todo, path, visited, pathwl);
            } catch (NoMatchingGroup err) {
                System.out.println("No groups found with that name!");
            }
        }

        return getShortest(this.result);
    }

    public List<String> getShortest(List<List<String>> result) {
        List<String> shortest = result.get(0);

        for (List<String> los : result) {
            if (shortest.size() > los.size()) {
                shortest = los;
            }
        }
        return shortest;
    }

    public FunctionalGroup getGroupByName(String name) throws NoMatchingGroup {
        for (FunctionalGroup f : this.funcGroups) {
            if (f.getName().equals(name)) {
                return f;
            }
        }
        throw new NoMatchingGroup();
    }

    class NoMatchingGroup extends Exception {
        public NoMatchingGroup() {
        }
    }


    // returns a list of identical list objects. n is number of copies made
    public List<List<String>> copyList(int n, List<String> visited) {
        List<List<String>> copies = new ArrayList<>();
        int i;
        for (i = 0; i < n; i++) {
            List<String> copy = new ArrayList<>();
            copy.addAll(visited);
            copies.add(copy);
        }
        return copies;
    }


    // REQUIRES: a different FunctionalGroup object with the same name was NOT added previously
    //           (FunctionalGroups with same name are not allowed).
    // MODIFIES: this
    // EFFECTS: adds functional group to funcGroups list + logs event.
    public void addGroup(FunctionalGroup f) {
        this.funcGroups.add(f);
        EventLog.getInstance().logEvent(new Event("New functional group created: " + f.getName()));
    }

    public List<FunctionalGroup> getGroups() {
        return this.funcGroups;
    }

    public void setTitle(String title) {
        this.title = title;
        EventLog.getInstance().logEvent(new Event("Title has been changed: " + this.title));
    }

    public String getTitle() {
        return this.title;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", this.title);
        json.put("funcGroups", groupsToJson());
        return json;
    }

    private JSONArray groupsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (FunctionalGroup f : this.funcGroups) {
            jsonArray.put(f.toJson());
        }
        return jsonArray;
    }
}
