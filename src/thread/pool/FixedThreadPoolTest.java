package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {

	public static void main(String[] args) {
		Long start1 = System.currentTimeMillis();
		ExecutorService es = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			final int index = i + 1;
			es.submit(new Runnable() {

				@Override
				public void run() {
					try {
						Long start = System.currentTimeMillis();
						Thread.sleep(3000);
						System.out.println("线程" + index + "执行完毕,耗时：" + (System.currentTimeMillis() - start) + "...."
								+ "与主线程对比，耗时：" + (System.currentTimeMillis() - start1));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		es.shutdown();
		System.out.println("总线程执行完毕，总耗时：" + (System.currentTimeMillis() - start1));
	}

}
