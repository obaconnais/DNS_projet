'use strict';

window.onload = () => {
    
    let service = new WebSocket("ws://localhost:8025/Roman_Olivier/main");
    //traitement du message envoyer par java
    service.onmessage = (event) => {
        console.log("Message from Java: " + event.data);
        service.send(
                JSON.stringify(
                {
                    Response: "[JS]: La page Web est chargé chez le client."
                })
            );
    };
    
    service.onopen = () => {
        console.log("service.onopen...");
        let response = window.confirm(service.url + " just opened... Say 'Hi!'?");
        if (response)
            service.send(
                JSON.stringify(
                {
                    Response: "[JS]: La page Web est ouverte chez le client."
                })
            );
    };
    
    service.onclose = (event/*:CloseEvent*/) => {
        console.log("service.onclose... " + event.code);
        window.alert("Bye! See you later...");
    };
    
    service.onerror = () => {
        window.alert("service.onerror...");
        window.close;
    };
};