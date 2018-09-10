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
	public void arret() {
		synchronized(thread)
		{
			if (thread.isAlive()) {
				notifyAll();
				thread.stop();
			}
		}
	}
		
}
