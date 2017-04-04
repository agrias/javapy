import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Gao on 4/4/17.
 */
public class ProcessFunctionsTest {

    @Test
    public void testJpy() throws IOException {

        ProcessFunctions clazz = new ProcessFunctions();
        Process p = ProcessFunctions.jPy();

        BufferedWriter stdOutput = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

        stdOutput.write("print(\"Hello World\")");
        stdOutput.close();

        StringBuilder sb = new StringBuilder();
        String temp;

        while((temp = stdInput.readLine()) != null){
            sb.append(temp);
        }

        Assert.assertEquals("Hello World", sb.toString());
    }

    @Test
    public void testJpyScript() throws IOException {

        ClassLoader cl = getClass().getClassLoader();
        File f = new File(cl.getResource("helloWorld.py").getFile());

        String response = ProcessFunctions.jPyScript(f.getAbsolutePath());

        Assert.assertEquals("Hello World", response);
    }

    @Test
    public void testJpyScriptError() throws IOException {

        ClassLoader cl = getClass().getClassLoader();
        File f = new File(cl.getResource("brokenTest.py").getFile());

        try {
            String response = ProcessFunctions.jPyScript(f.getAbsolutePath());
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("There are errors in this script.", e.getMessage());
        }
    }

    @Test
    public void testJpyScriptArgs() throws IOException {

        ClassLoader cl = getClass().getClassLoader();
        File f = new File(cl.getResource("argsTest.py").getFile());

        List<String> args = new ArrayList<String>();
        args.add(f.getAbsolutePath());
        args.add("1");
        args.add("2");
        args.add("3");

        String response = ProcessFunctions.jPyScriptArgs(args);

        Assert.assertEquals("4", response);
    }
}
