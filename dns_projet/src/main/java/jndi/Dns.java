package jndi;

import javax.naming.*;
import javax.naming.directory.*;
import java.util.Hashtable;

public class Dns {
	public Dns() throws NamingException{
		//creation d'une Hashtable pour stocker les DNS.
		Hashtable<String,String> environnement = new Hashtable<String, String>();
		
		//Ajout des données dans la Hashtable.
		environnement.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.dns.DnsContextFactory");
		environnement.put(Context.PROVIDER_URL, "dns://194.167.156.13");
		environnement.put(Context.PROVIDER_URL, "dns://8.8.8.8");				
		
		//création d'un ensemble d'association.
		Context context = new InitialContext(environnement);
		
		//création d'une énumération de chaque association.
		NamingEnumeration ne = context.listBindings("");
        while (ne.hasMore()) {
            System.out.println("\t" + ((NameClassPair) (ne.next())).getName());
        }
		Object o = context.lookup("google");
		
	}

}
