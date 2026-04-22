class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> {
            if (i1[0] == i2[0]) return i1[1] - i2[1];
            return i1[0] - i2[0];
        });

        int[] current = intervals[0];
        List<int[]> answer = new ArrayList<>();

        for (int i = 1; i < intervals.length; i++) {
            int[] process = intervals[i];
            if (process[0] > current[1]) {
                answer.add(current);
                current = process;
            } else {
                current[0] = Math.min(current[0], process[0]);
                current[1] = Math.max(current[1], process[1]);
            }
        }
        answer.add(current);

        int[][] ret = new int[answer.size()][2];
        for (int i = 0; i < answer.size(); i++) {
            ret[i] = answer.get(i);
        }
        return ret;
    }
}
