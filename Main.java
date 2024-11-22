import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        Scanner input = new Scanner(System.in);
        // Input values for the tree
        System.out.println(Constants.INPUT);
        while (input.hasNext()) {
            if (input.hasNextInt()) {
                int value = input.nextInt();
                if (value >= 0 && value <= 999) {
                    tree.root = tree.insert(tree.root, value);
                } else {
                    System.out.println(Constants.INPUT_ERROR);
                }
            } else {
                String quit = input.next();
                if (quit.equalsIgnoreCase("done")) {
                    break;
                } else {
                    System.out.println(Constants.INVALID_INPUT);
                }
            }
        }
        boolean isRepeat = true;
        while (isRepeat) {
            System.out.println("Enter the choice : \n1.Print BST \n2.Mirror Image \n3.Delete Nodes \n4.Add number to all Nodes \n5.Quit");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    // Print the BST structure
                    System.out.println(Constants.BINARY_SEARCH_TREE);
                    tree.printTree();
                    break;
                case 2:
                    // Create a Mirror Image
                    System.out.println(Constants.MIRROR_IMAGE);
                    MirrorImage.swap_node(tree.root);
                    tree.printTree();
                    // Optionally restore the original tree
                    MirrorImage.swap_node(tree.root);
                    break;
                case 3:
                    // Delete nodes
                    Delete del = new Delete(tree.root);
                    System.out.println(Constants.DELETE_NODE);
                    // Ensure the input is an integer before proceeding
                    if (input.hasNextInt()) {
                        int valueToDelete = input.nextInt();
                        input.nextLine();  // Consume the newline character after nextInt()
                        del.delete(tree.root, valueToDelete);
                        System.out.println(Constants.UPDATION);
                        tree.printTree();
                    } else {
                        System.out.println(Constants.INPUT_INVALID);
                        input.nextLine();  // Clear the invalid input
                    }
                    break;
                case 4 :
                    System.out.println("Enter the number you want to add");
                    int number = input.nextInt();
                    AddNumber.addToAllNodes(tree.root, number);
                    tree.printTree();
                case 5 :
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }
}
