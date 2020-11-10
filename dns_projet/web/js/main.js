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
        let response = window.confirm(service.url + " link with server is open");
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

function domain_search(event){
   let Domain_Name = document.getElementById("search").value; // recupération de la valeur du nom de domaine 
   //Selon la charte de nommage de l'AFNIC, un nom de domaine est codé en ASCII composé de caractère alphanumérique a-z (casse non importante
   //de chiffre de 0 à 9 et du tiret -. 
   //le nom de domaine ne peut exceder 63 caractères.
   let Regex = /^[A-Za-z0-9][0-9a-zA-Z-]{0,61}[A-Za-z0-9].[A-Za-z]+$/;
   if(!Regex.test(Domain_Name))
   { 
       Swal.fire({
           title:'le nom de domaine n\'est pas au bon format',
           backdrop: 'false',
            background: '#fff url(web/img/DNS_search_false.jpeg)',    
            icon: 'warning'
       })
   }
}
