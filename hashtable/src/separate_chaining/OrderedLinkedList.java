package separate_chaining;

/**
 * Ordered linked list is the fundamental data structure in hash table.
 */
public class OrderedLinkedList {
    // first node of a ordered linked list
    private LinkNode first;

    // nodes in a ordered linked list
    static final class LinkNode {
        private int iData;
        private LinkNode next;

        public LinkNode(int iData) {
            this.iData = iData;
        }

        public int getKey() {
            return iData;
        }

        public void displayLink() {
            System.out.println(iData + " ");
        }
    }

    /**
     * Constructor
     */
    public OrderedLinkedList() {
        first = null;
    }

    /**
     * Determines weather the list is empty.
     *
     * @return if the list is empty, return true
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Inserts a node in list.
     * Ensures that the order of the list is not destroyed after insertion.
     *
     * @param node the node need to be inserted
     */
    public void insert(LinkNode node) {
        int key = node.getKey();
        LinkNode previous = null;
        LinkNode current = first;
        while (current != null && current.getKey() < key) {
            previous = current;
            current = current.next;
        }

        if (previous == null) {
            first = node;
        } else {
            previous.next = node;
        }
        node.next = current;
    }

    /**
     * Deletes a node from list.
     *
     * @param key the node with specific key need to be deleted
     * @return deleted node
     */
    public LinkNode delete(int key) {
        LinkNode previous = null;
        LinkNode current = first;
        if (isEmpty()) {
            System.out.println("Linked is Empty!!!");
            return null;
        }

        while (current != null && current.getKey() != key) {
            previous = current;
            current = current.next;
        }

        if (previous == null) {
            LinkNode node = first;
            first = first.next;
            return node;
        } else {
            LinkNode node = current;
            previous.next = current.next;
            return node;
        }
    }

    /**
     * Finds a node in list.
     *
     * @param key the key of the node
     * @return target node
     */
    public LinkNode find(int key) {
        LinkNode current = first;
        while (current != null && current.getKey() <= key) {
            if (current.getKey() == key) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Prints list.
     */
    public void displayLink() {
        System.out.println("Link(First->Last) ");
        LinkNode current = first;
        while (current != null) {
            System.out.print(current.getKey() + " ");
            current = current.next;
        }
        System.out.println();
    }


}
