# Topological Sort

Topological sort is done on a Direct Acyclic Graph (DAG) where some of the nodes are  dependent on each other.

We determine an order of the nodes where the ones which don't depend on others are placed earlier, and the ones which are dependent on some other node are placed later.

So, think of it like courses to taken in a university.

To take Course 1, you have to take 2, to take 2 you have to take 3, and you can take 5 without any conditions, so the topologically sorted order can be something like this 5,3,2,1 i.e. you can take the courses in this order.

This method has a lot of practical uses cases like determining the schedule of courses, finding the order in which a build system should carry out the builds where different modules are dependent on each other etc.

## Algorithm

The pre-requisite of the algorithm are:

Adjacency list -> i.e which node is connected to which node (this is a one way connection)

in-degree of each node -> This is the count of number of incoming connections to each node.

The algorithm goes as follows:
* Find the list of the nodes with in-degree as `0`. add them to a queue or a Stack.
  If there are no such nodes, then topological sort is not possible. (i.e. there is a cycle)
* Repeat the following steps until the stack/queue is not empty
   * Remove a node from the stack/queue
   * Add it to the visited list
   * Reduce the in-degree of all the neighbors of the node
   * Add the neighbors whose in-degree becomes zero in the above step to the stack/q
* Return the visited list -> that is the topologically sorted order

If the process returns empty list, that means that the topological sort is not possible.

Implementation can be found in the [TopologicalSort](../TopologicalSort.java) class.

Good problems on Topological sort:

https://www.codingninjas.com/studio/problems/alien-dictionary_630423