package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RuleTest {

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void testCreation() {
        Rule rule = new Rule("tio(X, Y, Z):- varon(X), hermano(X, Z),padre(Z, Y)");
        Assert.assertEquals(new Fact("tio(X,Y,Z)"), rule.getSignature());
        Assert.assertEquals(
            Arrays.asList(new Fact("varon(X)"), new Fact("hermano(X,Z)"), new Fact("padre(Z,Y)")),
            rule.getRequirements());        
    }

    @Test
    public void testReplaceRuleArgs() {
        Rule rule = new Rule("hijo(X,Y) :- varon(X), padre(Y,X)");
        List<Fact> newFacts = rule.replaceArgs(Arrays.asList("juan", "manuel"));
        Assert.assertEquals(
            Arrays.asList(new Fact("varon(juan)"), new Fact("padre(manuel, juan)")),
            newFacts
        );
    }

}
