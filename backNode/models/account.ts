export class Account {
    public nom : String;
    public prenom : String;
    public mail : String;
    public password : String;
    public telephone : Number;
    public postalCode : Number;
    public adress : String;
    public ville : String; 
    public role : Number;

    constructor(nom: String, prenom: String, mail: String, password: String, telephone: Number, postalCode:Number, adress: String, ville: String, role: Number)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.telephone = telephone;
        this.postalCode = postalCode;
        this.adress = adress;
        this.ville = ville;
        this.role = role;
    }
}