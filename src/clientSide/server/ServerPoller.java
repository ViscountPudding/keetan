package clientSide.server;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is used to 
 * @author djoshuac
 */
public class ServerPoller {
	/**
	 * UpdateModelTask is an inner class that extends TimerTask so the ServerPoller
	 * can create a TimerTask that polls it's target server.
	 * @author djoshuac
	 */
	private class UpdateModelTask extends TimerTask {
		private int i = 1; //#TEMPORARY #UMT-COUNT
		
		/**
		 * This function tells the CLIENTSERVER to check for updates in the model.
		 * 
		 * BUT currently it just counts.
		 * I need the client server Interface (#WILL-TODO) to be implemented since the ServerPoller
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
	
	private static final int ONE_SECOND = 1000; //in milliseconds
	private static final int NO_DELAY = 0; //in milliseconds
	private Timer timer;
	private IServer targetServer;
	
	/**
	 * @pre targetServer must a functioning implementation of IServer and cannot be null.
	 * @param targetServer - the server the poller will target
	 * @return A ServerPoller with the given target server
	 * @post A ServerPoller with the given target server is created. 
	 */
	public ServerPoller(IServer targetServer) {
		timer = null;
		this.targetServer = targetServer;
	}
	
	/**
	 * Changes the ServerPoller's target server.
	 * @pre targetServer must not be null
	 * @param server - the server you want the poller to poll
	 * @post The server that is polled is changed to the given server. This works even
	 * if the poller is currently running.
	 */
	public void changeServer(IServer targetServer) {
		if (targetServer == null) {
			this.targetServer = targetServer;
		}
	}
	
	/**
	 * Delays, and then starts a timer to poll the target server at regular intervals.
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
	 * Starts a timer to poll the target server at regular intervals.
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
	 * Starts a timer to poll the target server at regular intervals.
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
	 * Stops polling the target server.
	 * @pre none
	 * @post Polling to the target server is stopped
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
		/*ServerPoller polls = new ServerPoller(new ISERVER()); #whybroken
		
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
		polls.stop();*/
	}
}



