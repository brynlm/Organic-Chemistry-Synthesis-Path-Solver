package model;


import java.util.ArrayList;
import java.util.List;

// keeps record of FunctionalGroup objects comprising the graph.
// contains methods for adding FunctionalGroup objects to the SynthesisGraph,
// and for finding synthetic routes connecting two functional groups.
public class SynthesisGraph {
    private String title;
    private List<FunctionalGroup> funcGroups;
    private List<String> result;

    /* EFFECTS: initializes SynthesisGraph object.
       sets title field to title, and
       initializes nodes as empty list.          */
    public SynthesisGraph(String title) {
        this.title = title;
        this.funcGroups = new ArrayList<>();
    }

    // REQUIRES: funcGroups list contains objects with names matching the arguments.
    public List<String> findPathway(String f1, String f2) {
        List<String> todo = new ArrayList<>();
        List<String> path = new ArrayList<>();
        List<String> visited = new ArrayList<>();
        List<List<String>> pathwl = new ArrayList<>();


        // First just return whether not object found; then
        // find way to record and return visited path; then
        //  search for shortest route

        List<String> pathway = searchFG(getGroupByName(f1), f2, todo, path, visited, pathwl);


        return pathway; //stub
    }


    public List<String> searchFG(FunctionalGroup f, String end, List<String> todo,
                                 List<String> path, List<String> visited,
                                 List<List<String>> pathwl) {

        if (f.getName() == end) { // did you find it?
//            visited.add(f.getName());
//            visited.add(end);

            path.add(end);
            this.result = path;
            return this.result;
            //return path; // yes? then return the path

        } else if (visited.contains(f.getName())) { // no? then check if already visited

            //yes? then continue through unsearched paths

//            int last = pathwl.size() - 1;
//            path = pathwl.get(last);
//            pathwl.remove(last);
            searchPaths(end, todo, path, visited, pathwl); // "take one off the top (path-wl)"

        } else { //no? then add to visited and add same-level paths to to-do list. Copy visited by # nodes add to pathwl
//            int size = f.getPathways().size() - 1;
            int size = f.getPathways().size();
            todo.addAll(f.getPathways());
            path.add(f.getName());
            visited.add(f.getName());
            pathwl.addAll(copyList(size, path));

            searchPaths(end, todo, path, visited, pathwl);
        }

        path.add(end); // <<--- Adding this fixed the problem, for some reason even though this
        // is including in the termination condition, it doesn't execute? Maybe they are talking about
        // two different "path" objects?

        //return path;
        return this.result;
    }

    public boolean searchPaths(String end, List<String> todo, List<String> path,
                               List<String> visited, List<List<String>> pathwl) {

        if (todo.size() == 0) { // reached a dead end?
            if (pathwl.size() == 0) {
                return false;
            } else { // this case shouldn't happen if to-do and path work in tandem
                int index = pathwl.size() - 1;
                List<String> nextPath = pathwl.get(index);
                pathwl.remove(index);
            }
        } else {
            int index1 = todo.size() - 1;
            int index2 = pathwl.size() - 1;
            String f = todo.get(index1);
            path = pathwl.get(index2);
            todo.remove(index1);   // "take one off the top (to-do)"
            pathwl.remove(index2);
            searchFG(getGroupByName(f), end, todo, path, visited, pathwl);
        }

//        return true; return void?
        return false;
    }

    public FunctionalGroup getGroupByName(String name) {
        for (FunctionalGroup f : this.funcGroups) {
            if (f.getName() == name) {
                return f;
            }
        }
        return null;
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
    // EFFECTS: adds functional group to funcGroups list.
    public void addGroup(FunctionalGroup f) {
        this.funcGroups.add(f);
    }

    public List<FunctionalGroup> getGroups() {
        return this.funcGroups;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
