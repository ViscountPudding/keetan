package catana;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author djoshuac
 * 
 * This class tells a CLIENTSERVER to update the client model at a regular interval.
 */
public class ServerPoller {
	private class UpdateModelTask extends TimerTask {
		private int i = 1; //#TEMPORARY #UMT-COUNT
		
		/**
		 * This function tells the CLIENTSERVER to check for updates in the model.
		 * 
		 * BUT currently it just counts.
		 * I need the client server (#WILL-TODO) to be implemented since the ServerPoller
		 * will require a pointer to invoke it's 'UPDATEMODEL()' method #UMT-RUN 
		 * 
		 * @pre ServerPoller.clientServer can't be null
		 * @post clientServer is polled
		 * @throw NullPointerException - if ServerPoller.clientServer is null
		 */
		@Override
		public void run() {
			System.out.println(i++); //#TEMPORARY #UMT-COUNT 
		}
	}
	/*
	 * Notes for Timer:
	 * A Timer creates a thread that executes a TimerTask's 'run()' method on some time based system.
	 * 
	 * TimerTask.run() should execute quickly to prevent bunch up of tasks. AKA faster than the period.
	 * 
	 * NOTE: After a Timer is 'cancel()'ed it us basically unusable so call 'purge()' on it.
	 * 		 The TimerTask it was using is also unusable, so a new instance is needed each time.
	 */
	private Timer timer;
	private static final int ONE_SECOND = 1000; //in milliseconds
	private static final int NO_DELAY = 0; //in milliseconds
	//private CLIENTSERVER clientServer
	
	/**
	 * @pre cleintServer must be a non-null CLIENTSERVER
	 * @param clientServer - a functioning CLIENTSERVER
	 * @return A fully functional ServerPoller
	 * @post A fully functional ServerPoller is created
	 */
	public ServerPoller(/*CLIENTSERVER clientServer*/) {
		timer = null;
		//this.clientServer = clientServer;
	}
	
	/**
	 * Delays, and then starts a timer to poll the server at regular intervals.
	 * @pre delay must nonnegative, period must be positive, System.currentTimeMillis()
	 * must be nonnegative, and the clock used for Object.wait() must be accurate
	 * @param delay - delay in milliseconds before the polling of the server should begin
	 * @param period - delay in milliseconds between each poll to the server
	 * @post After the delay, the CLIENTSERVER will be polled periodically. This will
	 * override any previous calls to start
	 * @throw IllegalArgumentException - if delay is less than zero, period is less than one,
	 * or delay + System.currentTimeMillis() is less than zero
	 */
	public void start(long delay, long period) {
		if (timer == null) {
			timer = new Timer();
			timer.schedule(new UpdateModelTask(), delay, period);
		}
		else {
			timer.cancel();
			timer.purge();
			timer = null;
			start(delay, period);
		}
	}
	/**
	 * Starts a timer to poll the server at regular intervals.
	 * @pre period must be positive, System.currentTimeMillis() must be nonnegative,
	 * and the clock used for Object.wait() must be accurate
	 * @param period - delay in milliseconds between each poll to the server
	 * @post After the delay, the CLIENTSERVER will be polled at the given period. This will
	 * override any previous calls to start
	 * @throw IllegalArgumentException - if period is less than one, or 
	 * System.currentTimeMillis() is less than zero
	 */
	public void start(long period) {
		start(NO_DELAY, period);
	}
	/**
	 * Starts a timer to poll the server at regular intervals.
	 * @pre System.currentTimeMillis() must be nonnegative, and the clock used for
	 * Object.wait() must be accurate
	 * @post The CLIENTSERVER will be polled every second. This will override any
	 * previous calls to start
	 * @throw IllegalArgumentException - if period is less than one, or 
	 * System.currentTimeMillis() is less than zero
	 */
	public void start() {
		start(NO_DELAY, ONE_SECOND);
	}
	
	/**
	 * Stops polling the server.
	 * @pre none
	 * @post Polling to the CLIENTSERVER is stopped
	 */
	public void stop() {
		if (timer != null) {
			timer.cancel();
			timer.purge();
			timer = null;
		}
	}
	
	
	/**
	 * You can use this 'main()' to see how the poller will work.
	 * All I need to finish this class is to implement the 
	 * 'run()' method of the UpdateModelTask class. #UMT-RUN
	 * 
	 * @param args - we do not use this here
	 */
	public static void main(String[] args) {
		ServerPoller polls = new ServerPoller();
		
		System.out.println("START");
		polls.start(500, 1000);
		
		try {
			Thread.sleep(5000); //pause this thread 5 seconds, the poller runs on it's own thread.
		}
		catch (InterruptedException e) {
			System.out.println("ERROR: The thread was interrupted.");
			return;
		}
		
		System.out.println("END");
		polls.stop();
	}
}



