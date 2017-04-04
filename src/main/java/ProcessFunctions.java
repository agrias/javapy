import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Andrew Gao on 4/4/17.
 */
public class ProcessFunctions {

    public static Process jPy() throws IOException {

        Process p;

        try {
            p = Runtime.getRuntime().exec("python");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Is python installed?");
        }

        return p;
    }

    public static String jPyScript(String scriptname) throws IOException {

        ProcessBuilder pb = new ProcessBuilder("python", scriptname);

        return runScript(pb);
    }

    public static String jPyScriptArgs(List<String> args) throws IOException {

        ProcessBuilder pb = new ProcessBuilder(args);

        return runScript(pb);
    }

    private static String runScript(ProcessBuilder pb) throws IOException {

        Process p = pb.start();
        String temp;
        StringBuilder sb = new StringBuilder();

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        while ((temp = stdInput.readLine()) != null) {
            sb.append(temp);
        }

        while ((temp = stdError.readLine()) != null) {
            throw new IllegalArgumentException("There are errors in this script.");
        }

        return sb.toString().trim();
    }
}
