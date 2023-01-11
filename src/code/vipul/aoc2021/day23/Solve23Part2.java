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
 * https://adventofcode.com/2021/day/23#part2
 */
public class Solve23Part2 {

    private static final String INPUT =
                    "#############\n" +
                    "#...........#\n" +
                    "###B#C#B#D###\n" +
                    "  #A#D#C#A#\n" +
                    "  #########";

    private static final Map<Integer, Room> rooms;
    private static final  Map<Integer, Set<Integer>> connections;
    private static final Map<Grid.Pos, Integer> POS_TO_POINT;
    private static final Set<Integer> NOT_ALLOWED = Set.of(2, 4, 6, 8);
    private static int answer = Integer.MAX_VALUE;

    static {
        POS_TO_POINT = new HashMap<>();
        POS_TO_POINT.put(Grid.Pos.of(0, 3), 11);
        POS_TO_POINT.put(Grid.Pos.of(1, 3), 12);
        POS_TO_POINT.put(Grid.Pos.of(2, 3), 19);
        POS_TO_POINT.put(Grid.Pos.of(3, 3), 20);
        POS_TO_POINT.put(Grid.Pos.of(0, 5), 13);
        POS_TO_POINT.put(Grid.Pos.of(1, 5), 14);
        POS_TO_POINT.put(Grid.Pos.of(2, 5), 21);
        POS_TO_POINT.put(Grid.Pos.of(3, 5), 22);
        POS_TO_POINT.put(Grid.Pos.of(0, 7), 15);
        POS_TO_POINT.put(Grid.Pos.of(1, 7), 16);
        POS_TO_POINT.put(Grid.Pos.of(2, 7), 23);
        POS_TO_POINT.put(Grid.Pos.of(3, 7), 24);
        POS_TO_POINT.put(Grid.Pos.of(0, 9), 17);
        POS_TO_POINT.put(Grid.Pos.of(1, 9), 18);
        POS_TO_POINT.put(Grid.Pos.of(2, 9), 25);
        POS_TO_POINT.put(Grid.Pos.of(3, 9), 26);

        rooms = new HashMap<>();
        rooms.put(0, new Room(0, 11, 12, 19, 20));
        rooms.put(1, new Room(1, 13, 14, 21, 22));
        rooms.put(2, new Room(2, 15, 16, 23, 24));
        rooms.put(3, new Room(3, 17, 18, 25, 26));

        //0  1  2  3  4  5  6  7  8  9  10
        //     11    13    15    17
        //     12    14    16    18
        //     19    21    23    25
        //     20    22    24    26
        connections = new HashMap<>();
        connections.put(0, Set.of(1));
        connections.put(1, Set.of(0, 2));
        connections.put(2, Set.of(1, 3, 11));
        connections.put(3, Set.of(2, 4));
        connections.put(4, Set.of(3, 5, 13));
        connections.put(5, Set.of(4, 6));
        connections.put(6, Set.of(5, 7, 15));
        connections.put(7, Set.of(6, 8));
        connections.put(8, Set.of(7, 9, 17));
        connections.put(9, Set.of(8, 10));
        connections.put(10, Set.of(9));
        connections.put(11, Set.of(2, 12));
        connections.put(12, Set.of(11, 19));
        connections.put(13, Set.of(4, 14));
        connections.put(14, Set.of(13, 21));
        connections.put(15, Set.of(6, 16));
        connections.put(16, Set.of(15, 23));
        connections.put(17, Set.of(8, 18));
        connections.put(18, Set.of(17, 25));
        connections.put(19, Set.of(12, 20));
        connections.put(20, Set.of(19));
        connections.put(21, Set.of(14, 22));
        connections.put(22, Set.of(21));
        connections.put(23, Set.of(16, 24));
        connections.put(24, Set.of(23));
        connections.put(25, Set.of(18, 26));
        connections.put(26, Set.of(25));
    }
    private static Set<State> states = new HashSet<>();

    public static void solve() {
        //var positions = getPositions(INPUT); // Answer: 44169
        var positions = getPositions(INPUT_23); // Answer: 53767
        //var positions = getPositions(INPUT_23_2); // Answer: 48708
        dfs(new State(positions.get(0).get(0), positions.get(0).get(1), positions.get(0).get(2), positions.get(0).get(3),
                positions.get(1).get(0), positions.get(1).get(1), positions.get(1).get(2), positions.get(1).get(3),
                positions.get(2).get(0), positions.get(2).get(1), positions.get(2).get(2), positions.get(2).get(3),
                positions.get(3).get(0), positions.get(3).get(1), positions.get(3).get(2), positions.get(3).get(3),
                0), "");

        System.out.println("Answer: " + answer);
    }

