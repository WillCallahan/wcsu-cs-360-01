package edu.wcsu.cs360.battleship.server.domain.session;

import edu.wcsu.cs360.battleship.common.domain.trans.Player;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Random;

/**
 * Groups a {@link Socket} with a {@link Player}
 */
public class PlayerSession {

	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private Player player;
	private final long sessionId;

	{
		Random random = new Random((new Date()).getTime());
		sessionId = random.nextLong();
	}
	
	private PlayerSession() {
		
	}

	public PlayerSession(Socket socket, Player player) throws IOException {
		this();
		this.socket = socket;
		this.inputStream = socket.getInputStream();
		this.outputStream = socket.getOutputStream();
		this.player = player;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	public Socket getSocket() {
		return socket;
	}
	
	public long getSessionId() {
		return sessionId;
	}
}
