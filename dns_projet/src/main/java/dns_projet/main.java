package dns_projet;



import javax.naming.NamingException;

import jndi.Dns;

public class main {

	public static void main(String[] args) throws NamingException {
		Dns _google = new Dns("google", ".com");
	}
}
