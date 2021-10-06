/**
 * Author: Kevin Abrahams
 * Date: June 25, 2020
 * Class Description: This class represents the implementation
 * of a binary tree.
 */
import java.util.Stack;

public class BinaryTree {
    /**
     * Root tree
     */
    private Node root;

    /**
     * Make tree
     *
     * @param expression - Tree Expression
     * @throws InvalidTreeSyntax - Exception is thrown if tree syntax is not valid
     */
    public void makeTree(String expression) throws InvalidTreeSyntax {
        // If expression is null or empty throw expression
        if (null == expression || expression.trim().isEmpty()) {
            throw new InvalidTreeSyntax("Invalid Expression.");
        }
        if (!isBalanced(expression))
            throw new InvalidTreeSyntax("Expression is not balanced");
        root = null;
        root = makeTree(root, expression);
    }

    /**
     * Check expression is balanced or not
     *
     * @param expression
     * @return True/False
     * @throws InvalidTreeSyntax
     */
    private boolean isBalanced(String expression) throws InvalidTreeSyntax {
        char exp[] = expression.toCharArray();
        Stack<Character> st = new Stack<Character>();
        for (int i = 0; i < exp.length; i++) {
            if (exp[i] == '(') {
                st.push(exp[i]);
                if (exp[i + 1] == ' ')
                    throw new InvalidTreeSyntax("Invalid characters in the expression");
            }

            if (exp[i] == ')') {
                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            } else if (exp[i] != ' ' && exp[i] != '(' && exp[i] != ')' && !Character.isAlphabetic(expression.charAt(i))
                    && !Character.isDigit(expression.charAt(i))) {
                throw new InvalidTreeSyntax("Invalid characters in the expression");
            }

        }
        if (st.isEmpty())
            return true; /* balanced */
        return false;
    }

    /**
     * Recursive approach to make the tree
     *
     * @param root       - Root of the tree
     * @param expression - Expression
     * @return Tree node
     * @throws InvalidTreeSyntax
     */
    private Node makeTree(Node root, String expression) throws InvalidTreeSyntax {
        if (null == expression || expression.trim().isEmpty())
            return null;
        // Remove matching parenthesis
        if (expression.charAt(0) == '(') {
            expression = expression.substring(1, expression.length() - 1);
        }
        if (null == root) {
            root = new Node(expression.charAt(0), null, null);
            expression = expression.substring(1);
        }
        if (!expression.trim().isEmpty()) {
            expression = expression.trim();
            String leftSubstree = null;
            String rightSubstree = null;
            if (expression.contains(" ")) {
                Stack<Character> stack = new Stack<Character>();
                int end = 0;
                for (char ch : expression.toCharArray()) {
                    if (ch == '(') {
                        stack.push(ch);
                    } else if (ch == ')') {
                        stack.pop();
                        if (stack.isEmpty()) {
                            end++;
                            break;
                        }
                    }
                    end++;
                }
                if (end >= 0)
                    leftSubstree = expression.substring(0, end).trim();
                try {
                    if (end > 0)
                        rightSubstree = expression.substring(end + 1).trim();
                } catch (Exception e) {
                    throw new InvalidTreeSyntax("Tree is not balanced");
                }

            } else {
                leftSubstree = expression;
            }
            if (null != leftSubstree && !leftSubstree.trim().isEmpty())
                if (null == root.left) {
                    root.left = makeTree(root.left, leftSubstree);
                }
            if (null != rightSubstree && !rightSubstree.trim().isEmpty())
                if (null == root.right) {
                    root.right = makeTree(root.right, rightSubstree);
                }
        }
        return root;

    }

    /**
     * Check the tree is balanced or not
     *
     * @return True/False
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * Recursive approach to check tree is balanced or not
     */
    private boolean isBalanced(Node root) {

        // if tree is empty return true
        if (root == null)
            return true;

        int lh; // height of left subtree

        int rh; // for height of right subtree
        // Get the height of left and right sub trees
        lh = height(root.left);
        rh = height(root.right);

        if (Math.abs(lh - rh) <= 1 && isBalanced(root.left) && isBalanced(root.right))
            return true;

        /* Tree is not height-balanced */
        return false;
    }

    /**
     * Check tree is full or not
     *
     * @return True/False
     */
    public boolean isFull() {
        return isFull(root);
    }

    /**
     * Recursive approach to tree is full or not
     */
    private boolean isFull(Node root) {
        int h = getHeight();
        int n = getNodes();
        if (n == (Math.pow(2, h + 1) - 1))
            return true;
        return false;
    }

    /**
     * Check tree is proper or not
     *
     * @return True/False
     */
    public boolean isProper() {
        return isProper(root);
    }

    /**
     * Recursive approach to find the tree is proper or not
     */
    private boolean isProper(Node root) {
        // if empty tree
        if (root == null)
            return true;

        // if node is leaf node
        if (root.left == null && root.right == null)
            return true;

        // if left and right subtrees are not null then tree is full
        if ((root.left != null) && (root.right != null))
            return (isProper(root.left) && isProper(root.right));

        // Tree is not full
        return false;
    }

    /**
     * Get the height of the tree
     *
     * @return Height of the tree
     */
    public int getHeight() {
        return height(root);
    }

    /**
     * Recursive approach to find height of binary tree
     */
    public int height(Node root) {
        if (root == null)
            // base case
            return 0;
        else if (root.left != null || root.right != null)
            return 1 + Math.max(height(root.left), height(root.right));
        else
            return 0;
    }

    /**
     * Get the number of the nodes in tree
     *
     * @return Number of the nodes in tree
     */
    public int getNodes() {
        return getNodes(root);
    }

    /**
     * Recursive approach to find height of binary tree
     */
    public int getNodes(Node root) {
        if (root == null) {
            return 0;
        }
        /*
         * Call function recursively for left and right child and find their number of
         * nodes .
         */
        int ln = getNodes(root.left);
        int rn = getNodes(root.right);
        return rn + ln + 1;// Return sum of right and left subtree node count +1
    }

    /**
     * Get the inorder representation of the string
     *
     * @return Inorder representation of the string
     */
    public String inorder() {
        String str = "";
        str = getInorder(root, str);
        return str;
    }

    /**
     * Recursive approach to find the inorder traversal result
     *
     * @param root - Root of the tree
     * @param str  - String to hold the result
     * @return inorder traversal result
     */
    private String getInorder(Node root, String str) {
        if (root != null) {
            str += "(";
            str = getInorder(root.left, str);
            str += " " + root.data + " ";
            str = getInorder(root.right, str);
            str += ")";
        }
        return str;
    }

    /**
     * Class to hold tree data structure
     *
     *
     *
     */
    private static class Node {
        private char data;// Data stored in node
        private Node left;// Left child
        private Node right;// Right child

        /**
         * Constructor used to initialize the node data
         *
         * @param data  - Data stored in node
         * @param left  - Left child
         * @param right - Right child
         */
        public Node(char data, Node left, Node right) {
            super();
            this.data = data;
            this.left = left;
            this.right = right;
        }

    }

}