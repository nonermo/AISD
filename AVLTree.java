public class AVLTree {
    public Node root;
    public int operationCount;

    public int height(Node node) {
        operationCount++;
        return node == null ? 0 : node.height;
    }

    public int getBalance(Node node) {
        operationCount++;
        return node == null ? 0 : height(node.right) - height(node.left);
    }

    public void fixHeight(Node node) {
        operationCount++;
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    public Node rotateRight(Node y) {
        operationCount++;
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        fixHeight(y);
        fixHeight(x);

        return x;
    }

    public Node rotateLeft(Node x) {
        operationCount++;
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        fixHeight(x);
        fixHeight(y);

        return y;
    }

    public Node balance(Node node) {
        fixHeight(node);

        if (getBalance(node) == 2) {
            if (getBalance(node.right) < 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        if (getBalance(node) == -2) {
            if (getBalance(node.left) > 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        return node;
    }

    public Node insert(Node node, int key) {
        if (node == null) {
            operationCount++;
            return new Node(key);
        }

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        return balance(node);
    }

    public Node findMin(Node node) {
        operationCount++;
        while (node.left != null) {
            node = node.left;
            operationCount++;
        }
        return node;
    }

    public Node removeMin(Node node) {
        if (node.left == null)
            return node.right;
        node.left = removeMin(node.left);
        return balance(node);
    }

    public Node delete(Node node, int key) {
        if (node == null)
            return null;

        if (key < node.key)
            node.left = delete(node.left, key);
        else if (key > node.key)
            node.right = delete(node.right, key);
        else {
            Node left = node.left;
            Node right = node.right;
            if (right == null)
                return left;
            Node min = findMin(right);
            min.right = removeMin(right);
            min.left = left;
            return balance(min);
        }

        return balance(node);
    }

    public boolean search(Node node, int key) {
        while (node != null) {
            operationCount++;
            if (key == node.key)
                return true;
            node = (key < node.key) ? node.left : node.right;
        }
        return false;
    }
}
