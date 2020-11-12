
package com.Roman_Olivier.websocket;

public final class JSDN_Message {
    private String Domain_Name;
    private String Domain;
    
    // constructeurs
    public JSDN_Message(){init(null,null);};
    
    public JSDN_Message(String DN, String D){init(DN,D);};
    
    // Geter et Seter de la classe.
    public String GetDomain_Name(){return this.Domain_Name;};
    public void SetDomain_Name(String DN){this.Domain_Name = DN;}
    public String GetDomain(){return this.Domain;};
    public void SetDomain(String D){ this.Domain =  D;};
    
    // fonction d'init
    public void init(String DN, String D){
       SetDomain(D);
       SetDomain_Name(DN);
    }
    
    //affichage du message.
    public void Print(){
        System.out.println("Domain_Name: " + GetDomain_Name() + "\n" + "Domain: " + GetDomain());
    }
}
