/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gk.xml.jaxb2;

import com.sun.tools.xjc.BadCommandLineException;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.outline.Outline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Gian Carlo Pace
 */
public class JAXBClassNamePlugin extends Plugin {

    private Logger logger = LoggerFactory.getLogger(JAXBClassNamePlugin.class);
    public static final String PLG_OPTION = "-cn:";

    @Override
    public String getOptionName() {
        return "Xclassname";
    }

    @Override
    public String getUsage() {
        return "  -Xclassname -cn:/<regexp>/<subs>/       :  uses a certain strategy to change the class name";
    }

    @Override
    public int parseArgument(Options opt, final String[] args, int i)
            throws BadCommandLineException, IOException {

        logger.debug("parsing arguments passed by user: {}", Arrays.toString(args));

        Arrays.stream(args)
                .filter(o -> o.startsWith(PLG_OPTION))
                .findAny()
                .ifPresent(e -> opt.classNameAllocator = new RegexpClassNameAllocator(e));

        super.parseArgument(opt, stripPlgOptions(args), i);

        return 2;
    }

    private String[] stripPlgOptions(String[] args) {
        return Arrays.stream(args)
                    .filter(o -> !o.startsWith(PLG_OPTION))
                    .filter(o -> !o.equals("-Xclassname"))
                    .toArray(size -> new String[size]);
    }

    @Override
    public boolean run(Outline outline,
                       Options opt,
                       ErrorHandler errorHandler) {

        return true;
    }

}
