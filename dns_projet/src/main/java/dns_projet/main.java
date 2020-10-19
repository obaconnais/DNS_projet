package dns_projet;


import java.io.IOException;
import javax.naming.NamingException;
import java.util.HashMap;

import jndi.Dns;
import websocket.Websocket; 
public class main {

	public static void main(String[] args) throws NamingException, IOException {
		Dns _google = new Dns("samsung", ".fr");
		HashMap<String, Object> user_properties = new HashMap<String, Object>();
		user_properties.put("co-Author1", "oBaconnais");
		user_properties.put("co-Author2", "rBadanin");

        org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost", 1963, "/oBaconnais ", user_properties /* or 'null' */, Websocket.class);
	}
}
