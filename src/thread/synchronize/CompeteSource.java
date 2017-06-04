package thread.synchronize;

public class CompeteSource {

	public static int index = 0;

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			final int j = i + 1;
			Thread t = new Thread() {

				public void run() {
					index++;
					Thread.yield();// 线程让步
					System.out.println("执行任务" + j + "," + "输出：" + index);
				}
			};
			t.start();
		}
	}

}
