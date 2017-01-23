package gk.xml.jaxb2;

import com.sun.tools.xjc.BadCommandLineException;
import com.sun.tools.xjc.Driver;
import org.junit.rules.ExternalResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XJCRule extends ExternalResource {

    private final String targetPath;

    public XJCRule(String targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    protected void before() throws Throwable {
        ensureExists(targetPath);

    }

    public void invokeXJC(String... params) throws BadCommandLineException {
        Driver.run(params, new DumbXJCListener());
    }

    public void ensureExists(String path) throws IOException {
        ensureExists(Paths.get(path));
    }

    public void ensureExists(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

}
