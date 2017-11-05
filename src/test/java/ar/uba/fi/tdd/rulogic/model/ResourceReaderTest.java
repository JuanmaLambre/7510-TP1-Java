package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import ar.uba.fi.tdd.rulogic.ResourceReader;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;


public class ResourceReaderTest {

    static private String CONTENT = "content test";
    static private String RESOURCE_DIR = "src/main/resources/";
    static private String RESOURCE_FILE = "testfile.txt";

	@Before
	public void setUp() throws Exception {
        initMocks(this);
    }

	@Test
	public void testFileReading() {
        Assert.assertTrue(new ResourceReader(RESOURCE_FILE).read().startsWith(CONTENT));
    }
    
    @Test
    public void testInexistantFile() {
        Assert.assertNull(new ResourceReader("non-existing-file").read());
    }

}
