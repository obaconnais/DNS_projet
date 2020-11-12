package com.Roman_Olivier.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.naming.NamingException;

import org.glassfish.tyrus.server.Server;
import java.util.HashMap;
import com.Roman_Olivier.jndi.Dns;
import com.Roman_Olivier.websocket.JSDN_Message;
import com.Roman_Olivier.websocket.JVDN_Message;
import com.Roman_Olivier.websocket.Message_decoder;
import com.Roman_Olivier.websocket.Message_encoder;

public class main {
    
        @javax.websocket.server.ServerEndpoint(value = "/main", decoders=Message_decoder.class,encoders=Message_encoder.class)
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
        public void onMessage(javax.websocket.Session session, JSDN_Message message) {
            System.out.println("Server has just received a message from JavaScript");
            Dns _google = new Dns(message.GetDomain_Name(), message.GetDomain());
            _google.Print();
        }

        @javax.websocket.OnOpen
        public void onOpen(javax.websocket.Session session, javax.websocket.EndpointConfig ec) throws java.io.IOException {
            System.out.println("OnOpen... " + ec.getUserProperties().get("Author"));
            session.getBasicRemote().sendText("{Handshaking: \"Yes\"}");
        }
    }
        
    public static void main(String[] args) throws NamingException, IOException {
      
        //permet de stocker les propriétés du serveur.
        HashMap<String, Object> user_properties = new HashMap<String, Object>();
        user_properties.put("Author", "Roman_Olivier");

        //instanciation du Websocket.... le port 0 utilise le port par defaut (TCP).
        Server server = new Server("localhost", 8025, "/Roman_Olivier", user_properties,My_ServerEndpoint.class);
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
