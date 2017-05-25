package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CountDownLatchDemo {

	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		ExecutorService es = Executors.newFixedThreadPool(10);
		CountDownLatch latch = new CountDownLatch(3);
		String result = "";
		Future[] future = new Future[3];
		for (int i = 0; i < 3; i++) {
			Task task = new Task(i + 1, latch);
			future[i] = es.submit(task.createCallable());

		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (Future fu : future) {
			try {
				result += (String) fu.get(9, TimeUnit.SECONDS);
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		es.shutdown();
		System.out.println("-------------->任务返回结果：" + result);
		System.out.println("-------------->任务总耗时：" + (System.currentTimeMillis() - start));
	}

}

class Task {
	int type;
	CountDownLatch latch;

	public Task(int type, CountDownLatch latch) {
		this.type = type;
		this.latch = latch;
	}

	public Callable<Object> createCallable() {
		return new Callable<Object>() {

			@Override
			public Object call() throws Exception {
				if (type == 1) {
					Long start = System.currentTimeMillis();
					Thread.sleep(5000);
					System.out.println("-------------->任务1耗时：" + (System.currentTimeMillis() - start));
					latch.countDown();
					return "task1";
				} else if (type == 2) {
					Long start = System.currentTimeMillis();
					Thread.sleep(8000);
					System.out.println("-------------->任务2耗时：" + (System.currentTimeMillis() - start));
					latch.countDown();
					return "task2";
				} else {
					Long start = System.currentTimeMillis();
					Thread.sleep(10000);
					System.out.println("-------------->任务3耗时：" + (System.currentTimeMillis() - start));
					latch.countDown();
					return "task3";
				}
			}
		};
	}
}
