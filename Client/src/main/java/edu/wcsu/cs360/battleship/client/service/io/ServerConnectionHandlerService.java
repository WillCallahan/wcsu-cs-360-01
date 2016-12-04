package edu.wcsu.cs360.battleship.client.service.io;

import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.service.io.IConnectionSender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerConnectionHandlerService implements IConnectionSender<Request, Response> {
	
	private Log log = LogFactory.getLog(this.getClass());
	private String address;
	private Integer port;
	private static final ExecutorService executorService = Executors.newFixedThreadPool(100);
	
	private ServerConnectionHandlerService() {
		this.address = null;
		this.port = null;
	}
	
	public ServerConnectionHandlerService(String address, int port) {
		this();
		this.address = address;
		this.port = port;
	}
	
	@PreDestroy
	private void preDestroy() {
		log.info("Shutting down ExecutorService");
		executorService.shutdownNow();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> send(Request request) {
		try {
			ServerConnectionService<Request, Response> serverConnectionService = new ServerConnectionService<>(address, port, request, Request.class, Response.class);
			return executorService.submit(serverConnectionService);
		} catch (IOException e) {
			throw new IllegalStateException("Unable to send request!", e);
		}
	}
	
}
