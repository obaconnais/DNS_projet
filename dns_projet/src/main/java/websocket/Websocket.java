package websocket;

import java.io.IOException;
import javax.websocket.*;

public class Websocket {
	 @javax.websocket.server.ServerEndpoint(value = "/WebSockets_illustration")
	    public static class My_ServerEndpoint {

	        @javax.websocket.OnClose
	        public void onClose(javax.websocket.Session session, javax.websocket.CloseReason close_reason) {
	            System.out.println("onClose: " + close_reason.getReasonPhrase());
	        }

	        @javax.websocket.OnError
	        public void onError(javax.websocket.Session session, Throwable throwable) {
	            System.out.println("onError: " + throwable.getMessage());
	        }

	        @javax.websocket.OnMessage
	        public void onMessage(javax.websocket.Session session, String message) {
	            System.out.println("Message from JavaScript: " + message);
	        }

	        @javax.websocket.OnOpen
	        public void onOpen(javax.websocket.Session session, javax.websocket.EndpointConfig ec) throws java.io.IOException {
	            System.out.println("OnOpen... " + ec.getUserProperties().get("Author"));
	            session.getBasicRemote().sendText("{Handshaking: \"Yes\"}");
	        }
	    }
    
}
