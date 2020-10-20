package dns_projet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.naming.NamingException;

import org.glassfish.tyrus.server.Server;
import java.util.HashMap;

import jndi.Dns;
import websocket.Websocket.My_ServerEndpoint;; 
public class main {
	

	public static void main(String[] args) throws NamingException, IOException {
		Dns _google = new Dns("samsung", ".fr");
		HashMap<String, Object> user_properties = new HashMap<String, Object>();
		user_properties.put("co-Author1", "oBaconnais");
		user_properties.put("co-Author2", "rBadanin");

        Server server = new Server("localhost", 1963, "/oBaconnais", user_properties /* or 'null' */, My_ServerEndpoint.class);
        try {
        	server.start();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please press a key to stop the server.");
            reader.readLine();

        }catch(Exception e) {
        	System.out.println("error");
        	e.printStackTrace();
        }
        finally {
        	server.stop();
        }
	}
}
