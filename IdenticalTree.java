public class IdenticalTree {
        static boolean isIdentical(TreeNode Node1, TreeNode Node2) {
            if (Node1 == null && Node2 == null)
                return true;
            if (Node1 == null || Node2 == null)
                return false;
            return (Node1.data == Node2.data) &&
                    isIdentical(Node1.left, Node2.left) &&
                    isIdentical(Node1.right, Node2.right);
            }
        }