    private static void dfs(State state, String path) {
        if (states.contains(state)) {
            return;
        }
        if (states.size() % 100000 == 0) {
            System.out.println(states.size());
        }
        states.add(state);
        if (state.cost >= answer) {
            return;
        }
        Room rooma = rooms.get(0);
        Room roomb = rooms.get(1);
        Room roomc = rooms.get(2);
        Room roomd = rooms.get(3);
        if (state.cost <= answer
                && rooma.isPosInRoom(state.a1) && rooma.isPosInRoom(state.a2) && rooma.isPosInRoom(state.a3) && rooma.isPosInRoom(state.a4)
                && roomb.isPosInRoom(state.b1) && roomb.isPosInRoom(state.b2) && roomb.isPosInRoom(state.b3) && roomb.isPosInRoom(state.b4)
                && roomc.isPosInRoom(state.c1) && roomc.isPosInRoom(state.c2) && roomc.isPosInRoom(state.c3) && roomc.isPosInRoom(state.c4)
                && roomd.isPosInRoom(state.d1) && roomd.isPosInRoom(state.d2) && roomd.isPosInRoom(state.d3) && roomd.isPosInRoom(state.d4)
        ) {
            answer = state.cost;
            System.out.println("Cost: " + answer);
            System.out.println("Path: " + path);
            return;
        }


        // a1
        Room room = rooma;
        Map<Integer, Integer> pos = getPositions(state.a1, state.a2, state.a3, state.a4, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.a2, state.a3, state.a4, newpos)) {
                dfs(new State(newpos, state.a2, state.a3, state.a4,
                        state.b1, state.b2, state.b3, state.b4,
                        state.c1, state.c2, state.c3, state.c4,
                        state.d1, state.d2, state.d3, state.d4,
                        state.cost + len), path + ", a1 moves to " + newpos);
            }
        }

        // a2
        pos = getPositions(state.a2, state.a1, state.a3, state.a4, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.a1, state.a3, state.a4, newpos)) {
                dfs(new State(state.a1, newpos, state.a3, state.a4,
                        state.b1, state.b2, state.b3, state.b4,
                        state.c1, state.c2, state.c3, state.c4,
                        state.d1, state.d2, state.d3, state.d4,
                        state.cost + len), path + ", a2 moves to " + newpos);
            }
        }

        // a3
        pos = getPositions(state.a3, state.a1, state.a2, state.a4, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.a1, state.a2, state.a4, newpos)) {
                dfs(new State(state.a1, state.a2, newpos, state.a4,
                        state.b1, state.b2, state.b3, state.b4,
                        state.c1, state.c2, state.c3, state.c4,
                        state.d1, state.d2, state.d3, state.d4,
                        state.cost + len), path + ", a3 moves to " + newpos);
            }
        }

        // a4
        pos = getPositions(state.a4, state.a1, state.a3, state.a2, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.a1, state.a3, state.a2, newpos)) {
                dfs(new State(state.a1, state.a2, state.a3, newpos,
                        state.b1, state.b2, state.b3, state.b4,
                        state.c1, state.c2, state.c3, state.c4,
                        state.d1, state.d2, state.d3, state.d4,
                        state.cost + len), path + ", a4 moves to " + newpos);
            }
        }

        // b1
        room = roomb;
        pos = getPositions(state.b1, state.b2, state.b3, state.b4, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.b2, state.b3, state.b4, newpos)) {
                dfs(new State(state.a1, state.a2, state.a3, state.a4,
                        newpos, state.b2, state.b3, state.b4,
                        state.c1, state.c2, state.c3, state.c4,
                        state.d1, state.d2, state.d3, state.d4,
                        state.cost + (len * 10)), path + ", b1 moves to " + newpos);
            }
        }

        // b2
        pos = getPositions(state.b2, state.b1, state.b3, state.b4, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.b1, state.b3, state.b4, newpos)) {
                dfs(new State(state.a1, state.a2, state.a3, state.a4,
                        state.b1, newpos, state.b3, state.b4,
                        state.c1, state.c2, state.c3, state.c4,
                        state.d1, state.d2, state.d3, state.d4,
                        state.cost + (len * 10)), path + ", b2 moves to " + newpos);
            }
        }

        // b3
        pos = getPositions(state.b3, state.b2, state.b1, state.b4, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.b2, state.b1, state.b4, newpos)) {
                dfs(new State(state.a1, state.a2, state.a3, state.a4,
                        state.b1, state.b2, newpos, state.b4,
                        state.c1, state.c2, state.c3, state.c4,
                        state.d1, state.d2, state.d3, state.d4,
                        state.cost + (len * 10)), path + ", b3 moves to " + newpos);
            }
        }

        // b4
        pos = getPositions(state.b4, state.b2, state.b3, state.b1, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.b2, state.b3, state.b1, newpos)) {
                dfs(new State(state.a1, state.a2, state.a3, state.a4,
                        state.b1, state.b2, state.b3, newpos,
                        state.c1, state.c2, state.c3, state.c4,
                        state.d1, state.d2, state.d3, state.d4,
                        state.cost + (len * 10)), path + ", b4 moves to " + newpos);
            }
        }

        // c1
        room = roomc;
        pos = getPositions(state.c1, state.c2, state.c3, state.c4, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.c2, state.c3, state.c4, newpos)) {
                dfs(new State(state.a1, state.a2, state.a3, state.a4,
                        state.b1, state.b2, state.b3, state.b4,
                        newpos, state.c2, state.c3, state.c4,
                        state.d1, state.d2, state.d3, state.d4,
                        state.cost + (len * 100)), path + ", c1 moves to " + newpos);
            }
        }

        // c2
        pos = getPositions(state.c2, state.c1, state.c3, state.c4, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.c1, state.c3, state.c4, newpos)) {
                dfs(new State(state.a1, state.a2, state.a3, state.a4,
                        state.b1, state.b2, state.b3, state.b4,
                        state.c1, newpos, state.c3, state.c4,
                        state.d1, state.d2, state.d3, state.d4,
                        state.cost + (len * 100)), path + ", c2 moves to " + newpos);
            }
        }

        // c3
        pos = getPositions(state.c3, state.c1, state.c2, state.c4, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.c1, state.c2, state.c4, newpos)) {
                dfs(new State(state.a1, state.a2, state.a3, state.a4,
                        state.b1, state.b2, state.b3, state.b4,
                        state.c1, state.c2, newpos, state.c4,
                        state.d1, state.d2, state.d3, state.d4,
                        state.cost + (len * 100)), path + ", c3 moves to " + newpos);
            }
        }

        // c4
        pos = getPositions(state.c4, state.c1, state.c2, state.c3, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.c1, state.c2, state.c3, newpos)) {
                dfs(new State(state.a1, state.a2, state.a3, state.a4,
                        state.b1, state.b2, state.b3, state.b4,
                        state.c1, state.c2, state.c3, newpos,
                        state.d1, state.d2, state.d3, state.d4,
                        state.cost + (len * 100)), path + ", c4 moves to " + newpos);
            }
        }

        // d1
        room = roomd;
        pos = getPositions(state.d1, state.d2, state.d3, state.d4, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.d2, state.d3, state.d4, newpos)) {
                dfs(new State(state.a1, state.a2, state.a3, state.a4,
                        state.b1, state.b2, state.b3, state.b4,
                        state.c1, state.c2, state.c3, state.c4,
                        newpos, state.d2, state.d3, state.d4,
                        state.cost + (len * 1000)), path + ", d1 moves to " + newpos);
            }
        }

        // d2
        pos = getPositions(state.d2, state.d1, state.d3, state.d4, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.d1, state.d3, state.d4, newpos)) {
                dfs(new State(state.a1, state.a2, state.a3, state.a4,
                        state.b1, state.b2, state.b3, state.b4,
                        state.c1, state.c2, state.c3, state.c4,
                        state.d1, newpos, state.d3, state.d4,
                        state.cost + (len * 1000)), path + ", d2 moves to " + newpos);
            }
        }

        // d3
        pos = getPositions(state.d3, state.d1, state.d2, state.d4, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.d1, state.d2, state.d4, newpos)) {
                dfs(new State(state.a1, state.a2, state.a3, state.a4,
                        state.b1, state.b2, state.b3, state.b4,
                        state.c1, state.c2, state.c3, state.c4,
                        state.d1, state.d2, newpos, state.d4,
                        state.cost + (len * 1000)), path + ", d3 moves to " + newpos);
            }
        }

        // d4
        pos = getPositions(state.d4, state.d1, state.d2, state.d3, state, room);
        for (var entry : pos.entrySet()) {
            int newpos = entry.getKey();
            int len = entry.getValue();
            if (!room.isPosInRoom(newpos) || canMoveToRoom(room, state.d1, state.d2, state.d3, newpos)) {
                dfs(new State(state.a1, state.a2, state.a3, state.a4,
                        state.b1, state.b2, state.b3, state.b4,
                        state.c1, state.c2, state.c3, state.c4,
                        state.d1, state.d2, state.d3, newpos,
                        state.cost + (len * 1000)), path + ", d4 moves to " + newpos);
            }
        }
    }

    private static Map<Integer, Integer> getPositions(int current, int other1, int other2, int other3,
                                                      State state, Room room) {
        // If we are already at the bottom of our room, we dont want to move anywhere
        // If the room is already full, then don't move anything at all
        if (room.isPosSetInRoom(current, other1, other2, other3)) {
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

            for (var entry : connections.get(pos)) {
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

    private static boolean canMoveToRoom(Room room, int a1, int a2, int a3, int pos) {
        return room.isPosSetInRoom(pos, a1, a2, a3);
    }

    private static final class State {
        private int a1;
        private int a2;
        private int a3;
        private int a4;
        private int b1;
        private int b2;
        private int b3;
        private int b4;
        private int c1;
        private int c2;
        private int c3;
        private int c4;
        private int d1;
        private int d2;
        private int d3;
        private int d4;
        private int cost;

        private int hash = -1;

        State(int a1, int a2, int a3, int a4,
              int b1, int b2, int b3, int b4,
              int c1, int c2, int c3, int c4,
              int d1, int d2, int d3, int d4,
              int cost) {
            this.a1 = a1;
            this.a2 = a2;
            this.a3 = a3;
            this.a4 = a4;
            this.b1 = b1;
            this.b2 = b2;
            this.b3 = b3;
            this.b4 = b4;
            this.c1 = c1;
            this.c2 = c2;
            this.c3 = c3;
            this.c4 = c4;
            this.d1 = d1;
            this.d2 = d2;
            this.d3 = d3;
            this.d4 = d4;
            this.cost = cost;
        }

        @Override
        public int hashCode() {
            if (hash == -1) {
                hash = 5381;
                hash += (hash << 5) + Objects.hashCode(a1);
                hash += (hash << 5) + Objects.hashCode(a2);
                hash += (hash << 5) + Objects.hashCode(a3);
                hash += (hash << 5) + Objects.hashCode(a4);
                hash += (hash << 5) + Objects.hashCode(b1);
                hash += (hash << 5) + Objects.hashCode(b2);
                hash += (hash << 5) + Objects.hashCode(b3);
                hash += (hash << 5) + Objects.hashCode(b4);
                hash += (hash << 5) + Objects.hashCode(c1);
                hash += (hash << 5) + Objects.hashCode(c2);
                hash += (hash << 5) + Objects.hashCode(c3);
                hash += (hash << 5) + Objects.hashCode(c4);
                hash += (hash << 5) + Objects.hashCode(d1);
                hash += (hash << 5) + Objects.hashCode(d2);
                hash += (hash << 5) + Objects.hashCode(d3);
                hash += (hash << 5) + Objects.hashCode(d4);
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
                    && Objects.equals(a3, another.a3)
                    && Objects.equals(a4, another.a4)
                    && Objects.equals(b1, another.b1)
                    && Objects.equals(b2, another.b2)
                    && Objects.equals(b3, another.b3)
                    && Objects.equals(b4, another.b4)
                    && Objects.equals(c1, another.c1)
                    && Objects.equals(c2, another.c2)
                    && Objects.equals(c3, another.c3)
                    && Objects.equals(c4, another.c4)
                    && Objects.equals(d1, another.d1)
                    && Objects.equals(d2, another.d2)
                    && Objects.equals(d3, another.d3)
                    && Objects.equals(d4, another.d4)
                    && Objects.equals(cost, another.cost);
        }

        public boolean isPosNotInState(int entry) {
            return entry != a1 && entry != a2 && entry != a3 && entry != a4
                    && entry != b1 && entry != b2 && entry != b3 && entry != b4
                    && entry != c1 && entry != c2 && entry != c3 && entry != c4
                    && entry != d1 && entry != d2 && entry != d3 && entry != d4;
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
        private final int p3;
        private final int p4;

        public Room(int code, int p1, int p2, int p3, int p4) {
            this.code = code;
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
            this.p4 = p4;
        }

        public boolean isPosInRoom(int pos) {
            return pos == p1 || pos == p2 || pos == p3 || pos == p4;
        }

        private static boolean match(int roomPos, int o1, int o2, int o3) {
            return roomPos == o1 || roomPos == o2 || roomPos == o3;
        }

        public boolean isLowestPos(int pos) {
            return pos == p4;
        }

        public boolean isPosSetInRoom(int current, int o1, int o2, int o3) {
            if (isLowestPos(current)) {
                return true;
            }
            boolean p4match = match(p4, o1, o2, o3);
            boolean p3match = match(p3, o1, o2, o3);
            boolean p2match = match(p2, o1, o2, o3);
            return (p4match && current == p3) ||
                    (p3match && p4match && current == p2) ||
                    (p2match && p3match && p4match && current == p1);
        }
    }

    private static Map<Integer, List<Integer>> getPositions(String input) {
        Map<Integer, List<Integer>> retmap = new HashMap<>();
        String[] split = input.split("\n");
        List<String> lines = new ArrayList<>();
        lines.add(split[2]);
        lines.add("  #D#C#B#A#");
        lines.add("  #D#B#A#C#");
        lines.add(split[3]);

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
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
