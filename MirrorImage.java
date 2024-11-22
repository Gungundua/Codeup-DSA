public class MirrorImage {
    public static void swap_node(TreeNode n) {
        if(n != null) {
            TreeNode temp = n.left;
            n.left = n.right;
            n.right = temp;

            swap_node(n.left);
            swap_node(n.right);
        }
    }

}
