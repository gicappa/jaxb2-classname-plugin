package gk.xml.jaxb2;

import com.sun.tools.xjc.api.ClassNameAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RegexpClassNameAllocator implements ClassNameAllocator {

    private Logger logger = LoggerFactory.getLogger(RegexpClassNameAllocator.class);

    private final String regexp;
    private final String subst;

    public RegexpClassNameAllocator(String option) {
        this.regexp = parseOption(option).get(1);
        this.subst = parseOption(option).get(2);

        logger.debug("/regexp/subst/ /{}/{}/", regexp, subst);
    }

    public String assignClassName(String packageName, String className) {
        String newName = className.replaceAll(regexp, subst);

        logger.debug("class name allocator changed the name from {} to {}", className, newName);

        return newName;
    }

    public List<String> parseOption(String option) {
        if (!option.startsWith("-cn:/"))
            throw new IllegalArgumentException(
                    "Wrong format for the xjc className plugin option. \n" +
                            "It should be expressed like this: -cn:/<regexp>/<subs>/\n" +
                            "and it was: " + option
            );

        return Arrays.stream(option.split("/"))
                .filter(e -> !e.isEmpty())
                .collect(Collectors.toList());
    }
}
