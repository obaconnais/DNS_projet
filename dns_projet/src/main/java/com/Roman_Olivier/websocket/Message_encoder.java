package com.Roman_Olivier.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.json.JSONObject;


public class Message_encoder implements Encoder.Text<JVDN_Message> {

    public String encode(JVDN_Message t) throws EncodeException {
        JSONObject json = new JSONObject(t);
        System.out.println(json);
        return "json";
    }

    public void init(EndpointConfig ec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
