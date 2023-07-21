package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionalGroupTest {
    private FunctionalGroup a0;

    @BeforeEach
    public void runBefore() {
        a0 = new FunctionalGroup("a0");
    }

    @Test
    public void addPathwayTest() {
        a0.addPathway("a1");
        assertEquals(1, a0.getPathways().size());
    }


}
