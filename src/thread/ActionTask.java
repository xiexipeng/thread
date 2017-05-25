package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class ActionTask {

	public Callable<Object> createTask1() {
		return new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				Long start = System.currentTimeMillis();
				Thread.sleep(5000);
				System.out.println("-------------->任务1耗时：" + (System.currentTimeMillis() - start));
				return "task1";
			}

		};
	}

	public Callable<Object> createTask2() {
		return new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				Long start = System.currentTimeMillis();
				Thread.sleep(8000);
				System.out.println("-------------->任务2耗时：" + (System.currentTimeMillis() - start));
				return "task2";
			}

		};
	}

	public Callable<Object> createTask3() {
		return new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				Long start = System.currentTimeMillis();
				Thread.sleep(10000);
				System.out.println("-------------->任务3耗时：" + (System.currentTimeMillis() - start));
				return "task3";
			}

		};
	}

	public Callable<Object> createTask4(Future<Object> future1, Future<Object> future2, Future<Object> future3) {
		return new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				return (String) future1.get() + future2.get() + future3.get();
			}

		};
	}

	// 递归实现方式
	public static int fibonacci(int n) {
		if (n <= 2) {
			return 1;
		} else {
			return fibonacci(n - 1) + fibonacci(n - 2);
		}
	}

	// 递推实现方式
	public static int fibonacciNormal(int n) {
		int n1 = 1, n2 = 1, sn = 0;
		for (int i = 0; i < n - 2; i++) {
			sn = n1 + n2;
			n1 = n2;
			n2 = sn;
		}
		return sn;
	}

	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		int sum = 0;
		// for (int i = 1; i <= 30; i++) {
		// sum += fibonacci(i);
		// }
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(sum);
		System.out.println(System.currentTimeMillis() - start);
	}

}
