package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleTest {

	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		ExecutorService es = Executors.newFixedThreadPool(10);
		SchTask task = new SchTask();
		Future<Object> future = es.submit(task);
		ScheduledExecutorService timerExector = Executors.newScheduledThreadPool(10);
		ScheduledFuture timeFuture = timerExector.schedule(new TimeListnerTask(future), 3, TimeUnit.SECONDS);
		try {
			System.out.println("-------------->任务耗时：" + (System.currentTimeMillis() - start));
			System.out.println(future.get());
			// System.out.println("-------------->任务耗时：" +
			// (System.currentTimeMillis() - start));
			es.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("-------------->任务耗时：" + (System.currentTimeMillis() - start));
		timeFuture.cancel(true);
		System.out.println("-------------->任务耗时：" + (System.currentTimeMillis() - start));
	}

}

class SchTask implements Callable<Object> {

	@Override
	public Object call() throws Exception {
		Long start = System.currentTimeMillis();
		Thread.sleep(5000);
		System.out.println("-------------->任务1耗时：" + (System.currentTimeMillis() - start));
		return "task1";
	}

}

class TimeListnerTask implements Runnable {

	Future future;

	public TimeListnerTask(Future future) {
		this.future = future;
	}

	@Override
	public void run() {
		future.cancel(true);
		System.out.println("。。。。。。。。。。。。超时结束线程");
	}

}
