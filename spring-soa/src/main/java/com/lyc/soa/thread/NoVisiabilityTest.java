package com.lyc.soa.thread;

public class NoVisiabilityTest {

	public static StringBuffer bbBuffer = new StringBuffer();

	private static class ReadThread extends Thread {

		private volatile boolean ready;

		private int number;

		public void run() {
			while (!ready) {
				number++;
				// Thread.yield();
				// System.out.println(number);
			}
			System.out.println(ready);
		}

		public void readyOn() {
			this.ready = true;
		}
	}

	private static class RThread extends Thread {

		public void run() {
			for (int i = 0; i < 1000; i++) {
				System.out.println("sss" + i);
				bbBuffer.append(i + "");
			}
			System.out.println(NoVisiabilityTest.bbBuffer);
		}

	}

	public static void main(String[] args) throws InterruptedException {
		RThread rThread = new RThread();
		rThread.start();
		RThread rThread1 = new RThread();
		RThread rThread2 = new RThread();
		rThread1.start();
		rThread2.start();
		
		rThread.join();
		rThread1.join();
		rThread2.join();
		System.out.println(NoVisiabilityTest.bbBuffer+"111");
	}
}
