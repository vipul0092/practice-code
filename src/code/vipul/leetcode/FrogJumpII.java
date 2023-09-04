package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 31/08/23
 * https://leetcode.com/problems/frog-jump-ii/
 *
 * Binary search on answer
 */
public class FrogJumpII {

    public static void solve() {
        System.out.println(new FrogJumpII().maxJump(new int[]{0,2,5,6,7}));
    }

    public int maxJump(int[] stones) {
        int min = 1, max = stones[stones.length-1]-stones[0]+1;

        int result = 0;
        while (min <= max) {
            int mid = min + ((max-min)>>1);
            if (possible(mid, stones)) {
                result = mid;
                max = mid-1;
            } else {
                min = mid+1;
            }
        }
        return result;
    }

    private boolean possible(int maxJump, int[] stones) {
        int min = stones[0];
        int max = stones[stones.length-1];
        int current = min;
        Set<Integer> marked = new HashSet<>();
        while(current < max) {
            int next = floor(current+maxJump, stones);
            if (next == -1 || stones[next] == current) return false;
            current = stones[next];
            marked.add(next);
        }

        List<Integer> toBeUsedInBack = new ArrayList<>(stones.length-marked.size()+1);
        for (int i = 0; i < stones.length; i++) {
            if (!marked.contains(i)) toBeUsedInBack.add(i);
        }
        toBeUsedInBack.add(stones.length-1);

        for (int i = toBeUsedInBack.size()-1; i >= 1; i--) {
            int diff = stones[toBeUsedInBack.get(i)] - stones[toBeUsedInBack.get(i-1)];
            if (diff > maxJump) return false;
        }
        return true;
    }

    private int floor(int value, int[] arr) {
        int min = 0, max = arr.length-1;
        int floor = -1;
        while(min <= max) {
            int mid = (min+max)>>1;
            if (arr[mid] < value) {
                floor = mid;
                min=mid+1;
            } else if (arr[mid] > value) {
                max = mid-1;
            } else {
                floor = mid;
                break;
            }
        }
        return floor;
    }
}
