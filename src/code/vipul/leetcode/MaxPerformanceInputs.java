package code.vipul.leetcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 * Created by vgaur created on 06/06/21
 */
public final class MaxPerformanceInputs {

    public static int N = 100000;
    public static int K = 86484;
    public static String SPEEDS;
    public static String EFFICIENCIES;

    static {
        try {
            URL path = MaxPerformanceInputs.class.getResource("speeds.txt");
            File f = new File(path.getFile());

            BufferedReader reader = new BufferedReader(new FileReader(f));
            SPEEDS = reader.readLine();
            reader.close();

            path = MaxPerformanceInputs.class.getResource("efficiencies.txt");
            f = new File(path.getFile());
            reader = new BufferedReader(new FileReader(f));
            EFFICIENCIES = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
