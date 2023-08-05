# Silding window problems

Problems where we are asked to do some operations/calculations in a window of either fixed size or variable size.

Aditya Verma's Sliding window playlist is pretty solid, covers both fixed and variable size window approaches.

https://www.youtube.com/playlist?list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj

## Fixed size window

Here we are given a window size `k`, and we have to try to satisfy a particular constraint in all possible windows of size `k`, and then find the result according to that.

i -> the point where the current window starts

j -> the point where the current window ends

k -> window size

The structure of the code looks something like this:

-------

// k is given in the problem

int i = 0, j = 0, k;

// Loop until we reach the end

while j < array/string size

//  Do the calculation on the window based on incoming entry at index `j` which will help us with finding the answer

if (j - i + 1 < k) j++; // We haven't reached the window size yet

else if (j - i + 1 == k) // Reached window size, now we'll maintain it

// Since we have reached the window size, we'll find an answer from the calculations done

// We will update the calculation on the element at index `i` since the window is about slide

// Slide the window

i++;
j++;

--------

## Variable size sliding window

Here we are given a constraint `k`, and we have to find either the minimum or maximum window length satisfying that constraint.

This method is very frequently used for substring problems where we have to find substrings fulfilling a certain condition(s).

**IMP NOTE:** This method is only applicable only when the "calculations" you are doing are strictly monotonic, in other cases this method will fail.
(For example, sub-arrays sum to `k` where numbers in the array can be zero or negative)


// k is given in the problem

int i = 0, j = 0, k;

// Loop until we reach the end

while j < array/string size

//  Do the calculation on the window based on incoming entry at index `j` which will help us with finding the answer

if (Calculation on window < or > k) j++; // We haven't fulfilled the constraint yet

else if (Calculation on window == k) // fulfilled the constraint

// Since we have fulfilled the constraint,
we'll find an answer from current window size i.e. j - i + 1

max_window_size = Math.max(max_window_size, j - i + 1)

OR

min_window_size = Math.min(min_window_size, j - i + 1)

else // We have moved past the constraint

// Now we will move `i` until the calculation becomes less than the constraint

while (calc < k) i++;

j++

---------

### Additional note for variable size windows

If the calculation function is not strictly monotonic, then we can't apply the variable window approach above.

In those cases, we can use a hashmap approach in a lot of problems.

We have to find sub-arrays satisfying a conditions, right?
Any sub-array can be expressed as a difference of 2 prefix arrays, and thats the concept we are going to use.

So, for example we need to find all sub-arrays where the sum is `k` and the numbers in the array can be negative.

We can keep track of the sum of all prefixes of the elements of the array in a map. Basically how many times a particular prefix sum has occurred as we move in the array.
And on each element, we can see if there is `current prefix sum - k` item in the map. If there is, then that means there is/are some prefix array(s) which have that as the sum, and if we subtract those arrays from the current prefix array, we get a sub-array of sum `k`, which is what we need.

## Questions
https://leetcode.com/problems/longest-nice-subarray/
https://leetcode.com/problems/binary-subarrays-with-sum/description/
https://leetcode.com/problems/max-consecutive-ones-iii/
https://leetcode.com/problems/jump-game-vi/description/
https://leetcode.com/problems/subarray-sum-equals-k/
https://leetcode.com/problems/count-subarrays-with-score-less-than-k/
https://leetcode.com/problems/continuous-subarray-sum/description/
https://leetcode.com/problems/subarray-product-less-than-k/description/
    