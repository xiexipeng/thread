package thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {

	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		ScheduledExecutorService es = Executors.newScheduledThreadPool(20);
		ScheduledThreadPoolTest test = new ScheduledThreadPoolTest();
		 test.scheduledTest(es, start);
//		test.fixTest(es);
	}

	// 延时执行任务
	public void scheduledTest(ScheduledExecutorService es, Long start) {
		for (int i = 0; i < 5; i++) {
			final int index = i + 1;
			es.schedule(new Runnable() {

				@Override
				public void run() {
					try {
						// 每个任务的执行时间
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程" + index + "执行完毕" + "," + "任务总耗时：" + (System.currentTimeMillis() - start));
				}
			}, 3, TimeUnit.SECONDS);// 延时执行任务的时间
		}
		es.shutdown();
	}

	// 执行延时周期任务
	public void fixTest(ScheduledExecutorService es) {
		es.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println("延时周期执行任务");
			}
		}, 1, 3, TimeUnit.SECONDS);
	}

}
