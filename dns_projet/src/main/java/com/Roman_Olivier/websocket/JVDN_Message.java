package com.Roman_Olivier.websocket;

import java.util.HashMap;

public class JVDN_Message{
    private HashMap<String, String> map = new HashMap();
    
    public JVDN_Message(){_init(null);};
    
    public JVDN_Message(HashMap<String,String> map){
        _init(map);
    }
    
    public HashMap<String,String> GetMap(){
        return this.map;
    }
   
    private void _init(HashMap<String,String> map){
        this.map = map;
    }
    
}
