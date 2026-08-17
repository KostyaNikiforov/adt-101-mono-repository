package part3;

public class SearchAlgorithms {
    public static int linearSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int linearSearchRecursive(int[] array, int value) {
        return linearSearchRecursive(array, value, 0);
    }

    private static int linearSearchRecursive(int[] array, int value, int index) {
        if (index >= array.length) {
            return -1;
        }
        if (array[index] == value) {
            return index;
        }
        return linearSearchRecursive(array, value, index + 1);
    }

    public static int binarySearch(int[] array, int value) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == value) {
                return mid;
            } else if (array[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchRecursive(int[] array, int value) {
        return binarySearchRecursive(array, value, 0, array.length - 1);
    }

    private static int binarySearchRecursive(int[] array, int value, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (array[mid] == value) {
            return mid;
        } else if (array[mid] < value) {
            return binarySearchRecursive(array, value, mid + 1, right);
        } else {
            return binarySearchRecursive(array, value, left, mid - 1);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i * 2;
        }

        int valueInArray = 50;
        int valueNotInArray = 51;

        System.out.println("Array: [0, 2, 4, ..., 198]");
        System.out.println();

        System.out.println("Search for " + valueInArray + " (exists in array):");
        System.out.println("Linear Search: " + linearSearch(array, valueInArray));
        System.out.println("Linear Search Recursive: " + linearSearchRecursive(array, valueInArray));
        System.out.println("Binary Search: " + binarySearch(array, valueInArray));
        System.out.println("Binary Search Recursive: " + binarySearchRecursive(array, valueInArray));
        System.out.println();

        System.out.println("Search for " + valueNotInArray + " (not in array):");
        System.out.println("Linear Search: " + linearSearch(array, valueNotInArray));
        System.out.println("Linear Search Recursive: " + linearSearchRecursive(array, valueNotInArray));
        System.out.println("Binary Search: " + binarySearch(array, valueNotInArray));
        System.out.println("Binary Search Recursive: " + binarySearchRecursive(array, valueNotInArray));
    }
}
