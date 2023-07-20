package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SynthesisGraphTest {
    private SynthesisGraph testGraph;
    private FunctionalGroup a0;
    private FunctionalGroup a1;
    private FunctionalGroup a2;
    private FunctionalGroup b0;
    private FunctionalGroup b1;
    private FunctionalGroup b2;
    private FunctionalGroup c0;
    private FunctionalGroup c1;
    private FunctionalGroup c2;

    @BeforeEach
    public void runBefore() {
        init();
    }

    @Test
    public void addGroupTest() {
        assertEquals(9, testGraph.getGroups().size());
        assertEquals(a1, testGraph.getGroups().get(1));
    }

    @Test
    public void getGroupByNameTest() {
        assertEquals(b0, testGraph.getGroupByName("b0"));
    }

    @Test
    public void findPathwayTestTrivial() {
        // Start and End points are the same
        List<String> pathTest = testGraph.findPathway("a0", "a0");
        List<String> testList = new ArrayList<>();
        testList.add("a0");
        assertEquals(testList, pathTest);
    }

    @Test
    public void findPathwayTestStep() {
        // End point is first encountered node
        List<String> pathTest = testGraph.findPathway("a0", "c2");
        List<String> testList = new ArrayList<>();
        testList.add("a0");
        testList.add("c2");
        assertEquals(testList, pathTest);
    }

    @Test
    public void findPathwayTest() {
        // End point is first encountered node
        List<String> pathTest = testGraph.findPathway("a0", "a1");
        List<String> testList = new ArrayList<>();
        testList.add("a0");
        testList.add("a1");
        assertEquals(testList, pathTest);
    }

    @Test
    public void copyListTest() {
        List<List<String>> copyTest = testGraph.copyList(a0.getPathways().size(), a0.getPathways());
        assertEquals(3, copyTest.size());
    }


    @SuppressWarnings("CheckStyle")
    public void init() {
        testGraph = new SynthesisGraph("testGraph");

        a0 = new FunctionalGroup("a0");
        a0.addPathway("a1");
        a0.addPathway("b0");
        a0.addPathway("c2");
        testGraph.addGroup(a0);

        a1 = new FunctionalGroup("a1");
        a1.addPathway("a0");
        a1.addPathway("a2");
        a1.addPathway("b1");
        testGraph.addGroup(a1);

        a2 = new FunctionalGroup("a2");
        a2.addPathway("a1");
        a2.addPathway("b2");
        a2.addPathway("c0");
        testGraph.addGroup(a2);

        b0 = new FunctionalGroup("b0");
        b0.addPathway("b1");
        b0.addPathway("a0");
        testGraph.addGroup(b0);

        b1 = new FunctionalGroup("b1");
        b1.addPathway("b0");
        b1.addPathway("a1");
        b1.addPathway("b2");
        b1.addPathway("c1");
        testGraph.addGroup(b1);

        b2 = new FunctionalGroup("b2");
        b2.addPathway("b1");
        b2.addPathway("a2");
        b2.addPathway("c2");
        testGraph.addGroup(b2);

        c0 = new FunctionalGroup("c0");
        c0.addPathway("b0");
        c0.addPathway("c1");
        c0.addPathway("a2");
        testGraph.addGroup(c0);

        c1 = new FunctionalGroup("c1");
        c1.addPathway("c0");
        c1.addPathway("c2");
        c1.addPathway("b1");
        testGraph.addGroup(c1);

        c2 = new FunctionalGroup("c2");
        c2.addPathway("c1");
        c2.addPathway("b2");
        c2.addPathway("a0");
        testGraph.addGroup(c2);
    }

}
