package gk.xml.jaxb2;

import com.sun.tools.xjc.BadCommandLineException;
import gk.xml.jaxb2.helpers.XJCRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JAXBClassNamePluginTest {

    @Rule
    public XJCRule xjcRule = new XJCRule("target/test-xsd");

    @Before
    public void before() throws BadCommandLineException {
        xjcRule.invokeXJC(
                "-d", "target/test-xsd",
                "-p", "pkg",
                "-npa",
                "-extension",
                "-Xclassname",
                getClass().getResource("/test.xsd").getFile());
    }

    @Test
    public void it_matches_the_regexp_substitution() throws Exception {
        assertTrue(xjcRule.exists("pkg/TypeAGK.java"));
    }

}
