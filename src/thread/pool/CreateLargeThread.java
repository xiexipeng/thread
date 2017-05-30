package thread.pool;

public class CreateLargeThread {

	public static void main(String[] args) {
		Long num = (long) 0;
		for (int i = 0; i < 10; i++) {
			Long start = System.currentTimeMillis();
			ThreadTest test = new ThreadTest(i + 1);
			Thread t = new Thread(test);
			t.start();
			num += System.currentTimeMillis() - start;
		}
		System.out.println("线程执行时间：" + num);
	}

}

class ThreadTest implements Runnable {

	int i;

	public ThreadTest(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("线程" + i + "启动。。。。。。");
	}

}