package com.lyc.soa.thread;

public class dsds {
	public static boolean ss(char c) {
		System.out.println(c);
		return true;
	}

	public static void main(String[] args) {
		/*int c = 0;
		for (ss('A'); ss('B') && c < 4; ss('C')) {
			c++;
			System.out.println('D');
		}*/
		new A();
	}
}//abdcbcdb
abstract class B{
	public B() {
		t();
	}
	 public void t() {
		System.out.println('B');
		};
}
class A extends B{
	public A(){
		t();
	}
	/*@Override
	public void t() {
	System.out.println('a');
	}*/
	
}
