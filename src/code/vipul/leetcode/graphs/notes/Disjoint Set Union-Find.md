# Disjoint Set

Disjoint set is a data structure that helps in defining relationships between items, i.e. whether different items belong to different groups or not.

For example:

A knows B & C, D knows E & F, B knows G, F knows H

Here, we can see there are two disjoint groups -> A,B,C,G and D,E,F,H

Identification of such classification can be done easily using the disjoint set data structure.

-----------------------------------
Each item in the problem is assigned an `id`.

We have 2 arrays in this data structure -> parent[] and size[]

Parent[] --> Defines the id of the item which is the parent of the item at `i`

By default an item is the parent of itself.

Size[] --> Defines the size of the group that the item belongs to

By default a group has a size of 1 (because of each item being one group initially)

### Find

When 2 items are related, they constitute a group, then we select one of them as a "representative of the group". Hence when 2 items are related, we make one the parent of another, so that whenever we want to find the parent of either item, we get the same value.

The `find` method gives you the representative of the group of the item passed to it.
You can find the implementation in the [DisjointSet](../DisjointSet.java) class.

It recursively tries to find the representative of the group that the item is in.
And then stores it in the `item`s position in the parent array.

(This is called as path compression, because if we dont save the latest value in the parent, then the worst case complexity of finding the parent becomes O(n), using this path compression technique, the avg complexity is alpha(n), where alpha the is Inverse-Ackermann function, which increases extremely slowly - has a value of 4 for n = 10^600)


### Union

Now lets take an example of a little complex version of this situation, where we are now joining 2 items that belong to completely different groups. Once we join them, they will become one big group.

Now how do we join these 2 groups? The operation to do that is called `union`.

You can find the implementation in the [DisjointSet](../DisjointSet.java) class.

Here, what we do is that we do the `find` operation on the 2 items to join, if they are already have the same parent, then we dont need to do anything, because that means they already belong to the same group.

Now, there are 2 groups to join, we merge them on the basis of the size of the groups. We will merge the smaller group into the larger group, so that the height of the connected items tree doesn't increase.

Once we determine which of the groups has bigger size, we make the representative with the bigger size the parent of the smaller size group, increment the size of the new group as the sum of the sizes.