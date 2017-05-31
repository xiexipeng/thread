package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CashThreadPoolTest {

	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			final int index = i + 1;
			es.submit(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(1000);
						System.out.println("线程" + index + "执行完毕");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			// 确保第二个任务执行的时候第一个任务已经执行完
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		es.shutdown();
	}

}
