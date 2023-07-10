## Longest common subsequence concept and derived problems

Another subset of dynamic programming problems.

If there are 2 strings, X & Y, then we need to find the longest common subsequence

#### X = a b e d f h
#### Y = a b c d g h r
#### LCS = a b d h

So start from the last character of both strings, compare the character at the end.

If they are same then include it in the LCS, and recursively call with length reduced by 1.

If they are not the same then we have two recursive cases, where we reduce the length of one string in one of the cases, and then reduce the length of the other string in the second case. And since we need the longest value, we take maximum of the two.

#### Recursive Code:

```
int lcs(String x, String y, int xi, int yi) {
    // base case
    if (xi == 0 || yi == 0) {
        return 0;
    }
    
    if (x.charAt[xi - 1] == y.charAt[yi- 1]) {
        return 1 + lcs(x, y, xi - 1, yi - 1);
    } else {
        return Math.max(
            lcs(x, y, xi - 1, yi),
            lcs(x, y, xi, yi - 1)
        );
    }
}

...

lcs(X, Y, X.length(), Y.length());

```

This code can be memoized by caching on the (xi, yi) pair with the value being the result that is evaluated for the pair, in a top-down manner.

This can very easily be done using a 2-D array where the rows and columns correspond to the pair (xi, yi).


#### Iterative Code:

Here we directly start populating value of the 2-D cache array in an iterative manner.
We start from the base case to the limits and populate the elements of the 2-D cache array.

Each element at (i,j) of the array represents the longest common subsequence of the substrings of the 2 original strings with lengths i & j respectively.

Now, we already know how LCS of 2 strings having lengths l1 and l2 are related to smaller length strings, as we can clearly see in the recursive code. We can translate the code to a nested iterative loop as follows.

```
int lcs(String x, String y) {
    int l1 = x.length(); int l2 = y.length();
    
    int[][] table = new int[l1 + 1][l2 + 1];
    for (int i = 0; i < l1; i++) {
        for (int j = 0; j < l2; j++) {
            if (i == 0 || j == 0) {
                table[i][j] = 0;
            } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                table[i][j] = 1 + table[i - 1][j - 1];
            } else {
                table[i][j] = Math.max(table[i][j - 1], table[i - 1][j]);
            }
        }
    }
    
    return table[l1][l2];
}
```

### Longest Common Substring

Since, substring is also a subsequence, the above concept can be modified to solve for a substring. This can be done by storing a value zero in the table as soon as there is a dis-continuity, i.e. if the characters don't match and by keeping track of the maximum while iterating.

```
int longestCommonSubstring(String x, String y) {
    int l1 = x.length(); int l2 = y.length();
    
    int[][] table = new int[l1 + 1][l2 + 1];
    int longest = 0;
    for (int i = 0; i < l1; i++) {
        for (int j = 0; j < l2; j++) {
            if (i == 0 || j == 0) {
                table[i][j] = 0;
            } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                table[i][j] = 1 + table[i - 1][j - 1];
            } else {
                table[i][j] = 0;
            }
            
            longest = Math.max(longest, table[i][j]);
        }
    }
    
    return longest;
}
```

### Printing Longest common subsequence

This can be done by back-tracking after the table filling process.


First we fill the table as usual.
Then we start from the last element of the table, and start moving towards the first element of the table.

Now, if we are at any table element (i,j), then two things can happen:
* Characters at [i] and [j] in the 2 strings respectively are same
* Characters at [i] and [j] in the 2 strings respectively are different

If the characters are the same, then they must have contributed to the lcs, hence we save that character to be included, and the move to the element at (i-1, j-1) in the table; why? because that's what we did while tabulating originally.

If the characters are not the same, then we move to the element to the left or to the element above, given which one is larger; why? again because thats the logic we used while tabulating originally. If the values are the same, we can move to either one, it doesn't matter.

The final string is the reverse of the LCS.

