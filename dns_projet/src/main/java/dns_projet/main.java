package dns_projet;


import java.io.IOException;
import javax.naming.NamingException;

import jndi.Dns;
import websocket.Websocket; 
public class main {

	public static void main(String[] args) throws NamingException {
		Dns _google = new Dns("samsung", ".fr");
		try{
			Websocket serveurWeb = new Websocket();
		} catch(IOException e) {
			System.out.println("error");
		}
	}
}
