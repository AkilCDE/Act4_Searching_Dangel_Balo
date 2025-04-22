import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
       
        System.out.println("Choose a searching algorithm to perform:");
        System.out.println("1. Linear Search");
        System.out.println("2. Binary search");
        System.out.println("3. Jump search");
        System.out.println("4. Exponential search");
        System.out.print("Enter your choice (1-4): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Enter the number of elements: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the elements: ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
            scanner.nextLine(); 
        }
        System.out.print("Enter the target value: ");
        int target = scanner.nextInt();
        scanner.nextLine(); 

        int result;

        result = switch (choice) {
            case 1 -> linearSearch(arr, target);
            case 2 -> {
                selectionSort(arr);
                yield binarySearch(arr, target);
            }
            case 3 -> {
                selectionSort(arr);
                yield jumpSearch(arr, target);
            }
            case 4 -> {
                selectionSort(arr);
                yield exponentialSearch(arr, target);
            }
            default -> {
                System.out.println("Invalid choice");
                System.exit(0);
                yield -1; 
            }
        };
        
        if (result != -1) {
            System.out.println("Search result: Element is found at index " + result);
        } else {
            System.out.println("Search result: Element is not found.");
        }

        }
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        if (n == 0) {
            return -1;
        }
        int step = (int) Math.sqrt(n);
        int prev = 0;
        while (prev < n && arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.sqrt(n);
            if (prev >= n) {
                return -1;
            }
        }
        while (arr[prev] < target) {
            prev++;
            if (prev == Math.min(step, n)) {
                return -1;
            }
        }
        if (arr[prev] == target) {
            return prev;
        }
        return -1;
    }

    public static int exponentialSearch(int[] arr, int target) {
        int n = arr.length;
        if (n == 0) {
            return -1;
        }
        if (arr[0] == target) {
            return 0;
        }
        int i = 1;
        while (i < n && arr[i] <= target) {
            i *= 2;
        }
        int low = i / 2;
        int high = Math.min(i, n - 1);
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}