package dns_projet;


import java.io.IOException;
import javax.naming.NamingException;

import org.glassfish.tyrus.server.Server;

import java.util.HashMap;

import jndi.Dns;
import websocket.Websocket; 
public class main {
	

	public static void main(String[] args) throws NamingException, IOException {
		Dns _google = new Dns("samsung", ".fr");
		HashMap<String, Object> user_properties = new HashMap<String, Object>();
		user_properties.put("co-Author1", "oBaconnais");
		user_properties.put("co-Author2", "rBadanin");

        Server server = new Server("localhost", 1963, "/oBaconnais ", user_properties /* or 'null' */, Websocket.class);
        try {
        	server.start();
            java.awt.Desktop.getDesktop().browse(java.nio.file.FileSystems.getDefault().getPath("web" + java.io.File.separatorChar + "index.html").toUri());
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
            System.out.println("Please press a key to stop the server...");
            reader.readLine();

        }catch(Exception e) {
        	e.printStackTrace();
        }
        finally {
        	server.stop();
        }
	}
}
