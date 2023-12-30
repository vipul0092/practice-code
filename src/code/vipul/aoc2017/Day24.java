package code.vipul.aoc2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static code.vipul.aoc2017.inputs.Inputs.DAY_24;

/**
 * Created by vgaur created on 30/12/23
 */
public class Day24 {

    private static String INPUT = """
            0/2
            2/2
            2/3
            3/4
            3/5
            0/1
            10/1
            9/10
            """;

    public static void solve() {
        INPUT = DAY_24;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        List<Port> ports = new ArrayList<>();
        // Index -> << number -> connected bitmap >>
        Map<Integer, Long[]> connections = new LinkedHashMap<>();
        List<Integer> starting = new ArrayList<>();

        for (String line : lines) {
            var parts = line.split("/");
            Port port = new Port(new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])});
            ports.add(port);
        }

        for (int i = 0; i < ports.size(); i++) {
            Port p1 = ports.get(i);
            if (p1.pins[0] == 0 || p1.pins[1] == 0) {
                starting.add(i);
            }
            long con1 = 0, con2 = 0;
            connections.put(i, new Long[2]);
            for (int j = 0; j < ports.size(); j++) {
                if (i == j) continue;
                Port p2 = ports.get(j);
                if (p1.pins[0] == p2.pins[0] || p1.pins[0] == p2.pins[1]) {
                    con1 |= (1L << j);
                }
                if (p1.pins[1] == p2.pins[0] || p1.pins[1] == p2.pins[1]) {
                    con2 |= (1L << j);
                }
            }
            connections.get(i)[0] = con1;
            connections.get(i)[1] = con2;
        }

        Result maxResult = null;
        for (int start : starting) {
            Port current = ports.get(start);
            int connected = current.pins[0] == 0 ? 0 : 1;
            Result res = recurse(start, connected, 1L << start, false, connections, ports);
            if (maxResult == null) {
                maxResult = res;
            } else {
                if (maxResult.str < res.str) {
                    maxResult = res;
                }
            }
        }
        System.out.println("Part 1: " + maxResult.str); // 1511


        maxResult = null;
        for (int start : starting) {
            Port current = ports.get(start);
            int connected = current.pins[0] == 0 ? 0 : 1;
            Result res = recurse(start, connected, 1L << start, true, connections, ports);
            if (maxResult == null) {
                maxResult = res;
            } else {
                if (maxResult.len == res.len && maxResult.str < res.str || maxResult.len < res.len) {
                    maxResult = res;
                }
            }
        }
        System.out.println("Part 2: " + maxResult.str); // 1471
    }

    private static Result recurse(int index, int connected, long used, boolean considerLength,
                                  Map<Integer, Long[]> connections, List<Port> ports) {

        int other = connected == 0 ? 1 : 0;
        Port current = ports.get(index);
        Result maxResult = null;
        Long connection = connections.get(index)[other];

        for (int i = 0; i < ports.size(); i++) {
            if (((used & (1L << i)) == 0) && ((connection & (1L << i)) != 0)) {
                Port con = ports.get(i);
                int furtherConnect = current.pins[other] == con.pins[0] ? 0 : 1;
                var subRes = recurse(i, furtherConnect, used | 1L << i, considerLength, connections, ports);
                if (maxResult == null) {
                    maxResult = subRes;
                } else if (considerLength) {
                    if (maxResult.len == subRes.len && maxResult.str < subRes.str || maxResult.len < subRes.len) {
                        maxResult = subRes;
                    }
                } else { // consider only strength
                    if (maxResult.str < subRes.str) {
                        maxResult = subRes;
                    }
                }
            }
        }
        Result res = maxResult == null
                ? new Result(1, current.pins[0] + current.pins[1])
                : new Result(maxResult.len + 1, maxResult.str + current.pins[0] + current.pins[1]);
        return res;
    }

    record Port(int[] pins) {
    }

    record Result(int len, int str) {
    }
}
