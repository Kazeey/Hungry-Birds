export class Notification {
    public nom : String;
    public description : String;
    public state : Boolean;

    constructor(nom: String, description: String, state: Boolean)
    {
        this.nom = nom;
        this.description = description;
        this.state = state;
    }
}