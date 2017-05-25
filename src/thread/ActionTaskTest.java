package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ActionTaskTest {

	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		ActionTask task = new ActionTask();
		ExecutorService es = Executors.newFixedThreadPool(10);
		Future<Object> future1 = es.submit(task.createTask1());
		Future<Object> future2 = es.submit(task.createTask2());
		Future<Object> future3 = es.submit(task.createTask3());
		Future<Object> future4 = es.submit(task.createTask4(future1, future2, future3));
		try {
//			System.out.println(future4.get());
			System.out.println("-------------->任务返回结果：" + (String) future1.get(3, TimeUnit.SECONDS) + future2.get() + future3.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		es.shutdown();
		System.out.println("-------------->任务总耗时：" + (System.currentTimeMillis() - start));
	}

}
