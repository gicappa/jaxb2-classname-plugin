package gk.xml.jaxb2;

import com.sun.tools.xjc.BadCommandLineException;
import gk.xml.jaxb2.helpers.XJCRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class JAXBClassNamePluginTest {

    @Rule
    public XJCRule xjcRule = new XJCRule("target/test-xsd");

    @Before
    public void before() throws BadCommandLineException, IOException {
        Files.deleteIfExists(Paths.get("target/test-xsd/pkg/TypeAGK.java"));
        xjcRule.invokeXJC(
                "-d", "target/test-xsd",
                "-p", "pkg",
                "-npa",
                "-extension",
                "-Xclassname",
                "-cn:/(^.*$)/$1GK/",
                getClass().getResource("/test.xsd").getFile());
    }

    @Test
    public void it_matches_the_regexp_substitution() throws Exception {
        assertTrue(xjcRule.exists("pkg/TypeAGK.java"));
    }

}
