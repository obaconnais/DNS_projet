package websocket;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.OnError;
import javax.websocket.Session;
//import javax.websocket.CloseReason.CloseCodes; (not used)
import javax.websocket.server.ServerEndpoint;
import javax.websocket.EndpointConfig;

public class Websocket {
	
	@ServerEndpoint(value = "/Websockets_serveur")
	public static class My_ServerEndpoint{
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
}