```
String lcsString(String x, String y) {
    int l1 = x.length(); int l2 = y.length();
    
    int[][] table = new int[l1 + 1][l2 + 1];
    for (int i = 0; i < l1; i++) {
        for (int j = 0; j < l2; j++) {
            if (i == 0 || j == 0) {
                table[i][j] = 0;
            } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                table[i][j] = 1 + table[i - 1][j - 1];
            } else {
                table[i][j] = Math.max(table[i][j - 1], table[i - 1][j]);
            }
        }
    }
    
    int i = l1, int j = l2;
    StringBuilder lcsRev = new StringBuilder();
    while(i > 0 && j > 0) {
        if (x.charAt(i) == y.charAt(j)) {
            lcsRev.append(x.charAt(i));
            i--; j--;
        } else if (table[i - 1][j] > table[i][j - 1]) {
            i--;
        } else {
            j--;
        }
    }
    return lcsRev.reverse().toString();
}
```

### Length of Shortest common super-sequence (SCS)

Shortest string made from combining the characters of 2 strings, so that the original strings are subsequences of the combined string.

This can be done by finding the LCS of the 2 strings, and then iterating on the strings character by character, comparing them with the LCS characters and generating the super-sequence. The key logic here is to consider the characters present in the LCS only once when creating the super-sequence.

Hence, the length of the SCS can be found as Length of string s1 + Length of string s2 - Length of LCS.

### Finding the SCS string

We iterate on the filled LCS table for the strings whose SCS is to be found.

On the same lines as the process of finding the actual LCS string, we include the character when both characters are same, but in addition to that when we move towards the larger number in case when both characters are not the same, we include the character which we are moving from.

Also we stopped while finding lcs, when either of i or j became zero, if do that here, we'll miss characters from one of the strings. Hence, we see if i or j are not zero, then we decrement them until they are zero and add the characters to the scs.

So the code becomes:

```
String scsString(String x, String y) {
    int l1 = x.length(); int l2 = y.length();
    
    int[][] table = new int[l1 + 1][l2 + 1];
    for (int i = 0; i < l1; i++) {
        for (int j = 0; j < l2; j++) {
            if (i == 0 || j == 0) {
                table[i][j] = 0;
            } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                table[i][j] = 1 + table[i - 1][j - 1];
            } else {
                table[i][j] = Math.max(table[i][j - 1], table[i - 1][j]);
            }
        }
    }
    
    int i = l1, int j = l2;
    StringBuilder scsRev = new StringBuilder();
    while(i > 0 && j > 0) {
        if (x.charAt(i - 1) == y.charAt(j - 1)) {
            scsRev.append(x.charAt(i - 1));
            i--; j--;
        } else if (table[i - 1][j] > table[i][j - 1]) {
            scsRev.append(x.charAt(i - 1));
            i--;
        } else {
            scsRev.append(y.charAt(j - 1));
            j--;
        }
    }
    
    while (i > 0) {
        scsRev.append(x.charAt(i - 1));
        i--;
    }
    while (j > 0) {
        scsRev.append(y.charAt(j - 1));
        j--;
    }
    
    return scsRev.reverse().toString();
}
```



### Minimum changes (insertion or deletion) to convert string A to string B

We notice that the characters in the LCS string of string A & string B will not be touched during this conversion, other character will either be inserted or deleted.

We'll do this: A -> LCS of A & B -> B

To convert string A to the LCS string, we do `d` number of deletions, which will be the length of A - length of LCS.
And the to convert the LCS to string B, we'll do `i` number of insertions, which will be the length of B - length of LCS.

So total operations = `d+i`; `d` deletions and `i` insertions.

### Longest Palindromic Subsequence

The subsequence of a string must be palindromic and we'll need to find the longest such subsequence.

This can be found by finding the LCS of the string and its reverse, because the palindromic subsequence would be a part of both the strings, and LCS would find the longest such string.

### Minimum deletions to convert a string into a palindrome

Here, if we think about it, the final string after deletion from the original string would be the longest palindromic subsequence of the string.

So we would just remove the characters which are not in the palindromic substring, hence the answer for this would the length of original string - length of the longest palindromic substring.