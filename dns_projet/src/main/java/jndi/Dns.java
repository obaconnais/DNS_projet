package jndi;

import javax.naming.*;

import java.util.Hashtable;

public class Dns {
	private Hashtable<String,String> environnement = new Hashtable<String, String>();
	Dns() throws NamingException{
		environnement.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.dns.DnsContextFactory");
		environnement.put(Context.PROVIDER_URL,"dns://8.8.8.8");
		Context context = new InitialContext(environnement);
}
	public Hashtable<String, String> GetEnv() {
	return this.environnement;
	}
	
	public void SetEnv(String DNS, String URL) {
		this.environnement.put(DNS, URL);
	}
	
}
