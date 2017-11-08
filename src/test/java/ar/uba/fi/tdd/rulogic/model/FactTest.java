package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FactTest {

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void testCreation() {
        Fact fact = new Fact("padre(juan, pedro)");
        Assert.assertEquals("padre", fact.getName());
        Assert.assertEquals(Arrays.asList("juan", "pedro"), fact.getArgs());
        Assert.assertEquals(2, fact.getArgs().size());
    }
    
    @Test
    public void testToString() {
        Fact fact = new Fact("padre  (juan ,pedro)");
        Assert.assertEquals("padre(juan, pedro)", fact.toString());
    }

    @Test
    public void testEquality() {
        Fact fact = new Fact("padre(juan, pedro)");
        Fact fact2 = new Fact("padre(juan, pedro)");
        Assert.assertTrue(fact.equals(fact2));
    }

    @Test
    public void testFactsWithUnorderedArgs() {
        Fact fact = new Fact("padre(juan, pedro)");
        Fact fact2 = new Fact("padre(pedro, juan)");
        Assert.assertFalse(fact.equals(fact2));
    }

    @Test
    public void testReplaceFactArgs() {
        Fact fact = new Fact("padre(F, B)");
        HashMap<String, String> replacements = new HashMap();
        replacements.put("F", "foo");
        replacements.put("B", "bar");
        Assert.assertTrue(fact.replaceArgs(replacements).equals(new Fact("padre(foo, bar)")));
    }

}
