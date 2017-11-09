package ar.uba.fi.tdd.rulogic;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DrKnowTest {

    static String SANE_DB = 
        "padre(roberto, marcos).\n" +
        "padre (roberto, maria). \n" +
        "varon(marcos).\n" +
        "hijo(X, Y) :- varon(X), padre(Y, X).\n";

    DBParser parser;
    DrKnow drknow;

	@Before
	public void setUp() throws Exception {
        initMocks(this);
        parser = new DBParser(SANE_DB);
        drknow = new DrKnow(parser.getRules(), parser.getFacts());
	}

	@Test
	public void testAskRule() {
        Assert.assertTrue(drknow.ask("hijo(marcos, roberto)"));
    }

    @Test
    public void testUnexistantRuleReturnsFalse() {
        Assert.assertFalse(drknow.ask("noexiste(uno, dos)"));
    }

    @Test
    public void testFalseRule() {
        Assert.assertFalse(drknow.ask("hijo(maria, roberto)"));
    }

    @Test
    public void testFact() {
        Assert.assertTrue(drknow.ask("padre( roberto, maria )"));
    }

}
