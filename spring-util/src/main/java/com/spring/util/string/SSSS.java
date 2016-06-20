package com.spring.util.string;

import com.alibaba.fastjson.JSON;

public class SSSS {
	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int b[] = { 3, 1, 5, 657, 456, 7, 4, 234, 234, 63, 6346, 4511, 7, 81111, 56, 58, 568, 5856, 875, 32, 52, 112, 422, 34, 23 };
		int count = 0;
		for (int i = 0; i < b.length; i++) {
			sort(a, b[i]);
			count++;
		}
		System.out.println(count);
		System.out.println(JSON.toJSONString(a));
	}

	private static int[] sort(int[] ss, int i) {
		int temp;
		for (int j = 0; j < ss.length; j++) {
			if (ss[j] < i) {
				temp = i;
				i = ss[j];
				ss[j] = temp;
			}
		}
		return ss;

	}
}
