import java.awt.*;
import java.util.ArrayList;
import javax.swing.Timer;

public class Sort
{
	public static final int BUBBLE_SORT = 1;
	public static final int INSERTION_SORT = 0;
	public static final int SELECTION_SORT = -1;
	public static final int MERGE_SORT = 2;
	public static final int QUICK_SORT = 3;
	private static long beginTime;
	private static long endTime;
	private static long counterForGets;

	private void swap(ArrayList<Comparable> list, int a, int b)
	{
		Comparable temp = list.get(a);
		counterForGets++;
		list.set(a, list.get(b));
		counterForGets++;
		list.set(b, temp);
	}
	public static long getGetCounter()
	{
		return counterForGets;
	}
	public long getTime()
	{
		return endTime - beginTime;
	}
	public void sort(ArrayList<Comparable> list, int sortType)
	{
		counterForGets = 0;
		if (sortType == BUBBLE_SORT)
		{
			beginTime = System.nanoTime();
			bubbleSort(list);
		}
		else if (sortType == INSERTION_SORT)
		{
			beginTime = System.nanoTime();
			insertionSort(list);
		}
		else if (sortType == SELECTION_SORT)
		{
			beginTime = System.nanoTime();
			selectionSort(list);
		}
		else if (sortType == MERGE_SORT)
		{
			beginTime = System.nanoTime();
			mergeSort(list);

		}
		else if (sortType == QUICK_SORT)
		{
			beginTime = System.nanoTime();
			quickSort(list);
		}
		endTime = System.nanoTime();
		System.out.println("The time is " + getTime());
	//	System.out.println("The getCounter is " + getGetCounter());
	}
	public void selectionSort(ArrayList<Comparable> list)
	{
		int size = list.size();
		Comparable minimum = list.get(0);
		counterForGets++;
		//find the minimum value
		int swapWith = 0;
		while (swapWith < size)
		{
			minimum = list.get(swapWith);
			counterForGets++;
			int index = swapWith;
			for (int i = swapWith; i < size; i++)
			{
				counterForGets++;
				if (minimum.compareTo(list.get(i))>0)
				{
					minimum = list.get(i);
					//counterForGets++;
					index = i;
				}
			}
			swap(list, swapWith, index);
			swapWith++;
		}
	}
	public void insertionSort(ArrayList<Comparable> list)
	{
		int size = list.size();
		int eDone = 1;
		for (int i = 1; i < size; i++)
		{
			Comparable next = list.get(i);
			list.add(this.binarySearch(list, next, 0, eDone++), list.remove(i));
		}
	}
	public void bubbleSort(ArrayList<Comparable> list)
	{
		//loop through
		//check each pair
		//if out of order, switch
		boolean sorted = false;
		int size = list.size();
		int counter = 1;
		while (!sorted)
		{
			boolean noSwapsDone = true;
			for (int i = 0; i < size-counter; i++)
			{
				counterForGets++;
				counterForGets++;
				if (list.get(i+1).compareTo(list.get(i))<0)
				{
					swap(list, i, i+1);
					noSwapsDone = false;
				}
			}
			counter++;
			if (noSwapsDone)
				sorted = true;
		}
	}
	public int binarySearch(ArrayList<Comparable> list, Comparable value, int s, int e)
	{
		int halfList = (s+e)/2;
		if (s == e)
			return s;
		if (value.compareTo(list.get(halfList)) < 0)
		{
			return binarySearch(list, value, s, halfList);
		}
		if (value.compareTo(list.get(halfList)) > 0)//halfList is smaller
		{
			return binarySearch(list, value, halfList+1, e);
		}
		return halfList + 1;
	}
/*	public static ArrayList<Comparable> merge(ArrayList<Comparable> list2, int start, int mid, int end)
	{
		ArrayList<Comparable> returning = new ArrayList<Comparable>(list1.size()+list2.size());
		for (int i = 0; i < list1/2)
		{

		}
	}*/
	public ArrayList<Comparable> merge(ArrayList<Comparable> list1, ArrayList<Comparable>list2)
	{
		int index1 = 0;
		int index2 = 0;
		int size1 = list1.size();
		int size2 = list2.size();

		ArrayList<Comparable> returning = new ArrayList<Comparable>(size1 + size2);
		while (index1 < size1 && index2 < size2)
		{
			if (list1.get(index1).compareTo(list2.get(index2)) < 0)//index1  before
			{
				returning.add(list1.get(index1));
				index1++;
			}
			else
			{
				returning.add(list2.get(index2));
				index2++;
			}
		}
		if (index1 < size1)
		{
			for(int i = index1; i < size1; i++)
			{
				returning.add(list1.get(i));
			}
		}
		else if (index2 < size2)
		{
			for (int i = index2; i < size2; i++)
			{
				returning.add(list2.get(i));
			}
		}
		return returning;
	}
	private ArrayList<Comparable> mergeSortPrivate(ArrayList<Comparable> list)
	{
		if (list.size() <= 1)
		{
			return list;
		}
		ArrayList<Comparable> half1 = new ArrayList<Comparable>(list.size()/2);
		ArrayList<Comparable> half2 = new ArrayList<Comparable>(list.size()-(list.size()/2));
		for (int i = 0; i < list.size()/2; i++)
		{
			half1.add(list.get(i));
		}
		for (int i = list.size()/2; i < list.size(); i++)
		{
			half2.add(list.get(i));
		}
		return (merge(mergeSortPrivate(half1), mergeSortPrivate(half2)));
	}
	void mergeSort(ArrayList<Comparable> list)
	{
		ArrayList<Comparable> temp = new ArrayList<Comparable>(list.size());
		temp = mergeSortPrivate(list);
		for (int i = 0; i < temp.size(); i++)
		{
			list.set(i, temp.get(i));
		}
	}
	public void quickSort(ArrayList<Comparable> list)
	{
		quickSortPrivate(list, 0, list.size()-1);
	}
	private void quickSortPrivate(ArrayList<Comparable> list, int start, int end)
	{
		//get pivot
		//set frontIterator
		//set backIterator
		//while loop with frontIterator isnt backIterator
			//while front iterator keeps running
			//while backIterator keeps running
			//if the frontIterator<= backIterator, swap
		//recursion
		Comparable pivot = list.get(start);//pivot is the first one
		int frontIterator = start;
		int backIterator = end;
		while (frontIterator<=backIterator)
		{
			//front Iterator
			while (list.get(frontIterator).compareTo(pivot)<0)//while nums greater than pivot
			{
				frontIterator++;
			}
			while (list.get(backIterator).compareTo(pivot)>0)
			{
				backIterator--;
			}
			if (frontIterator <= backIterator)
			{
				swap(list, frontIterator, backIterator);
				frontIterator++;
				backIterator--;
			}
		}
		if (start < backIterator)
			quickSortPrivate(list, start, backIterator);
		if (frontIterator < end)
			quickSortPrivate(list, frontIterator, end);
	}
/*	public static void main (String[] args)
	{
		ArrayList<Comparable> list = new ArrayList<Comparable>();
	//	ArrayList<Comparable> list2 = new ArrayList<Comparable>();
		list.add(new Integer(1160)); //8
		list.add(new Integer(1)); //0
		list.add(new Integer(8)); //3
		list.add(new Integer(9)); //4
		list.add(new Integer(2)); //1
		list.add(new Integer(2)); //2
		list.add(new Integer(543215));
		list.add(new Integer(10)); //5
		list.add(new Integer(50)); //6
		list.add(new Integer(1160)); //7
		list.add(new Integer(2)); //2
		list.add(new Integer(54)); //6

		list.add(new Integer(1160)); //9
		list.add(new Integer(1160)); //10
		list.add(new Integer(1160)); //11
		list.add(new Integer(1160)); //12
		list.add(new Integer(8)); //3
		list.add(new Integer(9)); //4
		list.add(new Integer(10)); //5

	//	list.add(new Integer(5));
	//	list.add(new Integer(7));
	//	list.add(new Integer(9));
	//	list.add(new Integer(102332));
	//	list.add(new Integer(2345439));
	//	list.add(new Integer(339));

		Sort sort = new Sort();
	/*	for (int i = 0; i < list.size(); i++)
		{
			System.out.print(list.get(i) + ", ");
		}
		System.out.println("");
		beginTime = System.nanoTime();
		//sort.selectionSort(list);

		//sort.insertionSort(list);
		//sort.bubbleSort(list);
		//ArrayList<Comparable> result = mergeSort(list);
	//	ArrayList<Comparable> result = merge(list, list2);
	for (int i = 0; i < list.size(); i++)
		{
			System.out.print(list.get(i) + " ");
		}
		System.out.println("before");
		quickSort(list);
		System.out.println("after");
	//mergeSort(list);
		for (int i = 0; i < list.size(); i++)
		{
			System.out.print(list.get(i) + " ");
		}
		System.out.println("");


	//	System.out.println("The index of x should be "+sort.binarySearch(list, 5));
	//	System.out.println("3The index of y should be " + sort.binarySearch(list, 10));
	//	System.out.println("3The index of y should be " + sort.binarySearch(list, 52));
	//	System.out.println("2The index of y should be " + sort.binarySearch(list, 5430));

		endTime = System.nanoTime();

		//System.out.println("aftersort");
//		for (int i = 0; i < list.size(); i++)
//		{
//			System.out.print(list.get(i) + ", ");
//		}
		System.out.println("The total time was " + sort.getTime());
	}*/
}