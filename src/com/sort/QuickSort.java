package com.sort;

public class QuickSort {

	public static void main(String[] args) {
		//int[] array = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3 };
		int[] array = { 9, 6, -2, 2, 5, 4, 3, 8, 1, 0, -1, 7, -3 };

		System.out.println("Before sort:");
		ArrayUtils.printArray(array);

		quickSort(array);

		System.out.println("After sort:");
		ArrayUtils.printArray(array);
		
		
	}

	public static void quickSort(int[] array) {
		//方法一
		subQuickSort(array, 0, array.length - 1);
		
		//方法二
		quicksort(array, 0, array.length - 1);
	}

	private static void subQuickSort(int[] array, int start, int end) {
		if (array == null || (end - start + 1) < 2) {
			return;
		}

		int part = partition(array, start, end);

		if (part == start) {
			subQuickSort(array, part + 1, end);
		} else if (part == end) {
			subQuickSort(array, start, part - 1);
		} else {
			subQuickSort(array, start, part - 1);
			subQuickSort(array, part + 1, end);
		}
	}

	private static int partition(int[] array, int start, int end) {
		int value = array[end];
		int index = start - 1;

		for (int i = start; i < end; i++) {
			if (array[i] < value) {
				index++;
				if (index != i) {
					ArrayUtils.exchangeElements(array, index, i);
				}
			}
		}

		if ((index + 1) != end) {
			ArrayUtils.exchangeElements(array, index + 1, end);
		}

		return index + 1;
	}
	

/**

     * description : 快速排序

     * @autor kwzhang

     * modify :2012-6-20

     *

     * @param pData

     * @param left

     * @param right

     * @return

     */

    static void quicksort(int n[], int left, int right) {

        int dp;

        if (left < right) {

            dp = partition2(n, left, right);

            quicksort(n, left, dp - 1);

            quicksort(n, dp + 1, right);

        }

    }

 

    public static int partition2(int n[], int left, int right) {

        int pivot = n[left];

        while (left < right) {

            while (left < right && n[right] >= pivot)

                right--;

            if (left < right)

                n[left++] = n[right];

            while (left < right && n[left] <= pivot)

                left++;

            if (left < right)

                n[right--] = n[left];

        }

        n[left] = pivot;

        return left;

    }
}
