import java.util.*;

class Solution {
    private static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1]; // temporary array
        int left = low;      // starting index of left half of arr
        int right = mid + 1;   // starting index of right half of arr
        int k = 0; // index of the temporary array

        //storing elements in the temporary array in a sorted manner//

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp[k] = arr[left];
                left++;
            } else {
                temp[k] = arr[right];
                right++;
            }
            k++;
        }

        // if elements on the left half are still left //

        while (left <= mid) {
            temp[k] = arr[left];
            left++;
            k++;
        }

        //  if elements on the right half are still left //
        while (right <= high) {
            temp[k] = arr[right];
            right++;
            k++;
        }

        // transfering all elements from temporary to arr //
        for (int i = low; i <= high; i++) {
            arr[i] = temp[i - low];
        }
    }

    public static void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);  // left half
            mergeSort(arr, mid + 1, high); // right half
            merge(arr, low, mid, high);  // merging sorted halves
        }
    }
}
 class tUf {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = 6;
    int arr[]=new int[n];
    for(int i=0;i<n;i++){
        System.out.print(arr[i]);
    }
       
       
        Solution.mergeSort(arr, 0, n - 1);
        
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
        

}