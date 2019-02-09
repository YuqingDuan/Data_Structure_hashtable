package open_addressing;

/**
 * As for open addressing method, if the data items can not be stored directly in the array subscripts calculated by the hash function, we need to find other locations.
 * There are three methods: linear detection, secondary detection and re-hashing.
 * In order to eliminate primitive aggregation and secondary aggregation, we use re-hashing.
 * The method is to hash the keywords again with different hash functions and use the result as the step size.
 * For specified keywords, the step size is constant throughout the detection, but different keywords use different step size.
 * The second hash function must have the following characteristics:
 * 1. Unlike the first hash function;
 * 2. Never output 0 (otherwise, there will be no step size, each detection is standing in place, the algorithm will fall into a dead cycle).
 */
public class MyHashTable {
    // store DataItem instances
    private DataItem[] hashArray;
    // initial size of array
    private int arraySize;
    // how many items of data are actually stored in an array
    private int itemNum;
    // used to delete data items
    private DataItem nonItem;

    // DataItem class, representing information for each data item
    private static final class DataItem {
        private int iData;

        public DataItem(int iData) {
            this.iData = iData;
        }

        public int getKey() {
            return iData;
        }
    }

    /**
     * Constructor
     */
    public MyHashTable() {
        // choose a prime number like 11, 13, 17...
        arraySize = 13;
        hashArray = new DataItem[arraySize];
        // deleted data entry marked -1
        nonItem = new DataItem(-1);
    }

    /**
     * Determines weather the array is full.
     *
     * @return if the array is full, return true
     */
    public boolean isFull() {
        return itemNum == arraySize;
    }

    /**
     * Determines weather the array is empty.
     *
     * @return if the array is empty, return true
     */
    public boolean isEmpty() {
        return itemNum == 0;
    }

    /**
     * Prints array.
     */
    public void display() {
        System.out.println("Table: ");
        for (int i = 0; i < arraySize; i++) {
            if (hashArray[i] != null) {
                System.out.print(hashArray[i].getKey() + " ");
            } else {
                System.out.print("** ");
            }
        }
    }

    /**
     * New index are obtained by hash function transformation.
     *
     * @param key the key in DataItem
     * @return insert location of element
     */
    public int hashFunction1(int key) {
        return key % arraySize;
    }

    /**
     * Resolves conflicts by re-hashing.
     *
     * @param key the key in DataItem
     * @return step size
     */
    public int hashFunction2(int key) {
        // experts have found that hash functions in the following form work very well:
        // stepSize = constant - key % constant
        // "constant" is a prime number and less than the capacity of the array
        return 5 - key % 5;
    }

    /**
     * Inserts data items.
     *
     * @param item data items need to be inserted
     */
    public void insert(DataItem item) {
        if (isFull()) {
            System.out.println("Hash Table is already full, re-hashing!");
            extendHashTable();
        }

        int key = item.getKey();
        int hashVal = hashFunction1(key);
        int stepSize = hashFunction2(key);
        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            hashVal += stepSize;
            hashVal %= arraySize;
        }
        hashArray[hashVal] = item;
        itemNum++;
    }

    /**
     * Arrays have a fixed size and cannot be extended, so an extended hash table can only create a larger array,
     * and then insert the data from the old array into the new array.
     * But the hash table calculates the location of the given data according to the size of the array,
     * so these data items can no longer be placed in the same position as the old array in the new array.
     * Therefore, it can not be copied directly, so we need to traverse the old array in order and insert each data item into the new array using insert method.
     * This process is called re-hashing. This is a time-consuming process, but this process is necessary if the array is to be extended.
     */
    public void extendHashTable() {
        int num = arraySize;
        // re-count, because the original data is transferred to the new expanded array
        itemNum = 0;
        // array size doubled
        arraySize *= 2;
        DataItem[] oldHashArray = hashArray;
        hashArray = new DataItem[arraySize];
        for (int i = 0; i < num; i++) {
            insert(oldHashArray[i]);
        }
    }

    /**
     * Deletes data items.
     *
     * @param key data items need to be deleted
     * @return deleted data item
     */
    public DataItem delete(int key) {
        if (isEmpty()) {
            System.out.println("Hash Table is Empty!");
            return null;
        }

        int hashVal = hashFunction1(key);
        int stepSize = hashFunction2(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                DataItem temp = hashArray[hashVal];
                hashArray[hashVal] = nonItem;
                itemNum--;
                return temp;
            }
            hashVal += stepSize;
            hashVal %= arraySize;
        }
        return null;
    }

    /**
     * Finds data items.
     *
     * @param key data items need to be find
     * @return target data items
     */
    public DataItem find(int key) {
        int hashVal = hashFunction1(key);
        int stepSize = hashFunction2(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal];
            }
            hashVal += stepSize;
            hashVal %= arraySize;
        }
        return null;
    }
}
