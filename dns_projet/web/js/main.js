/* on declara une variable globale pour avoir accées partout */
let service;
let domaine, niveau;

window.onload = () => {
    
    /* on affecte a la variable le websocket */
    service = new WebSocket("ws://localhost:8025/Roman_Olivier/main");

    /* traitement du message envoyer par java */
    service.onmessage = (event) => {
        //ecoute des messages reçu du serveur.
        console.log("Message reçu de Java: " + event.data);
        msg = JSON.parse(event.data);
        
        /* recupération sous forme de tableau des clés du JSON. */
        let tab_keys = Object.keys(msg);
        
        /* on appele la fonction d'affichage du resultat */
        response(event.data, tab_keys, msg);
    };

    /* affichage de message que le serveur est allumé */
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
                    Response: "La page web est ouverte chez le client."
                })
            );
    };

     /* affichage de message que le serveur est etteint */
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

     /* affichage de message que le serveur rencontre une erreur(connection) */
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

/* 
 * Traitement de donnée entre dans le INPUT(search) verification et validation
 */
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
        // on recupere le niveau du domaine et domaine separer par un point en miniscule
        let sub = Domain_Name.toLowerCase().split(".");
        
        // on envoi à la console java les données découpés
        service.send(
            JSON.stringify(
                {
                    domaine: sub[0],
                    niveau: "." + sub[1]
                }
            )
        );
        
        // attribution des valeurs de niveau et domaine aux variables global pour etre utilise par le sweetalert
        domaine = sub[0];
        niveau = "." + sub[1];
    }
}

/* 
 * Fonction d'affichage du resultat dans une fenetre SweetAlert
 */
function response(event, tab, msg) {
    /* on recupere chaque clef avec ça valeur et on revient a la ligne */
    let resp = tab.map(x => x + ": " + msg[x]).join('<br>');
    
    /* si dans les valeur que l'on recupere une erreur est presente */
    let erreur = resp.includes("[_Erreur]:") ? -1 : 0;
    
    /* si il pas d'erreur */
    if(erreur != -1) {
        Swal.fire({
            position: 'center',
            icon: 'success',
            confirmButtonText: 'OK',
            title: `Resultat pour: ${domaine}${niveau}`,
            html: `${resp}`,
            footer: `<a href="http://${domaine}${niveau}">Acceder au site ${domaine}${niveau}</a>`
        });
    } else {
        /* on recupere juste la signification de l'erreur */
        resp = tab.map(x => msg[x]);
        
        /* on recupere la partie avant le [ */
        let sub = resp.toString().toUpperCase().split("[");
        
        Swal.fire({
            position: 'center',
            icon: 'warning',
            confirmButtonText: 'Réessayer',
            title: `Resultat pour: ${domaine}${niveau}`,
            html: `<i style="color:red;">${sub[0]}</i>`,
            footer: `Code Erreur: ${RecupereCodeErreur(sub[1])}`
        });
        
        // on efface le contenue dans search(on vide le champ)
        document.getElementById("search").value = "";
    }
}

/*
 * Fonction pour recupere le numero de code erreur
 */
function RecupereCodeErreur(sub) {
    
    /* 
     * on a besoin juste de recupere ne numero de l'erreur 
     */
    const Regex = /[0-9]/;
    return sub.match(Regex);
}

/* 
 * Fonction d'affichage des contacts dans une fenetre SweetAlert
 */
function Contact() {
    /* sweetalert */
    Swal.fire({
        title: 'Contacts des developpeurs',
        html: `r.badanin@etud.univ-pau.fr<br> o.baconnais@etud.univ-pau.fr`,
        position: 'center',
        confirmButtonText: 'OK',
        confirmButtonColor: '#131f36',
        showConfirmButton: true,
    });
}

/* 
 * Fonction d'affichage des contacts dans une fenetre SweetAlert
 */
function About() {
    /* sweetalert */
    Swal.fire({
        title: 'A Propos',
        icon: 'info',
        html: `Plus de 56 commits entre le debut de creation du projet <i>16 Oct</i> et <i>Dec 3, 2020 dernier commit.</i>.`,
        footer: `<a href="https://github.com/obaconnais/DNS_projet">GitHub Private Repositorie</a>`,
        position: 'center',
        confirmButtonText: 'OK',
        confirmButtonColor: '#131f36',
        showConfirmButton: true,
    });
}