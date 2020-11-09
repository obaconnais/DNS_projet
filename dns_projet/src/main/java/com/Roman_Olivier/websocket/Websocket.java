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

    @ServerEndpoint(value = "/websocket")
    public static class My_ServerEndpoint {

        @javax.websocket.OnMessage
        public void Message_traitement(String message) {
            System.out.println("Message recu par Websockets_serveur: " + message);
        }

        @javax.websocket.OnOpen
        public void onOpen(Session session, EndpointConfig ec) throws java.io.IOException {
            System.out.println("OnOpen... " + ec.getUserProperties().get("co-Author1"));
            session.getBasicRemote().sendText("{Handshaking: \"Yes\"}");
        }
        
        @javax.websocket.OnClose
        public void onClose(javax.websocket.Session session, javax.websocket.CloseReason close_reason) {
            System.out.println("onClose: " + close_reason.getReasonPhrase());
        }

        @javax.websocket.OnError
        public void onError(javax.websocket.Session session, Throwable throwable) {
            System.out.println("onError: " + throwable.getMessage());
        }
        
    }
}
