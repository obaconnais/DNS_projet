/* on declara une variable globale pour avoir accées partout */
let service;
let domaine, niveau;

window.onload = () => {
    
    /* on affecte a la variable le websocket */
    service = new WebSocket("ws://localhost:8025/Roman_Olivier/main");

    /* traitement du message envoyer par java */
    service.onmessage = (event) => {
        //ecoute des messages reçu du serveur.
        console.log("Message from Java: " + event.data);
        msg = JSON.parse(event.data);
        //recupération sous forme de tableau des clés du JSON.
        let tab_keys = Object.keys(msg);
        response(event.data, tab_keys, msg);
    };

    service.onopen = () => {
        console.log("service.onopen...");
        Swal.fire({
            position: 'center',
            icon: 'success',
            title: `Server is open`,
            showConfirmButton: false,
            timer: 2500
        });
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
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: `Server is closed<br>Bye! See you later...`,
            showConfirmButton: false,
            timer: 2500
        });
    };

    service.onerror = () => {
        console.log("service.onerror...");
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: `Server is closed`,
            showConfirmButton: false,
            timer: 2500
        });
    };
};

//traitment of data in search-bar
function domain_search()
{
    let Domain_Name = document.getElementById("search").value; // recupération de la valeur du nom de domaine 

    /*
     * Selon la charte de nommage de l'AFNIC, un nom de domaine est codé en ASCII composé de caractère alphanumérique a-z (casse non importante
     * de chiffre de 0 à 9 et du tiret -.
     * le nom de domaine ne peut exceder 63 caractères.
     */

    const Regex = /^[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9]\.[a-zA-Z]{2,}$/;

    /* 
     * si le champ n'est pas vide
     * si le domaine avec le niveau ne correspond pas au regex 
     */

    if (!Regex.test(Domain_Name)) {

        /* sweetalert */
        Swal.fire({
            position: 'center',
            imageUrl: 'img/bonhomme-loupe.png',
            footer: 'Le domaine est vide ou invalide',
            background: '#ECE7E5',
            confirmButtonText: 'OK',
            confirmButtonColor: '#131f36',
            showConfirmButton: true,
            timer: 2500
        });

        // on efface le contenue dans search(on vide le champ)
        document.getElementById("search").value = "";
    }

    /* si le domaine avec le niveau sont accepté par regex */
    else
    {
        
        // on recupere le niveau du domaine et domaine separer par un point
        let sub = Domain_Name.split(".");
        // on envoi à la console java les données découpés
        service.send(
                JSON.stringify(
                        {
                            domaine: sub[0],
                            niveau: "." + sub[1]
                        })
                );
        domaine = sub[0];
        niveau = "." + sub[1];
    }
}

//function to display the Answer.
function response(event, tab, msg) {
    let resp = tab.map(x => x + ": " + msg[x]).join('<br>');
    Swal.fire({
            position: 'center',
            icon: 'succes',
            icon: 'info',
            confirmButtonText: 'OK',
            title: `Resultat pour: ${domaine}${niveau}`,
            html: `${resp}`,
            showConfirmButton: false,
    });
}

function Contact() {
    /* sweetalert */
    Swal.fire({
        title: 'contact(s) developpeur(s)',
        text: "r.badanin@univ-pau.fr \n test",
        position: 'center',
        background: '#ECE7E5',
        confirmButtonText: 'OK',
        confirmButtonColor: '#131f36',
        showConfirmButton: true,
    });
}