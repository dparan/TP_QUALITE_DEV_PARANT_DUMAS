package example;

public class Twelve{
	Thread thread = null;

	public Twelve(Runnable runnable) {
		thread = new Thread(runnable);
	}

	public void startThread(){
		thread.start();
	}
	
	//@SuppressWarnings("deprecation") @SuppressionWarnings est déprécié
	public synchronized void arret() {
			if (thread.isAlive()) {
				notifyAll();
				thread.interrupt();
			}
	}
		
}
