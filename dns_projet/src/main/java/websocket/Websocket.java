package websocket;

import java.io.IOException;
import javax.websocket.*;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnOpen;
import javax.websocket.EndpointConfig;

@ServerEndpoint(value = "/Websockets_serveur")
public class Websocket {
	
	public Websocket() {}
	
	public Websocket(String message, Session session, EndpointConfig config, CloseReason close_reason, Throwable t) {
		this.Message_traitement(message);
		this.OnClose_traitement(session, close_reason);
		this.OnOpen_traitement(session, config);
		this.OnError(session, t);
	}
	
	
	@OnMessage
	public void Message_traitement(String message) {
		System.out.println("Message recu par Websockets_serveur: " + message);
	}
	
	@OnOpen
	public void OnOpen_traitement(Session session, EndpointConfig config){
		System.out.println("WebSocket ouverte :" + session.getId());
	}
	
	@OnClose
	public void OnClose_traitement(Session session, CloseReason close_reason) {
		System.out.println("Fermeture de la wabsocket : " + close_reason.getReasonPhrase());
	}
	
	@OnError
	public void OnError(Session session, Throwable t) {
		t.printStackTrace();
	}
	
}
