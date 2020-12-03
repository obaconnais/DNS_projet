package com.Roman_Olivier.jndi;

import javax.naming.*;
import javax.naming.directory.*;
import java.util.*;

public class Dns {
    private HashMap<String,String> Records = new HashMap<String,String>();
    
    public Dns(){};
    public Dns(String Name_, String Domaine_) {
        try {
            Properties _p = new Properties();
            _p.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
            InitialContext _ic = new InitialContext(_p);

            NameParser np = _ic.getNameParser(_ic.getNameInNamespace());
            Name name = np.parse("www." + Name_ + Domaine_);
            Object o = _ic.lookup(name.getPrefix(name.size() - 1));
            NamingEnumeration<?> _ne = (((DirContext) o).getAttributes(name, null)).getAll();
            while (_ne.hasMore()) {
                BasicAttribute ba = (BasicAttribute) _ne.next();
                GetRecords().put(ba.getID(),ba.get().toString());
            }
            _ic.close();

        } catch (NamingException _ne) {
            /* recuperation de l'erreur */
            GetRecords().put("[_Erreur]",_ne.getMessage());
            System.err.println(_ne.getMessage() + ": " + _ne.getExplanation());
        }
    }
    
    public void Print(){
        System.out.println(GetRecords());    
    }
    
    public HashMap<String,String> GetRecords(){
        return this.Records;
    }
    
    public void SetRecords(String s1,String s2){
        this.Records.put(s1,s2);
    }
}
