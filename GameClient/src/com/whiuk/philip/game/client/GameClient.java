package com.whiuk.philip.game.client;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.whiuk.philip.game.shared.Messages.ClientMessage;
import com.whiuk.philip.game.shared.Messages.ServerMessage;

/**
 *
 * @author Philip Whitehouse
 *
 */
public class GameClient {
	/**
	 *
	 */
	private static final String HOST = "localhost";
	/**
	 *
	 */
	private static final int PORT = 8443;
	/**
	 * 
	 */
	private static GameClient gameClient;
	/**
	 *
	 */
	private final NetworkThread ntwThread;
	private volatile boolean connected;
	private volatile boolean running;
	private static final transient Logger LOGGER = Logger.getLogger(GameClient.class);

	/**
	 *
	 */
	public GameClient() {
    	//TODO: Initialize logging properly
    	BasicConfigurator.configure();
		ntwThread = new NetworkThread(HOST, PORT);
	}

	/**
	 * Run game client.
	 */
	public final void run() {
		ntwThread.start();
		running = true;
		while(running) {
			while(!connected) {
				
			}
			while(connected) {
				LOGGER.info("Sending message");
				ntwThread.sendOutboundMessage(ClientMessage.newBuilder()
						.setType(ClientMessage.Type.SYSTEM)
						.setSystemData(ClientMessage.SystemData.newBuilder()
								.setType(ClientMessage.SystemData.Type.CONNECTING)
								.build())
						.build());
			}
		}
	}
	/**
	 * Process message.
	 * @param message
	 */
	public void processInboundMessage(final ServerMessage message) {
		// TODO Auto-generated method stub

	}

	/**
	 * Singleton access.
	 * @return client
	 */
	public static GameClient getGameClient() {
		return gameClient;
	}
	/**
	 * Singleton setter.
	 * @param client
	 * @return
	 */
	public static void setGameClient(final GameClient client) {
		gameClient = client;
	}

	public void setConnected(boolean b) {
		connected = b;
	}

}