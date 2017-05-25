package thread.exam;

import java.util.concurrent.atomic.AtomicInteger;

//����1
public class Sample2 {
	private static int count = 0;

	synchronized public static void increment() {
		count++;
	}
}

// ����2
class Sample3 {
	private static AtomicInteger count = new AtomicInteger(0);

	public static void increment() {
		count.getAndIncrement();
	}
}
