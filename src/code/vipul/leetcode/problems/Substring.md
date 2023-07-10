## Solving Substring problems

```
public static String minWindowHash(String s, String t) {
    // start, end ... two pointers that form the sliding window (which we want to check if it contains all
    // characters in t and if it is the smallest observed one)
    int start = 0, end = 0;

    // counter monitors the total number of characters in t that are missing in current sliding window
    int counter = t.length();

    // two variables to store information about smallest valid window observed until now
    int minStart = 0, minLength = Integer.MAX_VALUE;

    // we want to know both which characters are missing, and how many of them are missing in the current
    // window. To monitor this we use a hashmap. Keys are unique characters in t, and values are their occurrences
    Map<Character, Integer> map = new HashMap<>();
    for (char c : t.toCharArray()) {
        map.put(c, map.getOrDefault(c, 0)+1);
    }

    // all interesting windows are discovered when "end" is increased to s.length()
    while (end < s.length()) {
        char currentEndingChar = s.charAt(end);

        // if the character pointed to by "end" is contained in t
        if (map.containsKey(currentEndingChar)) {
            // and we are still missing that character (hence its value in map is larger than 0), we decrease counter
            if (map.get(currentEndingChar)>0) counter--;

            // We decrease its value by one.
            // value = 0 means that the number of this character inside current window is equal to the total number of it
            // contained in t
            // value = -2 means that we have two extra duplicates of that character in the window. So when we start to
            // shrink the window, leaving this character two times won't invalidate the resulting window!
            map.put(currentEndingChar, map.get(currentEndingChar)-1);
        }

        // If all characters from t are found in current window ("start" to "end"), we want to start reduce the window's
        // size by increasing "start"
        while(counter == 0) {
            // of course, check if the current valid window is the smallest
            if (end - start < minLength) {
                minStart = start;

                // Make sure that the minLength is the actual length of the window
                minLength = end - start + 1;
            }

            // Now, since we plan to make the window smaller by increasing "start", we want to know two things:
            // 1. Is the character, which we are leaving now, contained in t?
            // 2. If it is contained in t, how many of its duplicates are contained in the current window? If there
            // are plenty of them, leaving it (start++) won't immediately invalidate the resulting window.
            if (map.containsKey(s.charAt(start))) {

                // If the character pointed to by "start" is of interest, since we are leaving it, we increase its
                // value in the map by 1, to indicate that we miss it once
                map.put(s.charAt(start), map.get(s.charAt(start))+1);

                // If the value is greater than 0, that means the next window is no more valid, and we need to move
                // "end" forward to find the next valid window (in the next iteration)
                if (map.get(s.charAt(start))>0) counter++;
            }

            // After all checks, we reduce window size by increasing "start" by 1
            start++;
        }

        // The while loop is left, so the current window is not valid and we need to increment "end" to look for
        // the next valid window.
        end++;
    }

    if (minLength != Integer.MAX_VALUE) return s.substring(minStart, minStart + minLength);
    return "";
}
```
