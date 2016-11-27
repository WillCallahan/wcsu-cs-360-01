package edu.wcsu.cs360.battleship.client.service;

import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerConnectionHandlerService {
	
	private Log log = LogFactory.getLog(this.getClass());
	private String address;
	private Integer port;
	private List<ServerConnectionService<Request, Response>> serverConnectionServiceList;
	
	private ServerConnectionHandlerService() {
		this.serverConnectionServiceList = new ArrayList<>();
		this.address = null;
		this.port = null;
	}
	
	public ServerConnectionHandlerService(String address, int port) {
		this();
		this.address = address;
		this.port = port;
	}
	
	public void send(Request request) {
		try {
			ServerConnectionService<Request, Response> serverConnectionService = new ServerConnectionService<>(address, port, request);
			serverConnectionServiceList.add(serverConnectionService);
			serverConnectionService.start();
		} catch (IOException e) {
			throw new IllegalStateException("Unable to send request!", e);
		}
	}
	
}
