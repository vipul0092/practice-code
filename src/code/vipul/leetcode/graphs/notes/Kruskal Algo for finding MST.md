# Kruskal Method for finding MST of a graph

MST -> Minimum spanning tree

A minimum spanning tree is a special tree style connection of the nodes of a graph, where all the nodes of the graph are connected and the total sum of the weights of the edges is minimum.

This has a lot of real life use cases, like in networking and connecting places/cities.

Kruskal's algorithm can help us find the MST of a graph.

Also a graph having a cycle cant have a valid MST.

The algorithm uses the DisjointSet union-find operations, so make sure you know that first -> [Disjoint Set Union-Find](./Disjoint%20Set%20Union-Find.md)

The implementation can be found in [KruskalMST](../KruskalMST.java) class.

1. Sort the edges according to their weights in the ascending order.
   Now we'll progressively try to create a tree by adding the edges one by one.
2. Take the least weighing edge currently and add it to the current tree.
   If addition of the edge creates a cycle, that means we can't use the edge, move to the next edge, and so on.

The way of detecting a cycle is done using the Disjoint Set `find` operation.
We check that the representatives of the start and end points of an edge are different or not, if they are, that means there is no cycle, if they aren't that means that the edge forms a cycle. We reject or accept the edge based on this condition.

Once we've accepted the edge, we do the `union` operation on the start and end points of the accepted edge, because they are now part of the MST. We continue this process until we have no more edges to operate on.