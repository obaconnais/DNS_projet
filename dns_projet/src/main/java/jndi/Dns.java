package jndi;

import javax.naming.*;
import javax.naming.directory.*;
import java.util.*;

public class Dns {
	public Dns(String Name_, String Domaine_) {
		
		try {
            Properties _p = new Properties();
            _p.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
            InitialContext _ic = new InitialContext(_p);
            
            NameParser np = _ic.getNameParser(_ic.getNameInNamespace());
            Name university_of_Mondragon = np.parse("www." + Name_ + Domaine_);
            System.out.print("\nwww." + Name_ + Domaine_ + " " + university_of_Mondragon.size() + " components:");
            for (int i = 0; i < university_of_Mondragon.size(); i++) {
                System.out.print("\t" + university_of_Mondragon.get(i));
            }
            Object o = _ic.lookup(university_of_Mondragon.getPrefix(university_of_Mondragon.size() - 1));
            NamingEnumeration<?> _ne = (((DirContext) o).getAttributes(university_of_Mondragon, null)).getAll();
            while (_ne.hasMore()) {
                BasicAttribute ba = (BasicAttribute) _ne.next();
                System.out.print("\n\tAttribute id. [" + ba.getID() + "]: ");
                NamingEnumeration<?> NSe = ba.getAll();
                while (NSe.hasMore()) {
                    System.out.print("\t" + NSe.next());
                }
            }
            System.out.println("\n\nClosing...");
            _ic.close();
            
		} catch (NamingException _ne) {
            System.err.println(_ne.getMessage() + ": " + _ne.getExplanation());
        }
	}
 }
