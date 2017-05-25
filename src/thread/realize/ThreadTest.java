package thread.realize;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ThreadTest {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println("主线程开始执行...............");
		BasicThread1 bt1 = new BasicThread1();
		bt1.start();
		BasicThread2 bt2 = new BasicThread2();
		Thread t = new Thread(bt2);
		t.start();
		BasicThread3 bt3 = new BasicThread3();
		FutureTask<Object> task = new FutureTask<Object>(bt3);
		Thread t2 = new Thread(task);
		t2.start();
		ExecutorService es = Executors.newCachedThreadPool();
		BasicThread4 bt4 = new BasicThread4();
		Future<Object> future = es.submit(bt4);
		try {
			System.out.println("获取线程4返回结果：" + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		es.shutdown();
		System.out.println("主线程执行完毕，执行时间：" + (System.currentTimeMillis() - start));
	}

}

class BasicThread1 extends Thread {

	@Override
	public void run() {
		try {
			long start = System.currentTimeMillis();
			Thread.sleep(3000);
			System.out.println("线程1执行完毕,总耗时：" + (System.currentTimeMillis() - start));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class BasicThread2 implements Runnable {

	@Override
	public void run() {
		try {
			long start = System.currentTimeMillis();
			Thread.sleep(2000);
			System.out.println("线程2执行完毕,总耗时：" + (System.currentTimeMillis() - start));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class BasicThread3 implements Callable<Object> {

	@Override
	public Object call() throws Exception {
		try {
			long start = System.currentTimeMillis();
			Thread.sleep(5000);
			System.out.println("线程3执行完毕,总耗时：" + (System.currentTimeMillis() - start));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Thread3";
	}

}

class BasicThread4 implements Callable<Object> {

	@Override
	public Object call() throws Exception {
		try {
			long start = System.currentTimeMillis();
			Thread.sleep(1000);
			System.out.println("线程4执行完毕,总耗时：" + (System.currentTimeMillis() - start));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Thread4";
	}

}