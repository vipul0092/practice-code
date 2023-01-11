package code.vipul.aoc2021.day23;

import code.vipul.aoc2019.Grid;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

import static code.vipul.aoc2021.Inputs.INPUT_23;
import static code.vipul.aoc2021.Inputs.INPUT_23_2;

/**
 * Created by vgaur created on 10/01/23
 * https://adventofcode.com/2021/day/23
 */
public class Solve23 {

    private static final String INPUT =
                    "#############\n" +
                    "#...........#\n" +
                    "###B#C#B#D###\n" +
                    "  #A#D#C#A#\n" +
                    "  #########";

    private static final Map<Integer, Room> ROOMS;
    private static final Map<Integer, Set<Integer>> CONNECTIONS;
    private static final Map<Grid.Pos, Integer> POS_TO_POINT;
    private static final Set<Integer> NOT_ALLOWED = Set.of(2, 4, 6, 8);
    private static int answer = Integer.MAX_VALUE;

    static {
        POS_TO_POINT = new HashMap<>();
        POS_TO_POINT.put(Grid.Pos.of(0, 3), 11);
        POS_TO_POINT.put(Grid.Pos.of(1, 3), 12);
        POS_TO_POINT.put(Grid.Pos.of(0, 5), 13);
        POS_TO_POINT.put(Grid.Pos.of(1, 5), 14);
        POS_TO_POINT.put(Grid.Pos.of(0, 7), 15);
        POS_TO_POINT.put(Grid.Pos.of(1, 7), 16);
        POS_TO_POINT.put(Grid.Pos.of(0, 9), 17);
        POS_TO_POINT.put(Grid.Pos.of(1, 9), 18);

        ROOMS = new HashMap<>();
        ROOMS.put(0, new Room(0, 11, 12));
        ROOMS.put(1, new Room(1, 13, 14));
        ROOMS.put(2, new Room(2, 15, 16));
        ROOMS.put(3, new Room(3, 17, 18));
        CONNECTIONS = new HashMap<>();

        //0  1  2  3  4  5  6  7  8  9  10
        //     11    13    15    17
        //     12    14    16    18
        CONNECTIONS.put(0, Set.of(1));
        CONNECTIONS.put(1, Set.of(0, 2));
        CONNECTIONS.put(2, Set.of(1, 3, 11));
        CONNECTIONS.put(3, Set.of(2, 4));
        CONNECTIONS.put(4, Set.of(3, 5, 13));
        CONNECTIONS.put(5, Set.of(4, 6));
        CONNECTIONS.put(6, Set.of(5, 7, 15));
        CONNECTIONS.put(7, Set.of(6, 8));
        CONNECTIONS.put(8, Set.of(7, 9, 17));
        CONNECTIONS.put(9, Set.of(8, 10));
        CONNECTIONS.put(10, Set.of(9));
        CONNECTIONS.put(11, Set.of(2, 12));
        CONNECTIONS.put(12, Set.of(11));
        CONNECTIONS.put(13, Set.of(4, 14));
        CONNECTIONS.put(14, Set.of(13));
        CONNECTIONS.put(15, Set.of(6, 16));
        CONNECTIONS.put(16, Set.of(15));
        CONNECTIONS.put(17, Set.of(8, 18));
        CONNECTIONS.put(18, Set.of(17));
    }

    public static void solve() {
        final long startTime = System.nanoTime();
        //var positions = getPositions(INPUT);          // Answer: 12521
        var positions = getPositions(INPUT_23);         // Answer: 13495
        //var positions = getPositions(INPUT_23_2);     // Answer: 13520
        dfs(new State(positions.get(0).get(0), positions.get(0).get(1),
                positions.get(1).get(0), positions.get(1).get(1),
                positions.get(2).get(0), positions.get(2).get(1),
                positions.get(3).get(0), positions.get(3).get(1), 0), "");

        final long duration = (System.nanoTime() - startTime) / 1000000;
        System.out.println("Runtime(in ms): " + duration);
        System.out.println(answer);
    }

    private static Set<State> states = new HashSet<>();

