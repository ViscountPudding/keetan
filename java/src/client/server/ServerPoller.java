package client.server;

import java.util.Timer;
import java.util.TimerTask;

import client.exceptions.ServerException;
import client.model.ModelFacade;
import client.model.TransferModel;

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
		/**
		 * This function gets the latest Model from the server and
		 * updates it in the ClientFacade.
		 * 
		 * @pre ServerPoller.clientServer can't be null
		 * @post clientServer is polled, ClientFacade is updated
		 * @throw NullPointerException - if ServerPoller.clientServer is null
		 */
		@Override
		public void run() {
			TransferModel model;
			try {
				model = targetServer.getModel(modelFacade.getModelVersion());
			}
			catch (ServerException e) {
				System.err.println(e.getReason());
				return;
			}
			modelFacade.updateModel(model);
		}
	}
	
	private static final int ONE_SECOND = 1000; //in milliseconds
	private static final int NO_DELAY = 0; //in milliseconds
	private Timer timer;
	private IServer targetServer;
	private ModelFacade modelFacade;
	
	/**
	 * @pre targetServer must a functioning implementation of IServer and cannot be null.
	 * @param targetServer - the server the poller will target
	 * @return A ServerPoller with the given target server
	 * @post A ServerPoller with the given target server is created. 
	 */
	public ServerPoller(IServer targetServer, ModelFacade modelFacade) {
		timer = null;
		this.targetServer = targetServer;
		this.modelFacade = modelFacade;
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
}



