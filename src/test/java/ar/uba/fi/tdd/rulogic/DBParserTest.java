package ar.uba.fi.tdd.rulogic;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.uba.fi.tdd.rulogic.DBParser;


public class DBParserTest {

    static String SANE_DB = 
        "padre(roberto, cecilia).\n" +
        "hijo(simon, lalala).\n" +
        "hijo(X, Y) :- varon(X), padre(Y, X)." + "\n";

    DBParser parser;

	@Before
	public void setUp() throws Exception {
        initMocks(this);
        parser = new DBParser(SANE_DB);
	}

	@Test
	public void testRulesCreated() {
        Assert.assertEquals(1, parser.getRules().size());
    }
    
    @Test
	public void testFactsCreated() {
        Assert.assertEquals(2, parser.getFacts().size());
	}

}