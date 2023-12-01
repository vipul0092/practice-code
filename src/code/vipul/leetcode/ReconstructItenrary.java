package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 14/09/23
 * https://leetcode.com/problems/reconstruct-itinerary/
 *
 * Hard
 * Graph
 */
public class ReconstructItenrary {

    public static void solve() {
        System.out.println(new ReconstructItenrary().findItinerary(List.of(
                List.of("JFK","SFO"),List.of("JFK","ATL"),List.of("SFO","ATL"),List.of("ATL","JFK"),List.of("ATL","SFO")
        )));
        System.out.println(new ReconstructItenrary().findItinerary(List.of(
                List.of("EZE","AXA"),List.of("TIA","ANU"),List.of("ANU","JFK"),List.of("JFK","ANU"),
                List.of("ANU","EZE"),List.of("TIA","ANU"),List.of("AXA","TIA"),List.of("TIA","JFK"),
                List.of("ANU","TIA"),List.of("JFK","TIA")
        )));
        System.out.println(new ReconstructItenrary().findItinerary(List.of(
                List.of("JFK","KUL"),List.of("JFK","NRT"),List.of("NRT","JFK")
        )));

    }

    public List<String> findItinerary(List<List<String>> _tickets) {
        String jfk = "JFK";

        Map<String, Map<String, Integer>> tickets = new TreeMap<>();
        int total = _tickets.size();

        for (List<String> ticket: _tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!tickets.containsKey(from)) {
                tickets.put(from, new TreeMap<>(Comparator.naturalOrder()));
            }
            tickets.get(from).merge(to, 1, Integer::sum);
        }

        List<String> answer = solve(tickets, jfk, total);
        return answer;
    }

    public List<String> solve(Map<String, Map<String, Integer>> tickets, String current,
                              int remaining) {
        if (!tickets.containsKey(current) || tickets.get(current).isEmpty()) {
            return List.of(current);
        }

        Map<String, Integer> temp = new TreeMap<>(tickets.get(current));
        List<String> answer = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : temp.entrySet()) {
            String to = entry.getKey();
            tickets.get(current).put(to, tickets.get(current).get(to) - 1);
            if (tickets.get(current).get(to) == 0) {
                tickets.get(current).remove(to);
            }
            List<String> rest = solve(tickets, to, remaining-1);
            if (rest.size() == remaining) { // We used all the rest of the tickets
                answer.add(current);
                answer.addAll(rest);
                break;
            }
            tickets.get(current).merge(to, 1, Integer::sum);
        }
        return answer;
    }
}
