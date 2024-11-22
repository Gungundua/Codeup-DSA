/**
 * Binary Search Tree Implementation and Visuliazation
 * Author: Gungun Dua
 * Date: 11th November 2024
 * This program implements binary search tree and hepls us to visulize it in tree format.
 */
import java.util.ArrayList;
import java.util.List;
public class BinarySearchTree {
    TreeNode root;

    //Inserts the node
    TreeNode insert(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        if (value < node.data) {
            node.left = insert(node.left, value);
        } else if (value > node.data) {
            node.right = insert(node.right, value);
        }
        return node;
    }

    //this is the method for printing the binary search tree
    public void printTree() {
        List<List<String>> levels = new ArrayList<>();
        List<TreeNode> currentLevel = new ArrayList<>();
        List<TreeNode> nextLevel = new ArrayList<>();
        // Start with the root node
        currentLevel.add(root);
        int maxWidth = 0;
        int nonNullNodes = 1;
        while (nonNullNodes != 0) {
            List<String> line = new ArrayList<>();
            nonNullNodes = 0;
            for (TreeNode node : currentLevel) {
                if (node == null) {
                    line.add(null);
                    nextLevel.add(null);
                    nextLevel.add(null);
                } else {
                    String valueStr = String.valueOf(node.data);
                    line.add(valueStr);
                    if (valueStr.length() > maxWidth) maxWidth = valueStr.length();
                    nextLevel.add(node.left);
                    nextLevel.add(node.right);
                    if (node.left != null) nonNullNodes++;
                    if (node.right != null) nonNullNodes++;
                }
            }
            levels.add(line);
            currentLevel = nextLevel;
            nextLevel = new ArrayList<>();
        }
        // from here code is for adjusting spaces per node
        int perNode = levels.get(levels.size() - 1).size() * (maxWidth + 4);
        for (int i = 0; i < levels.size(); i++) {
            List<String> line = levels.get(i);
            int halfNode = (int) Math.floor(perNode / 2f) - 1;
            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '|' : '_';
                        } else if (line.get(j) != null) {
                            c = '_';
                        }
                    }
                    System.out.print(c);
                    if (line.get(j) == null) {
                        for (int k = 0; k < perNode - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < halfNode; k++) {
                            System.out.print(j % 2 == 0 ? " " : "_");
                        }
                        System.out.print(j % 2 == 0 ? "_" : " ");
                        for (int k = 0; k < halfNode; k++) {
                            System.out.print(j % 2 == 0 ? "_" : " ");
                        }
                    }
                }
                System.out.println();
            }
            for (String nodeValue : line) {
                if (nodeValue == null) nodeValue = "";
                int leftPadding , rightPadding;
                rightPadding=leftPadding = (int) Math.floor(perNode / 2f - nodeValue.length() / 2f);
                for (int k = 0; k < leftPadding; k++) {
                    System.out.print(" ");
                }
                System.out.print(nodeValue);
                for (int k = 0; k < rightPadding; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            perNode /= 2;
        }
    }
}
