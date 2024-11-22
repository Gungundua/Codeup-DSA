public class Delete {
    public TreeNode root;
    public Delete(TreeNode root) {
        this.root = root;
    }
    public TreeNode delete(TreeNode node, int value) {
        if (node == null) {
            return null; // Tree is empty or node not found
        }
        // If the node is the root, skip deletion
        if (node == root && node.data == value) {
            System.out.println("Cannot delete the root node.");
            return node;
        }
        // Search for the node to delete
        if (value < node.data) {
            node.left = delete(node.left, value); // Search in the left subtree
        } else if (value > node.data) {
            node.right = delete(node.right, value); // Search in the right subtree
        } else {
            // Node to be deleted found
            // Case 1: Node with no children
            if (node.left == null && node.right == null) {
                return null;
            }
            // Case 2: Node with one child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            // Case 3: Node with two children
            TreeNode successor = Minimum(node.right);
            node.data = successor.data;
            node.right = delete(node.right, successor.data);
        }
        return node;
    }
    public TreeNode Minimum(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
