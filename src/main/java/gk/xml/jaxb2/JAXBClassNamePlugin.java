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
import com.sun.tools.xjc.api.ClassNameAllocator;
import com.sun.tools.xjc.outline.Outline;
import org.xml.sax.ErrorHandler;

import java.io.IOException;

/**
 * @author Gian Carlo Pace
 */
public class JAXBClassNamePlugin extends Plugin {

    @Override
    public String getOptionName() {
        return "Xclassname";
    }

    @Override
    public String getUsage() {
        return "  -Xclassname        :  uses a certain strategy to change the class name";
    }

    @Override
    public int parseArgument(Options opt, String[] args, int i) throws BadCommandLineException, IOException {
        opt.classNameAllocator = new ClassNameAllocator() {
            public String assignClassName(String packageName, String className) {
                return className + "GK";
            }
        };

        return super.parseArgument(opt,args,i);
    }

    @Override
    public boolean run(Outline outline,
                       Options opt,
                       ErrorHandler errorHandler) {
        return true;
    }

}
