package separate_chaining;

/**
 * Another method is to set a linked list in each cell of the hash table (i.e. the chain address method).
 * The key value of a data item is mapped to the cell of the hash table as usual, and the data item itself is inserted into the linked list of the cell.
 * Other data items that are also mapped to this location need only be added to the list, and there is no need to find empties in the original array.
 */
public class MyHashTable {
    // store ordered linked lists in an array
    private OrderedLinkedList[] hashArray;
    // initial size of array
    private int arraySize;

    /**
     * Constructor
     */
    public MyHashTable(int size) {
        arraySize = size;
        hashArray = new OrderedLinkedList[arraySize];

        for (int i = 0; i < arraySize; i++) {
            hashArray[i] = new OrderedLinkedList();
        }
    }

    /**
     * Prints list.
     */
    public void displayTable() {
        for (int i = 0; i < arraySize; i++) {
            System.out.print(i + ": ");
            hashArray[i].displayLink();
        }
    }

    /**
     * The mapping index are obtained by hash function transformation.
     *
     * @param key the key in LinkNode
     * @return insert location of element in array
     */
    public int hashFunction(int key) {
        return key % arraySize;
    }

    /**
     * Inserts data items.
     *
     * @param node data need to be inserted
     */
    public void insert(OrderedLinkedList.LinkNode node) {
        int key = node.getKey();
        int hashVal = hashFunction(key);
        // just add it directly to the list
        hashArray[hashVal].insert(node);
    }

    /**
     * Finds data items.
     *
     * @param key data need to be find
     * @return target data items
     */
    public OrderedLinkedList.LinkNode find(int key) {
        int hashVal = hashFunction(key);
        OrderedLinkedList.LinkNode node = hashArray[hashVal].find(key);
        return node;
    }

    /**
     * Deletes data items.
     *
     * @param key data need to be deleted
     * @return deleted data item
     */
    public OrderedLinkedList.LinkNode delete(int key) {
        int hashVal = hashFunction(key);
        OrderedLinkedList.LinkNode temp = find(key);
        // find the data item to delete from the list and delete it directly
        hashArray[hashVal].delete(key);
        return temp;
    }
}
