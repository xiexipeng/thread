package thread;

class AnonTest {

	public static void main(String[] args) {
		System.out.println("主线程开始执行...............");
		long start = System.currentTimeMillis();

		// 使用匿名类创建Thread对象，同时重载它的run()方法，实现多线程调用
		new Thread() {
			public void run() {
				try {
					long start = System.currentTimeMillis();
					Thread.sleep(3000);
					System.out.println("线程1执行完毕,总耗时：" + (System.currentTimeMillis() - start));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();

		// 使用匿名类创建Thread对象和Runnable对象，重写Runable对象的run()方法，同时将Runnable对象作为Thread对象构造方法的参数，实现多线程
		new Thread(new Runnable() {

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

		}).start();
		System.out.println("主线程执行完毕,总耗时：" + (System.currentTimeMillis() - start));
	}
}