package thread.synchronize;

/**
 * @author xiexipeng
 * @version 创建时间：2017年7月9日 下午9:42:17
 * @description 创建一个死锁示例
 */
public class DeadLockTest {

	public static void main(String[] args) {
		Object o1 = new Object();
		Object o2 = new Object();
		Thread t1 = new Thread(new SyncTest(o1, o2), "t1");
		Thread t2 = new Thread(new SyncTest(o2, o1), "t2");
		t1.start();
		t2.start();
	}

}

class SyncTest implements Runnable {

	static Object o1;
	static Object o2;

	public SyncTest(Object o1, Object o2) {
		this.o1 = o1;
		this.o2 = o2;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		synchronized (o1) {
			System.out.println(name + "获取" + o1 + "对象锁");
			try {
				Thread.sleep(3000);
				synchronized (o2) {
					System.out.println(name + "获取" + o2 + "对象锁");
					Thread.sleep(3000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
