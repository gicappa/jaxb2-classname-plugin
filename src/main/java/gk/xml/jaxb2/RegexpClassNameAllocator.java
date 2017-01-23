package gk.xml.jaxb2;

import com.sun.tools.xjc.api.ClassNameAllocator;

public class RegexpClassNameAllocator implements ClassNameAllocator {

    private final String regexp;
    private final String subst;

    public RegexpClassNameAllocator(String regexp, String subst) {
        this.regexp = regexp;
        this.subst = subst;
    }

    public String assignClassName(String packageName, String className) {
        return className.replaceAll(regexp, subst);
    }

}
