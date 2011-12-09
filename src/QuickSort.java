public class QuickSort {
    public static final int CUTOFF = 3;
    public static void main(String[] args) {
        int[] array = {10,0,3,1,4,7,9,6,8,5,2};
        quicksort(array, 0, (array.length -1));
        printArray(array);
    }

    public static int median3(int[] a, int left, int right) {
        System.out.println("\n ---------Median------------");
        int center = (left + right) / 2;
        System.out.println("Center index : " + center);
        System.out.print("Array: "); printArray(a);

            if (a[center] < (a[left])) {
                System.out.print("a[center] (" + a[center] + ") < a[left] (" 
                                                               + a[left] + ")");
                System.out.print("\nSwaping left with center...");
                swapReferences(a, left, center);
                System.out.print("\nArray: "); printArray(a);
            }
            if (a[right] < (a[left])) {
                System.out.print("a[right] (" + a[right] + ") < a[left] (" 
                                                               + a[left] + ")");
                System.out.print("\nSwaping left with right...");
                swapReferences(a, left, right);
                System.out.print("\nArray: "); printArray(a);
            }
            if (a[right] < (a[center])) {
                System.out.print("a[right] (" + a[right] + ") < a[center] (" 
                                                             + a[center] + ")");
                System.out.print("\nSwaping center with right...");
                swapReferences(a, center, right);
                System.out.print("\nArray: "); printArray(a);
            }
            //Place pivot at position right - 1
            System.out.println("placing pivot at right");
            swapReferences(a, center, right - 1);
            System.out.println("-----------end Median-----------\n");
            return a[right - 1];
    }

    private static void quicksort(int[] a, int left, int right) {

		System.out.println("------------Quicksort----------------");
        printArray(a);
		System.out.println("left + CUTOFF <= right");
        System.out.println(left + CUTOFF + "<=" + right);
        if (left + CUTOFF <= right) {
		
            System.out.println("\n\nleft = " + left + " right = " + right);
            int pivot = median3(a, left, right);
            System.out.print("Array: "); printArray(a);
            System.out.println("Pivot = " + pivot);
		
            int i = left;
            int j = right - 1;
            for(;;) {
                while(pivot > a[++i]);
                System.out.println("Pivot < " + a[i] + "\ni = " + i);
                while(pivot < a[--j]);
                System.out.println("Pivot > "+ a[j] +"\nj = " + j);
                if (i < j) {
                    System.out.println("Swapping i j");
                    swapReferences(a,i,j);
                    System.out.print("Array: "); printArray(a);
                    } else {
                        break;
                    }
            }
        System.out.println("\nRestoring pivot.");
        swapReferences(a, i, right - 1); //restore pivot
        System.out.print("Array: "); printArray(a);

       
        quicksort(a, left, i - 1); //sort small elements
		
        quicksort(a, i + 1, right); // sort large elements

        } else { // do an insertion sort on the subarray
            System.out.println("Small Array...");
            insertionSort(a);
			printArray(a);
        }
    }

    public static void swapReferences(int[] a, int left, int right) {
        int item = a[left];
        a[left] = a[right];
        a[right] = item;
    }

    public static void insertionSort(int[] a) {
			
        System.out.println("\n");
        int j;
	
        for (int p = 1; p < a.length; p++) {
            int tmp = a[p];
            for (j = p; j > 0 && tmp < (a[j - 1]); j--) {
                a[j] = a [j - 1];
            }
            a[j] = tmp;
        }
    }

    public static void printArray(int[] a) {
        for(int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
