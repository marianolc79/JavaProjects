package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ThreadTest {

	class MyThread implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Fin thread");
		}

	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future future = executorService.submit(new MyThread());
		executorService.shutdown();
		MyThread thread = new MyThread();
		thread.run();
	}

}
