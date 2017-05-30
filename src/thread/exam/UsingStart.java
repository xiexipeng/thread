package thread.exam;

public class UsingStart {
	
	public static void main(String[] args) {
		ThreadTest test = new ThreadTest();
		Thread t = new Thread(test);
		t.start();
		t.start();
	}

}

class ThreadTest implements Runnable{

	@Override
	public void run() {
		System.out.println("线程1启动。。。。。。");
	}
	
}