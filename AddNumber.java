public class AddNumber {
    public static void addToAllNodes(TreeNode node, int valueToAdd) {
        if (node != null) {
            node.data += valueToAdd; // Add value to current node
            addToAllNodes(node.left, valueToAdd);  // Recurse on left subtree
            addToAllNodes(node.right, valueToAdd); // Recurse on right subtree
        }
    }
}
