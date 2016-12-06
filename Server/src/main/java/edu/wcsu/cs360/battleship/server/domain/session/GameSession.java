package edu.wcsu.cs360.battleship.server.domain.session;

import edu.wcsu.cs360.battleship.common.domain.trans.Player;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class GameSession {

	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private Player player;

	private GameSession() {

	}

	public GameSession(Socket socket, Player player) throws IOException {
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

	public Socket getSocket() {
		return socket;
	}
}
