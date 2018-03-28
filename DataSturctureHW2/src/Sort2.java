import java.util.*;

public class Sort2 {
	public static int left (int i) {
		//done
		return (2*i)+1;
	}
	public static int right (int i) {
		//done
		return ((2*i)+2);
	}
	public static int parent (int i) {
		//done
		return ((i-1)/2);
	}
	public static int[] max_heapify (int[] array, int heap_size, int i) {

		int l,r,largest,temp;
		l = left(i);
		r= right(i);
			if(l<heap_size && array[l]>array[i]) {
				largest = l;
			}
			
		else {
			largest = i;
		}
		if(r<heap_size && array[r]>array[largest]) {
			largest = r;
		}
		
		if(largest!=i) {
			temp = array[i];
			array[i]=array[largest];
			array[largest]=temp;
			max_heapify(array,heap_size,largest);
		}
		return array;
			
	}
	public static int[] build_heap (int[] array) {
			
		int heap_size=array.length; 
        for (int i = (heap_size-1)/2; i >= 0; i--) {
           max_heapify(array, heap_size, i);
	}
		return array ;
	}

	public static int[] heap_sort (int[] array) {
		int heap_size= array.length;
		build_heap(array); //calling the action
		for(int i=heap_size;i>0;i--){
		    int temp=array[0];
		    array[0]=array[i-1];
		    array[i-1]=temp;
		   heap_size=heap_size-1;
            max_heapify(array,heap_size, 0); //calling the action 
        }
		return array;
	}
	
	public static int[] quick_sort (int[] array, int p, int r) {
		
		if(p<r){
			int q=partition(array,p,r);
			quick_sort(array,p,q-1);
			quick_sort(array,q+1,r);
		
		}
		return array;
	}
	
	
	public static int partition (int[] array, int p, int r) {
			int x = array[r];
			int i = p-1;
			int temp;
		
			for(int j=p;j<r;j++){
				if(array[j]<=x){ 
					i=i+1;
					temp=array[i]; //swaping the elements 
					array[i]=array[j];
					array[j]=temp;
				}
			}
			temp=array[i+1];
			array[i+1]=array[r];//swaping the elements 
			array[r]=temp;
			return i+1;
			}
			
	/*
	 * the values in array range from 0 to k
	 */
	public static int[] counting_sort (int[] array, int k) {
		int b = array.length;
		int x = 0;
		int c[] = new int[k+1];
		for (int i :array) {
			c[i]++;
		}
		int[] Arrays = new int[b];
		for (int i = 0; i < c.length; i++) {
			int a = c[i];
			for (int j = 0; j < a; j++) {
				Arrays[x] = i;
				x++;
			}
		}
		
		return Arrays;

	}
	
/////////////////////////////////////////////////////////////
	/*
	 * n: the size of the output array
	 * k: the maximum value in the array
	 */
	public static int[] generate_random_array (int n, int k) {
		List<Integer> list;
		int[] array;
		Random rnd;
		
		rnd = new Random(System.currentTimeMillis());
		
		list = new ArrayList<Integer> ();
		for (int i = 1; i <= n; i++) 
			list.add(new Integer(rnd.nextInt(k+1)));
		
		Collections.shuffle(list, rnd);
		
		array = new int[n];
		for (int i = 0; i < n; i++) 
			array[i] = list.get(i).intValue();
		
		return array;
	}
	
	/*
	 * n: the size of the output array
	 */
	public static int[] generate_random_array (int n) {
		List<Integer> list;
		int[] array;
		
		list = new ArrayList<Integer> ();
		for (int i = 1; i <= n; i++) 
			list.add(new Integer(i));
		
		Collections.shuffle(list, new Random(System.currentTimeMillis()));
		
		array = new int[n];
		for (int i = 0; i < n; i++) 
			array[i] = list.get(i).intValue();
		
		return array;
	}
	
	/*
	 * Input: an integer array
	 * Output: true if the array is acsendingly sorted, otherwise return false
	 */
	public static boolean check_sorted (int[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i-1] > array[i])
				return false;
		}
		return true;
	}
	
	public static void print_array (int[] array) {
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + ", ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int k = 100;
		
		System.out.println("Heap sort starts ------------------");
		for (int n = 10; n <= 100000; n=n*2) {
			int[] array = Sort2.generate_random_array(n);
			long t1 = System.currentTimeMillis();
			array = Sort2.heap_sort(array);
			long t2 = System.currentTimeMillis();
			long t = t2 - t1;
			boolean flag = Sort2.check_sorted(array);
			System.out.println(n + "," + t + "," + flag);
		}
		System.out.println("Heap sort ends ------------------");

		
		System.out.println("Quick sort starts ------------------");
		for (int n = 10; n <= 100000; n=n*2) {
			int[] array = Sort2.generate_random_array(n);
			long t1 = System.currentTimeMillis();
			array = Sort2.quick_sort(array, 0, n-1);
			long t2 = System.currentTimeMillis();
			long t = t2 - t1;
			boolean flag = Sort2.check_sorted(array);
			System.out.println(n + "," + t + "," + flag);
		}
		
		System.out.println("Quick sort ends ------------------");
		
		System.out.println("Counting sort starts ------------------");
		for (int n = 10; n <= 100000; n=n*2) {
			int[] array = Sort2.generate_random_array(n, k);
			long t1 = System.currentTimeMillis();
			array = Sort2.counting_sort(array, k);
			long t2 = System.currentTimeMillis();
			long t = t2 - t1;
			boolean flag = Sort2.check_sorted(array);
			System.out.println(n + "," + t + "," + flag);
		}
		System.out.println("Counting sort ends ------------------");
	}

}
