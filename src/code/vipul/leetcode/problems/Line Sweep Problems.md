# Line Sweep problems

In these kinds of problems we work on different ranges of numbers, operating on them, and then we have to sweep the line to find a maximum/minimum value or something similar.

https://leetcode.com/discuss/study-guide/2166045/line-sweep-algorithms

For example in this problem:

https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/description/

We can add or subtract a fixed value to each array element and select a number from the resulting range.

We have to find out that which number if selected in all of such ranges would have the highest frequency.

For that, we have a tree map -> sort the ranges according to where they are starting from. Add the ranges with starting having a value of 1, and ending + 1 having a value of -1 (ending + 1 because ending is also counted as a valid answer).

Now we just start summing from the start, and maintain a global maximum to see when the value hits the maximum, and thats your answer.
