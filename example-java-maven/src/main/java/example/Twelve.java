package example;

public class Twelve extends Thread {
	Thread thread = null;

	public Twelve(Runnable runnable) {
		thread = new Thread(runnable);
		thread.start();
	}

	@SuppressWarnings("deprecation")
	public void arret() {
		if (thread.isAlive()) {
			notify();
			thread.stop();
		}
	}
}
