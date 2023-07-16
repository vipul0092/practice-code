# Solving Tree problems with Inorder traversal

Doing an inorder traversal, converts the tree into an array, which can then be operated upon to make a problem simpler.

For example to check if a tree is a BST, we can do an inorder traversal and check if the array is sorted.

To find the kth smallest element in a BST, we can do an inorder traversal and find the kth element from the start in the resultant array.

```
public List<Node> inorder(Node root) {
    List<Node> nodes = new ArrayList<>();
    if (root == null) return nodes;
    
    Stack<Node> stack = new Stack<>();
    while(root != null || !stack.isEmpty()) {
        // Keep going left until we can and pushing onto the stack
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
        root = stack.pop(); // Take the last visited node
        nodes.add(root); // Push it onto the list of nodes
        root = root.right; // Go right on the node in the next iteration
    }
    return nodes;
}
```