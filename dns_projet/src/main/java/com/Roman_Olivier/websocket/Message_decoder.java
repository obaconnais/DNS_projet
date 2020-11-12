package com.Roman_Olivier.websocket;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Set;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class Message_decoder implements Decoder.Text<JSDN_Message> {

    //declaration d'une map pour stocker les élements du JSON.
    private HashMap<String, String> map;

    public JSDN_Message decode(String s) throws DecodeException {
        JSDN_Message js_domain = null;
        Services_message SM=null;
        Set keys = map.keySet();

        if (willDecode(s)&&(keys.contains("domaine") && keys.contains("niveau"))) {
            js_domain = new JSDN_Message(map.get("domaine"), map.get("niveau"));
        }
        else
            SM = new Services_message(map.get("response"));

        return js_domain;
    }

    public boolean willDecode(String string) {
        //convert JSON to map.
        map = new HashMap<String, String>();
        JsonParser parser = Json.createParser(new StringReader(string));
        while (parser.hasNext()) {
            if (parser.next() == JsonParser.Event.KEY_NAME) {
                String key = parser.getString();
                parser.next();
                String value = parser.getString();
                map.put(key, value);
            }
        }
        Set keys = map.keySet();
        if ((keys.contains("domaine") && keys.contains("niveau"))||keys.contains("Response")) {
            return true;
        }

        return false;
    }

    public void init(EndpointConfig ec) {
        //null
    }

    public void destroy() {
        //null
    }
}
