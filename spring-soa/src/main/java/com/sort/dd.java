package com.sort;

public class dd {
	public static void main(String[] args) {
		int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1 };
		System.out.println("����֮ǰ��");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		// ֱ�Ӳ�������12
		for (int i = 1; i < a.length; i++) {
			// ������Ԫ��14
			int temp = a[i];
			int j;

			// for (j = i-1; j>=0 && a[j]>temp; j--) { //������temp�������ƶ�һλ 18
			// a[j+1] = a[j]; }
			// }
			for (j = i - 1; j >= 0; j--) {
				// ������temp�������ƶ�һλ22
				if (a[j] > temp) {
					a[j + 1] = a[j];
				} else {
					break;
				}
			}
			a[j + 1] = temp;
		}
		System.out.println();
		System.out.println("����֮��");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
}