package thread.synchronize;

public class SychronizedTest {

	public void syctest() {
		for (int i = 0; i < 3; i++) {
			System.out.println("线程" + Thread.currentThread().getName() + "正在执行");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void sycIntest() {
		for (int i = 0; i < 3; i++) {
			System.out.println("线程" + Thread.currentThread().getName() + "正在执行");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void sycIntest2() {
		for (int i = 0; i < 3; i++) {
			System.out.println("线程" + Thread.currentThread().getName() + "正在执行");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void syctest2() {
		synchronized (this) {
			for (int i = 0; i < 3; i++) {
				System.out.println("线程" + Thread.currentThread().getName() + "正在执行");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void syctest3() {
		synchronized (SychronizedTest.class) {// 类锁。注意，这表示该类所有对象实例同时只能有一个访问该代码块。
			for (int i = 0; i < 3; i++) {
				System.out.println("线程" + Thread.currentThread().getName() + "正在执行");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void runTest1() {
		Thread t1 = new Thread("任务1") {
			public void run() {
				sycIntest();
			}
		};
		Thread t2 = new Thread("任务2") {
			public void run() {
				sycIntest();
			}
		};
		t1.start();
		t2.start();
	}

	public void runTest2() {
		Thread t1 = new Thread("任务1") {
			public void run() {
				sycIntest();
			}
		};
		Thread t2 = new Thread("任务2") {
			public void run() {
				syctest();
			}
		};
		t1.start();
		t2.start();
	}

	public void runTest3() {
		Thread t1 = new Thread("任务1") {
			public void run() {
				sycIntest();
			}
		};
		Thread t2 = new Thread("任务2") {
			public void run() {
				sycIntest2();
			}
		};
		t1.start();
		t2.start();
	}

	public void runTest4() {
		Thread t1 = new Thread("任务1") {
			public void run() {
				sycIntest();
			}
		};
		Thread t2 = new Thread("任务2") {
			public void run() {
				syctest2();
			}
		};
		t1.start();
		t2.start();
	}

	public void runTest5() {
		SychronizedTest st1 = new SychronizedTest();
		SychronizedTest st2 = new SychronizedTest();
		Thread t1 = new Thread("任务1") {
			public void run() {
				st1.syctest3();
			}
		};
		Thread t2 = new Thread("任务2") {
			public void run() {
				st2.syctest3();
			}
		};
		t1.start();
		t2.start();
	}

	public static void main(String[] args) {
		SychronizedTest st = new SychronizedTest();
		// 不同线程对同一对象的同一个同步方法的访问
		// st.runTest1();
		// 不同线程对同一对象不同方法的访问，其中一个同步，一个非同步
		// st.runTest2();
		// 不同线程对同一兑现的两个不同的同步方法的访问
		// st.runTest3();
		// 不同线程对同一对象的一个同步方法和同步代码块的访问
		// st.runTest4();
		// 不同线程对同一对象的一个同步方法和同步代码块的访问
		st.runTest5();
	}

}
