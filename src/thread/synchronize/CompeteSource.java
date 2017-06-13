package thread.synchronize;

public class CompeteSource {

	public static int index = 0;

	public static void main(String[] args) {
		CompeteSource cs = new CompeteSource();
		for (int i = 0; i < 5; i++) {
			final int j = i + 1;
			Thread t = new Thread() {

				// 竞争资源实例
				public void run() {
					index++;
					Thread.yield();// 线程让步
					System.out.println("竞争资源实例执行任务" + j + "," + "输出：" + index);
				}
			};
			t.start();
		}

		for (int i = 0; i < 5; i++) {
			final int j = i + 1;
			Thread t = new Thread() {

				// 对象锁测试
				public void run() {
					cs.confictTest(j);
					Thread.yield();// 线程让步
				}
			};
			t.start();
		}

		for (int i = 0; i < 5; i++) {
			final int j = i + 1;
			Thread t = new Thread() {

				// 对象锁测试
				public void run() {
					synchronized (this) {
						index++;
						Thread.yield();// 线程让步
						System.out.println("对象锁测试竞争资源实例执行任务" + j + "," + "输出：" + index);
					}
				}
			};
			t.start();
		}
	}

	// 修饰方法的对象锁
	public synchronized void confictTest(int j) {
		index++;
		System.out.println("对象锁测试执行任务" + j + "," + "输出：" + index);
	}

}
