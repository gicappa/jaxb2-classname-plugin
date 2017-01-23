package gk.xml.jaxb2;

import org.junit.Rule;
import org.junit.Test;

public class JAXBClassNamePluginTest {

    @Rule
    public XJCRule xjcRule = new XJCRule("target/test-xsds");

    @Test
    public void when_xx() throws Exception {
        xjcRule.invokeXJC("-d", "target/test-xsd", "-extension", "-Xclassname",
                getClass().getResource("/test.xsd").getFile());

    }

}
