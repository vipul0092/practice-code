package code.vipul.leetcode;
import code.vipul.aoc2022.Solve13.Item;

import java.util.*;

import static code.vipul.aoc2022.Solve13.parse;


/**
 * Created by vgaur created on 18/07/23
 * https://leetcode.com/problems/lru-cache/
 */
public class LRU {

    public static void solve() {
        List<Item> items = parse("[[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]").getValues();
        LRUCache cache = new LRUCache(items.get(0).getValues().get(0).getValue());

        for (int i = 1; i < items.size(); i++) {
            List<Item> t = items.get(i).getValues();
            if (t.size() == 1) {
                System.out.println(cache.get(t.get(0).getValue()));
            } else { // t.size() == 2
                cache.put(t.get(0).getValue(), t.get(1).getValue());
            }
        }
    }

    static class LRUCache {

        private final Map<Integer, Node> map;
        private Node front;
        private Node head;
        private final int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.front = null;
            this.head = null;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                return getAndMove(key);
            }
            return -1;
        }

        public void put(int key, int value) {
            int existing = get(key);
            if (existing == -1) { //first time
                if (map.size() == capacity) { // over capacity, remove
                    int toRemove = head.key;
                    map.remove(toRemove);
                    head = head.next;
                    if (front.key == toRemove) {
                        front = null;
                    }
                }
                map.put(key, new Node(value, key));
                getAndMove(key);
            } else { //already exists
                map.get(key).val = value;
                getAndMove(key);
            }
        }

        // gets the value and moves the node to the front
        private int getAndMove(int key) {
            Node node = map.get(key);
            if (head == null) {
                head = node;
                head.linkPrev(null);
            } else if (head.key == key && head.next != null) {
                head = head.next;
                head.linkPrev(null);
            }
            if (front == null) {
                front = node;
            } else if (front.key != node.key) {
                if (node.prev != null) {
                    node.prev.linkNext(node.next);
                }
                front.linkNext(node);
                node.linkNext(null);
                front = node;
            }

            return node.val;
        }

        private void print() {
            Node curr = head;
            while(curr != null) {
                System.out.print(curr.key + " -> ");
                curr = curr.next;
            }
            System.out.println(" null");
        }

        public class Node {
            private Node next;
            private Node prev;
            private int val;
            private final int key;

            public Node(int v, int k) {
                this.val = v;
                this.key = k;
            }

            public void linkNext(Node node) {
                this.next = node;
                if (node != null) {
                    node.prev = this;
                }
            }
            public void linkPrev(Node node) {
                this.prev = node;
                if (node != null) {
                    node.next = this;
                }
            }
        }
    }
}
