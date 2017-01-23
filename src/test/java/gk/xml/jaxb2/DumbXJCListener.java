package gk.xml.jaxb2;

import com.sun.tools.xjc.XJCListener;
import org.xml.sax.SAXParseException;

public class DumbXJCListener extends XJCListener {
    @Override
    public void error(SAXParseException e) {
        printError(e, "ERROR");
    }

    @Override
    public void fatalError(SAXParseException e) {
        printError(e, "FATAL");
    }

    @Override
    public void warning(SAXParseException e) {
        printError(e, "WARN");
    }

    @Override
    public void info(SAXParseException e) {
        printError(e, "INFO");
    }

    private void printError(SAXParseException e, String level) {
        System.err.printf("%s: SAX Parse exception", level);
        e.printStackTrace();
    }
}