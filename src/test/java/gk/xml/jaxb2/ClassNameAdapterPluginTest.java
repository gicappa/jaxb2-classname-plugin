package gk.xml.jaxb2;

import com.sun.tools.xjc.XJCFacade;
import org.junit.Test;

public class ClassNameAdapterPluginTest {

    @Test
    public void when_calling_the_generator_the_name_is_different() throws Throwable {
        XJCFacade facade = new XJCFacade();
        facade.main(new String[]{"-Xclassname"});
//
    }
}