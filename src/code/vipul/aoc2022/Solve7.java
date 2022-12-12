package code.vipul.aoc2022;

import code.vipul.aoc2022.inputs.Inputs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 06/12/22
 * https://adventofcode.com/2022/day/7
 */
public class Solve7 {

    private static final String INPUT = "$ cd /\n" +
            "$ ls\n" +
            "dir a\n" +
            "14848514 b.txt\n" +
            "8504156 c.dat\n" +
            "dir d\n" +
            "$ cd a\n" +
            "$ ls\n" +
            "dir e\n" +
            "29116 f\n" +
            "2557 g\n" +
            "62596 h.lst\n" +
            "$ cd e\n" +
            "$ ls\n" +
            "584 i\n" +
            "$ cd ..\n" +
            "$ cd ..\n" +
            "$ cd d\n" +
            "$ ls\n" +
            "4060174 j\n" +
            "8033020 d.log\n" +
            "5626152 d.ext\n" +
            "7214296 k";

    private static Dir parentDirectory;
    private static List<Long> sizes = new ArrayList<>();

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_7.split("\n")).collect(Collectors.toList());
        // List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        Dir curdir = null;

        for (int i = 0; i < inputs.size(); ) {
            String in = inputs.get(i);
            if (in.startsWith("$")) {
                String[] breaks = in.split(" ");
                if (breaks[1].equals("cd")) {
                    if (breaks[2].equals("..")) {
                        curdir = curdir.parent;
                        // System.out.println("Going back to directory: " + curdir.name);
                    } else {
                        String dirname = breaks[2];
                        Dir dir = null;
                        if (curdir != null) {
                            dir = curdir.getDir(dirname);
                            curdir.addSub(dir);
                        } else {
                            dir = new Dir(dirname, null);
                            parentDirectory = dir;
                        }
                        curdir = dir;
                        // System.out.println("Changing directory to: " + curdir.name);
                    }
                    i++;
                } else if (breaks[1].equals("ls")) {
                    i++;
                    // System.out.println("In directory: " + curdir.name);
                    while (i < inputs.size() && !inputs.get(i).startsWith("$")) {
                        in = inputs.get(i);
                        // System.out.println(in);
                        if (in.startsWith("dir")) {
                            String name = in.split(" ")[1];
                            Dir dir = new Dir(name, curdir);
                            curdir.addSub(dir);
                        } else {
                            String[] sizename = in.split(" ");
                            long sz = Long.parseLong(sizename[0]);
                            curdir.size += sz;
                        }
                        i++;
                    }
                }
            }
        }

        long totalUsed = populateSizesRecursive(parentDirectory);
        long part1 = sizes.stream()
                .filter(size -> size <= 100000)
                .mapToLong(l -> l).sum();
        System.out.println(part1); //1334506

        long expectedUsage = 40000000L;

        long atLeastFreeThisMuch = totalUsed - expectedUsage;
        List<Long> sorted = sizes.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());

        long part2 = 0;
        for (Long v : sorted) {
            if (v >= atLeastFreeThisMuch) {
                part2 = v;
                break;
            }
        }
        System.out.println(part2); //7421137
    }

    private static long populateSizesRecursive(Dir dir) {
        long size = dir.size;
        for (Dir d : dir.subdirs.values()) {
            size += (populateSizesRecursive(d));
        }
        sizes.add(size);
        return size;
    }

    private static final class Dir {
        private final String name;
        private long size = 0;
        private final Map<String, Dir> subdirs;
        private final Dir parent;

        public Dir(String n, Dir parent) {
            this.name = n;
            this.parent = parent;
            subdirs = new HashMap<>();
        }

        public void addSub(Dir dir) {
            if (!subdirs.containsKey(dir.name)) {
                subdirs.put(dir.name, dir);
            }
        }

        public Dir getDir(String subname) {
            return subdirs.get(subname);
        }

        @Override
        public String toString() {
            return String.format("name: %s, parent: %s", name, parent == null ? "" : parent.name);
        }
    }
}
