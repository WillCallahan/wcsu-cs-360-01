package edu.wcsu.cs360.battleship.client.service.io;

import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.domain.trans.Board;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GameConnectionHandlerService {
	
	private Log log = LogFactory.getLog(this.getClass());
	private String address;
	private Integer port;
	private GameConnectionWriterService<Request> gameConnectionWriterService;
	private GameConnectionReaderService<Response> gameConnectionReaderService;
	private IGameConnectionCallback<Response<Board>> gameConnectionCallback;
	
	private GameConnectionHandlerService() {
		
	}
	
	public GameConnectionHandlerService(String address, int port) {
		this();
	}
	
	public void send(Request request) {
		//TODO
	}
	
}
