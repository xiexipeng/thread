package thread.realize;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ThreadTest {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf.parse("");
		long start = System.currentTimeMillis();
		System.out.println("主线程开始执行...............");
		//多线程实现方式1
		BasicThread1 bt1 = new BasicThread1();
		bt1.start();
		//多线程实现方式2
		BasicThread2 bt2 = new BasicThread2();
		Thread t = new Thread(bt2);
		t.start();
		//多线程实现方式3
		BasicThread3 bt3 = new BasicThread3();
		FutureTask<Object> task = new FutureTask<Object>(bt3);
		Thread t2 = new Thread(task);
		t2.start();
		//多线程实现方式4
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

//继承Thread类实现多线程
class BasicThread1 extends Thread {

	@Override
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long start = System.currentTimeMillis();
			Thread.sleep(3000);
			System.out.println("线程1执行完毕,总耗时：" + (System.currentTimeMillis() - start));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

//实现Runnable接口实现多线程
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

//实现Callbale接口实现多线程
class BasicThread3 implements Callable<Object> {

	@Override
	public Object call() throws Exception {
		try {
			long start = System.currentTimeMillis();
			Thread.sleep(4000);
			System.out.println("线程3执行完毕,总耗时：" + (System.currentTimeMillis() - start));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Thread3";
	}

}

//实现Callable并通过线程池实现多线程
class BasicThread4 implements Callable<Object> {

	@Override
	public Object call() throws Exception {
		try {
			long start = System.currentTimeMillis();
			Thread.sleep(5000);
			System.out.println("线程4执行完毕,总耗时：" + (System.currentTimeMillis() - start));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Thread4";
	}

}