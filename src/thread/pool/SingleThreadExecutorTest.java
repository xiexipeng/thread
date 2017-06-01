package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorTest {

	public static void main(String[] args) {
		ExecutorService es = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			final int index = i + 1;
			es.execute(new Runnable() {
				public void run() {
					try {
						Thread.sleep(2000);
						System.out.println("线程" + index + "执行完毕");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		es.shutdown();
	}

}
