package gk.xml.jaxb2.helpers;

import com.sun.tools.xjc.BadCommandLineException;
import com.sun.tools.xjc.Driver;
import org.junit.rules.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XJCRule extends ExternalResource {

    private final Path targetPath;

    public XJCRule(String path) {
        this.targetPath = Paths.get(path);
    }

    @Override
    protected void before() throws Throwable {
        ensureTargetPath(targetPath);
    }

    public void invokeXJC(String... params) throws BadCommandLineException {
        Driver.run(params, new DumbXJCListener());
    }

    public void ensureTargetPath(Path targetPath) throws IOException {
        if (!Files.exists(targetPath)) {
            Files.createDirectories(targetPath);
        }
    }

    public boolean exists(String path) throws IOException {
        return Files.exists(Paths.get(targetPath.toString(), path));
    }

}
