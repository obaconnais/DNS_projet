package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.naming.NamingException;

import org.glassfish.tyrus.server.Server;
import java.util.HashMap;
import java.net.URL;
import jndi.Dns;

public class main {
	
    @javax.websocket.server.ServerEndpoint(value = "/main")
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
    
    public static void main(String[] args) throws NamingException, IOException {
        Dns _google = new Dns("samsung", ".fr");

        //permet de stocker les propriétés du serveur.
        HashMap<String, Object> user_properties = new HashMap<String, Object>();
        user_properties.put("Author", "oBaconnaisrBadanin");

        //instanciation du Websocket.... le port 0 utilise le port par defaut (TCP).
        Server server = new Server("localhost", 8025, "/oBaconnaisrBadanin", user_properties,My_ServerEndpoint.class);
        try{
            server.start();
            java.awt.Desktop.getDesktop().browse(java.nio.file.FileSystems.getDefault().getPath("web" + java.io.File.separatorChar + "index.html").toUri());
            //pour lire les données envoyé par le serveur.
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //permet de stopper le serveur.
            System.out.println("Please press a key to stop the server...");
            reader.readLine();
        }catch (Exception e){
           e.printStackTrace();
        }
        finally{
           server.stop();
        }
    }
}
