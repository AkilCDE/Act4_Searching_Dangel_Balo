import java.util.*;

class SearchingAlgorithms {
    
    
    public static int[] selectionSort(int[] arr) {
        int[] index = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
         index[i] = i;
        }
        
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            
        
            int tempIndex = index[minIndex];
         index[minIndex] = index[i];
         index[i] = tempIndex;
        }
        return index;
    }
    
    public static int linearSearch(int[] arr, int target) {
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        
        int[] arrCopy = arr.clone();
        int[] originalIndices = selectionSort(arrCopy);
        
        int low = 0, high = arrCopy.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arrCopy[mid] == target) return originalIndices[mid]; 
            if (arrCopy[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
    
    public static int jumpSearch(int[] arr, int target) {
        
        int[] arrCopy = arr.clone();
        int[] originalIndices = selectionSort(arrCopy);
        
        int n = arrCopy.length;
        int step = (int) Math.sqrt(n);
        int prev = 0;

        while (prev < n && arrCopy[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.sqrt(n);
            if (prev >= n) return -1;
        }

        for (int i = prev; i < Math.min(step, n); i++) {
            if (arrCopy[i] == target) return originalIndices[i]; 
        }

        return -1;
    }

    public static int exponentialSearch(int[] arr, int target) {
    
        int[] arrCopy = arr.clone();
        int[] originalIndices = selectionSort(arrCopy);
        
        if (arrCopy[0] == target) return originalIndices[0];
        int i = 1;
        while (i < arrCopy.length && arrCopy[i] <= target) {
            i *= 2;
        }
        
        
        int low = i / 2;
        int high = Math.min(i, arrCopy.length - 1);
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arrCopy[mid] == target) return originalIndices[mid]; // Return original index
            if (arrCopy[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Choose a searching algorithm to perform:");
            System.out.println("1. Linear Search");
            System.out.println("2. Binary Search");
            System.out.println("3. Jump Search");
            System.out.println("4. Exponential Search");

            System.out.print("Enter your choice (1-4): ");
            int choice = sc.nextInt();

            System.out.print("Enter the number of elements: ");
            int n = sc.nextInt();
            int[] arr = new int[n];

            System.out.println("Enter the elements:");
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            System.out.print("Enter the target value: ");
            int target = sc.nextInt();
        
            int result;

            result = switch (choice) {
                case 1 -> linearSearch(arr, target);
                case 2 -> binarySearch(arr, target);
                case 3 -> jumpSearch(arr, target);
                case 4 -> exponentialSearch(arr, target);
                default -> {
                    System.out.println("Invalid choice.");
                    yield -1;
                }
            };

            if (result >= 0) {
                System.out.println("Search result: Element is found at index " + result + ".");
            } else {
                System.out.println("Search result: Element is not found.");
            }
        }
    }
}
    