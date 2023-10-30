import java.util.PriorityQueue;

// An AVL tree implementation
public class AVLTree<Key extends Comparable<Key>, Value extends Comparable<Value>> {

    public node root;  // root of the AVL tree


    // A node of the AVL tree
    public class node implements Comparable{
        public Key key;           // key of the node
        public Value value;       // value of the node
        private node left, right;  // left and right subtrees of the node
        private int height;        // height of the node

        public node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
        //converts the node object to a string
        public String toString(){
            return key+" "+value;
        }
        //overrides "toString()" to return a string representation of the key-value pair held in the node
        @Override

        //converts the min-heap to max-heap by switching the value returned by the compare function
        public int compareTo(Object o) {
            node temp= (node) o;
            if(this.value.compareTo(temp.value)<0) return 1;
            if(this.value.compareTo(temp.value)>0) return -1;
            return 0;
        }
    }

    // Returns the height of the node (or 0 if node is null)
    private int height(node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Returns the balance factor of the node
    private int balanceFactor(node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // Rotates the node to the left
    private node rotateLeft(node x) {
        node y = x.right;
        x.right = y.left;
        y.left = x;
        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return y;
    }

    // Rotates the node to the right
    private node rotateRight(node x) {
        node y = x.left;
        x.left = y.right;
        y.right = x;
        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return y;
    }

    // Balances the subtree rooted at the given node
    private node balance(node node) {
        if (node == null) {
            return null;
        }
        if (balanceFactor(node) > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        }
        else if (balanceFactor(node) < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }
        else {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
        return node;
    }

    // Returns the value associated with the given key
    public Value get(Key key) {
        node node = get(root, key);
        if (node == null) {
            return null;
        }
        return node.value;
    }
    public node get(Key key, Value value) {
        node node = get(root, key);
        if (node == null) {
            return null;
        }
        return node;
    }
    // Returns the node associated with the given key
    public node get(node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        }
        else if (cmp > 0) {
            return get(node.right, key);
        }
        else {
            return node;
        }
    }


    // Inserts the key-value pair into the AVL tree
    public void put(Key key, Value value) {root = put(root, key, value);
    }

    // Inserts the key-value pair into the subtree rooted at the given node
    private node put(node node, Key key, Value value) {
        if (node == null) {
            return new node(key,value);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        node = balance(node);
        return node;
    }
    //checks whether the tree contains a node with the specified key value and returns a boolean value
    public boolean contains(Key key){
        node curr = root;
        while(curr!=null){
            if(key.compareTo(curr.key)>0) curr=curr.right;
            else if (key.compareTo(curr.key)<0) curr = curr.left;
            else return true;
        }
        return false;
    }


    private String inOrderTraversal(node node) {
        if (node != null) {
            return inOrderTraversal(node.left) + (node.key + ":" + node.value+" ") + inOrderTraversal(node.right);
        }
        return "";
    }

    // Call this method from main:
    public String inOrderTraversal() {
        return inOrderTraversal(root);
    }

    //fills the priority queue with the node of the tree in decreasing order of their value
    public void fillheap(PriorityQueue heap){
        fillheap(heap, this.root);
    }

    //helper method for previous "fillheap" function.
    //fills the priority queue recursively with the nodes of the subtree rooted at node "n".
    //If the input node has a left child, call fillheap on the left child recursively.
    //If the input node has a right child, call fillheap on the right child recursively.
    public void fillheap(PriorityQueue heap, node n){
        if(n == null) return;
        else{
            fillheap(heap,n.left);
            heap.add(n);
            fillheap(heap, n.right);

        }
    }

    public static void main(String[] args) {
        AVLTree<Integer, String> tree = new AVLTree<>();

        tree.put(5, "apple");
        tree.put(2, "banana");
        tree.put(8, "orange");
        tree.put(1, "pear");
        tree.put(4, "grape");
        tree.put(7, "kiwi");
        tree.put(9, "pineapple");
        tree.put(3, "mango");
        tree.put(6, "peach");

        System.out.println("Inorder traversal:");
        tree.inOrderTraversal();

        System.out.println("Get value at key 4: " + tree.get(4));
    }
}
