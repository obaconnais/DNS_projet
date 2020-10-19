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
//		environnement.put(Context.PROVIDER_URL, "dns://194.167.156.13");
		environnement.put(Context.PROVIDER_URL, "dns://8.8.8.8");				
//		environnement.put(Context.PROVIDER_URL, "dns://193.146.78.14");

		//création d'un ensemble d'association.
		DirContext context = new InitialDirContext(environnement);
		NamingEnumeration ne = context.list("");
		
		//affichage de la list.
		while(ne.hasMore())
            System.out.println("\t" + ((NameClassPair) (ne.next())).getName());
		
		Attributes atr1 = context.getAttributes("www.google.com",new String[] {"A"});
		NamingEnumeration<? extends Attribute> e = atr1.getAll();
		
		//affichage des attributs.	
		while(e.hasMoreElements()) { 
		    Attribute a = e.next(); 
		    System.out.println(a.getID() + " = " + a.get()); 
		} 
        context.close();
	}
	
    static void printAttrs(Attributes attrs) {
	if (attrs == null) {
	    System.out.println("No attributes");
	} else {
	    /* Print each attribute */
	    try {
		for (NamingEnumeration ae = attrs.getAll();
		     ae.hasMore();) {
		    Attribute attr = (Attribute)ae.next();
		    System.out.println("attribute: " + attr.getID());

		    /* print each value */
		}
		    for (NamingEnumeration e = attrs.getAll();
			 e.hasMore();
			 System.out.println("value: " + e.next()))
			;
	    } catch (NamingException e) {
		e.printStackTrace();
	    }
	}
    }
 }
