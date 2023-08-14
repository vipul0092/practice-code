# Monotonic stack

This is a special usage of a stack to store values only in ascending or descending order. It can give you the minimum/maximum value in a range while traversing an array.

Stack Playlist by Aditya Verma, possibly the best resource for understanding monotonic stack:

https://www.youtube.com/playlist?list=PL_z_8CaSLPWdeOezg68SKkeLN4-T_jNHd

Problems related to monotonic stack:

https://leetcode.com/problems/online-stock-span/

https://leetcode.com/problems/largest-rectangle-in-histogram

https://leetcode.com/problems/maximal-rectangle/

https://leetcode.com/problems/min-stack

https://leetcode.com/problems/trapping-rain-water

# Monotonic Queue

Special usage of a Doubly-ended queue, to store values in ascending or descending order only from front -> last.
It can easily give you the maximum/minimum in a range during traversal in O(1). The front of the queue is the minimum/maximum
Otherwise you'd have to use Priority Queue type data structure, which will take logarithmic time, plus complex to implement.

The editorial of sliding window maximum gives a good overview of monotonic queue.

https://leetcode.com/problems/sliding-window-maximum/editorial/

Front of the queue gives the minimum/maximum element in the range.
What we basically do is if the queue is empty, then simply add the value to it.
If its not empty, then remove elements from the **_end_** of the queue, until the current element is greater/equal (for a decreasing queue) or smaller/equal (for an increasing queue) than the queue end. Add the value to the queue end.

Problems related to monotonic queue:
https://leetcode.com/problems/sliding-window-maximum/description

https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description

https://leetcode.com/problems/constrained-subsequence-sum/description

https://leetcode.com/problems/maximum-number-of-robots-within-budget/description/


## Other good problems are listed in this comment
https://leetcode.com/problems/maximum-number-of-robots-within-budget/solutions/2524838/java-c-python-sliding-window-o-n-solution/