    private static void dfs(State state, String path) {
        if (states.contains(state)) {
            return;
        }
        states.add(state);
        if (state.cost >= answer) {
            return;
        }
        Room rooma = ROOMS.get(0);
        Room roomb = ROOMS.get(1);
        Room roomc = ROOMS.get(2);
        Room roomd = ROOMS.get(3);
        if (rooma.isPosInRoom(state.a1) && rooma.isPosInRoom(state.a2)
                && roomb.isPosInRoom(state.b1) && roomb.isPosInRoom(state.b2)
                && roomc.isPosInRoom(state.c1) && roomc.isPosInRoom(state.c2)
                && roomd.isPosInRoom(state.d1) && roomd.isPosInRoom(state.d2)
                && state.cost <= answer) {
            answer = state.cost;
            System.out.println("Cost: " + answer);
            System.out.println("Path: " + path);
            return;
        }


        // a1
        Room room = rooma;
        Map<Integer, Integer> pos = getPositions(state.a1, state.a2, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.a2, newpos)) {
                dfs(new State(newpos, state.a2, state.b1, state.b2, state.c1, state.c2, state.d1, state.d2,
                        state.cost + len), path + ", a1 moves to " + newpos);
            }
        }

        // a2
        pos = getPositions(state.a2, state.a1, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.a1, newpos)) {
                dfs(new State(state.a1, newpos, state.b1, state.b2, state.c1, state.c2, state.d1, state.d2,
                        state.cost + len), path + ", a2 moves to " + newpos);
            }
        }

        // b1
        room = roomb;
        pos = getPositions(state.b1, state.b2, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            // either the entry is not in the room
            // and if it is, then we check whether we can actually move into the room or not
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.b2, newpos)) {
                dfs(new State(state.a1, state.a2, newpos, state.b2, state.c1, state.c2, state.d1, state.d2,
                        state.cost + (len * 10)), path + ", b1 moves to " + newpos);
            }
        }

        // b2
        pos = getPositions(state.b2, state.b1, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            // either the entry is not in the room
            // and if it is, then we check whether we can actually move into the room or not
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.b1, newpos)) {
                dfs(new State(state.a1, state.a2, state.b1, newpos, state.c1, state.c2, state.d1, state.d2,
                        state.cost + (len * 10)), path + ", b2 moves to " + newpos);
            }
        }

        // c1
        room = roomc;
        pos = getPositions(state.c1, state.c2, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            // either the entry is not in the room
            // and if it is, then we check whether we can actually move into the room or not
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.c2, newpos)) {
                dfs(new State(state.a1, state.a2, state.b1, state.b2, newpos, state.c2, state.d1, state.d2,
                        state.cost + (len * 100)), path + ", c1 moves to " + newpos);
            }
        }

        // c2
        pos = getPositions(state.c2, state.c1, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.c1, newpos)) {
                dfs(new State(state.a1, state.a2, state.b1, state.b2, state.c1, newpos, state.d1, state.d2,
                        state.cost + (len * 100)), path + ", c2 moves to " + newpos);
            }
        }

        // d1
        room = roomd;
        pos = getPositions(state.d1, state.d2, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.d2, newpos)) {
                dfs(new State(state.a1, state.a2, state.b1, state.b2, state.c1, state.c2, newpos, state.d2,
                        state.cost + (len * 1000)), path + ", d1 moves to " + newpos);
            }
        }

        // d2
        pos = getPositions(state.d2, state.d1, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.d1, newpos)) {
                dfs(new State(state.a1, state.a2, state.b1, state.b2, state.c1, state.c2, state.d1, newpos,
                        state.cost + (len * 1000)), path + ", d2 moves to " + newpos);
            }
        }
    }

    private static Map<Integer, Integer> getPositions(int current, int other, State state, Room room) {
        // If we are already at the bottom of our room, we dont want to move anywhere
        // If the room is already full, then don't move anything at all
        if (room.isPosSetInRoom(current, other)) {
            return Map.of();
        }
        Queue<Integer> q = new ArrayDeque<>();
        Queue<Integer> len = new ArrayDeque<>();
        q.add(current);
        len.add(0);
        Set<Integer> visited = new HashSet<>();
        visited.add(current);

        Map<Integer, Integer> possibles = new HashMap<>();
        Map<Integer, Integer> roomPossibles = new HashMap<>();
        boolean isCurrentPositionInRoom = room.isPosInRoom(current);
        boolean isCurrentPositionHor = current >= 0 && current <= 10;
        while (!q.isEmpty()) {
            int pos = q.remove();
            int curlen = len.remove();

            for (var entry : CONNECTIONS.get(pos)) {
                if (!visited.contains(entry) && state.isPosNotInState(entry)) {
                    visited.add(entry);
                    q.add(entry);
                    len.add(curlen + 1);
                    boolean isEntryHor = entry >= 0 && entry <= 10;
                    // If we are in horizontal position, we can only go to our room, and thats it
                    if (isCurrentPositionHor && room.isPosInRoom(entry)) {
                        possibles.put(entry, curlen + 1);

                        // if we aren't in horizontal pos, i.e. we are in our room or some other room
                        // entry can't be in the not allowed states
                    } else if (!isCurrentPositionHor && !NOT_ALLOWED.contains(entry)) {
                        // If we are in our room, then pos can only be horizontal
                        if ((isCurrentPositionInRoom && isEntryHor)
                                // if we are not in our room, i.e. we are in some other room
                                // then pos can be in our room or we can go horizontal pos
                                || (!isCurrentPositionInRoom && (room.isPosInRoom(entry) || isEntryHor))) {
                            if (room.isPosInRoom(entry)) {
                                roomPossibles.put(entry, curlen + 1);
                            } else {
                                possibles.put(entry, curlen + 1);
                            }
                        }
                    }
                }
            }
        }
        Map<Integer, Integer> all = new LinkedHashMap<>(roomPossibles);
        List<Map.Entry<Integer, Integer>> toSort = new ArrayList<>(possibles.entrySet());
        toSort.sort(Map.Entry.comparingByValue());
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> e : toSort) {
            if (map.put(e.getKey(), e.getValue()) != null) {
                throw new IllegalStateException("Duplicate key");
            }
        }
        all.putAll(map);
        return all;
    }

    private static boolean canMoveToRoom(Room room, int a, int pos) {
        return room.isPosSetInRoom(pos, a);
    }

    private static final class State {
        private int a1;
        private int a2;
        private int b1;
        private int b2;
        private int c1;
        private int c2;
        private int d1;
        private int d2;
        private int cost;

        private int hash = -1;

        State(int a1, int a2, int b1, int b2, int c1, int c2, int d1, int d2, int cost) {
            this.a1 = a1;
            this.a2 = a2;
            this.b1 = b1;
            this.b2 = b2;
            this.c1 = c1;
            this.c2 = c2;
            this.d1 = d1;
            this.d2 = d2;
            this.cost = cost;
        }

        @Override
        public int hashCode() {
            if (hash == -1) {
                hash = 5381;
                hash += (hash << 5) + Objects.hashCode(a1);
                hash += (hash << 5) + Objects.hashCode(a2);
                hash += (hash << 5) + Objects.hashCode(b1);
                hash += (hash << 5) + Objects.hashCode(b2);
                hash += (hash << 5) + Objects.hashCode(c1);
                hash += (hash << 5) + Objects.hashCode(c2);
                hash += (hash << 5) + Objects.hashCode(d1);
                hash += (hash << 5) + Objects.hashCode(d2);
                hash += (hash << 5) + Objects.hashCode(cost);
            }
            return hash;
        }

        @Override
        public boolean equals(Object another) {
            if (this == another) return true;
            return another instanceof State && equalTo((State) another);
        }

        private boolean equalTo(State another) {
            return Objects.equals(a1, another.a1)
                    && Objects.equals(a2, another.a2)
                    && Objects.equals(b1, another.b1)
                    && Objects.equals(b2, another.b2)
                    && Objects.equals(c1, another.c1)
                    && Objects.equals(c2, another.c2)
                    && Objects.equals(d1, another.d1)
                    && Objects.equals(d2, another.d2)
                    && Objects.equals(cost, another.cost);
        }

        public boolean isPosNotInState(int entry) {
            return entry != a1 && entry != a2
                    && entry != b1 && entry != b2
                    && entry != c1 && entry != c2
                    && entry != d1 && entry != d2;
        }
    }

    public static final class Node {
        private final int val;

        public Node(int val) {
            this.val = val;
        }
    }

    private static final class Room {
        private final int code;
        private final int p1;
        private final int p2;

        public Room(int code, int p1, int p2) {
            this.code = code;
            this.p1 = p1;
            this.p2 = p2;
        }

        public boolean isPosInRoom(int pos) {
            return pos == p1 || pos == p2;
        }

        public boolean isLowerPos(int pos) {
            return pos == p2;
        }

        public boolean isPosSetInRoom(int current, int other) {
            return isLowerPos(current) || (isPosInRoom(current) && isPosInRoom(other));
        }
    }

    private static Map<Integer, List<Integer>> getPositions(String input) {
        Map<Integer, List<Integer>> retmap = new HashMap<>();
        String[] lines = input.split("\n");

        for (int i = 0; i < 2; i++) {
            String line = lines[i + 2];
            for (int j = 0; j < line.length(); j++) {
                char ch = line.charAt(j);
                if (ch >= 'A' && ch <= 'D') {
                    retmap.putIfAbsent(ch - 'A', new ArrayList<>());
                    retmap.get(ch - 'A').add(POS_TO_POINT.get(Grid.Pos.of(i, j)));
                }
            }
        }
        return retmap;
    }
}
