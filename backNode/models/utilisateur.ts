export class Utilisateur {

    public id_utilisateur : number;
    public nom : String;
    public prenom : String;
    public mail : String;
    public password : String;
    public telephone : String;
    public adresse : String;
    public ville : String;
    public code_postal : number;
    public role : boolean;
    public statut : boolean;

    constructor(id_utilisateur : number, nom : String, prenom : String, mail : String, password : String, telephone : String, adresse : String, ville : String, code_postal : number, role : boolean, statut : boolean) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.telephone = telephone;
        this.adresse = adresse;
        this.ville = ville;
        this.code_postal = code_postal;
        this.role = role;
        this.statut = statut;
    }
}