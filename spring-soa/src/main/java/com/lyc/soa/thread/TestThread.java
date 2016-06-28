package com.lyc.soa.thread;

public class TestThread {
static class A extends Thread {
		
		public void run() {
			while(true) {
				Thread.yield();
			}
		}
	}
	
	static class B extends Thread {
		
		A a = new A();
		
		public void run() {
			//a.setDaemon(true);
			a.start();
			System.out.println("B is end....");
		}
	}
	
	public static void main(String []args) throws InterruptedException {
		B b = new B();
		b.start();
		Thread.sleep(1000);
		System.out.println(b.a.getState());//输出RUNNING，说明还未结束
	}
}

class test extends Thread {
	public test() {
	}

	@Override	
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				System.out.println("111");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
	}
}
