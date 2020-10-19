package jndi;

import javax.naming.*;
import javax.naming.directory.*;
import java.util.*;

public class Dns {
	public Dns(String Name_, String Domaine_) {
		
		try {
            Properties _p = new Properties();
            _p.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
            DirContext dc = new InitialDirContext(_p);
            Attributes attributes = dc.getAttributes(Name_ + Domaine_, new String[]{"NS", "A", "AAAA"});
            
            if (attributes != null) {
            	
            	NamingEnumeration<?> A = attributes.get("A").getAll();
            	NamingEnumeration<?> AAAA = attributes.get("AAAA").getAll();
                NamingEnumeration<?> NS = attributes.get("NS").getAll(); 
                
                while (NS.hasMoreElements()) {
                    System.out.println("[NS] entry: " + NS.next().toString());
                } 
                while (A.hasMoreElements()) {
                    System.out.println("[A] entry: " + A.next().toString());
                }  
                while (AAAA.hasMoreElements()) {
                    System.out.println("[AAAA] entry: " + AAAA.next().toString());
                }  
            }
            
            InitialContext _ic = new InitialContext(_p);
            NamingEnumeration<?> NS = _ic.list("");
            
            NameParser np = _ic.getNameParser(_ic.getNameInNamespace());
            Name university_of_Mondragon = np.parse("www." + Name_ + Domaine_);
            System.out.print("\nwww." + Name_ + Domaine_ + " " + university_of_Mondragon.size() + " components:");
            for (int i = 0; i < university_of_Mondragon.size(); i++) {
                System.out.print("\t" + university_of_Mondragon.get(i));
            }
            Object o = _ic.lookup(university_of_Mondragon.getPrefix(university_of_Mondragon.size() - 1));
            NS = (((DirContext) o).getAttributes(university_of_Mondragon, null)).getAll();
            while (NS.hasMore()) {
                BasicAttribute ba = (BasicAttribute) NS.next();
                System.out.print("\n\tAttribute id. [" + ba.getID() + "]: ");
                NamingEnumeration<?> NSe = ba.getAll();
                while (NSe.hasMore()) {
                    System.out.print("\t" + NSe.next());
                }
            }
            System.out.println("\n\nClosing...");
            _ic.close();
            
		} catch (NamingException NS) {
            System.err.println(NS.getMessage() + ": " + NS.getExplanation());
        }
	}
 }
