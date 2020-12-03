
package com.Roman_Olivier.websocket;

public final class Services_message {
    private String Service_message;
    
    public Services_message(){
        SetSM(null);
    };
    public Services_message(String SM){
        SetSM(SM);
    }
    
    protected void SetSM(String SM){
        this.Service_message = SM;
    }
    
    public String GetSM(){
        return this.Service_message;
    }
    
}
