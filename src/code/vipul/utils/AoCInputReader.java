package code.vipul.utils;

import code.vipul.aoc2024.Day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AoCInputReader {

    /**
     * Reads input for the given class
     *
     * @param clazz  Class where the method is being called from
     * @param sample Whether to read the sample input or the actual input
     * @return List of strings from the input
     */
    public static List<String> read(Class<?> clazz, boolean sample) {
        return read(clazz, sample, "");
    }

    public static List<String> read(Class<?> clazz, boolean sample, String suffix) {
        List<String> inputs = new ArrayList<>();
        var parts = clazz.getName().toLowerCase().split("\\.");
        String fileName = "inputs/" + parts[parts.length - 1] + (sample ? "sample" : "") + suffix;
        URL path = clazz.getResource(fileName);
        if (path == null) {
            throw new RuntimeException("Input file not found at path: " + fileName);
        }
        File file = new File(path.getFile());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                inputs.add(line);
            }
            return inputs;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
