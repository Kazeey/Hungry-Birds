class commande 
{
    public id_commande : number;
    public id_utilisateur : number;
    public id_panier : number;
    public description : String;
    public date : number;
    public ancien_solde : number;
    public prix : Number;

    constructor(id_commande : number, id_panier: number, id_utilisateur: number, description: String, date: number, ancien_solde: number, prix:Number)
    {
        this.id_commande = id_commande
        this.id_panier = id_panier;
        this.id_utilisateur = id_utilisateur;
        this.description = description;
        this.date = date;
        this.ancien_solde = ancien_solde;
        this.prix = prix;

    }

    public create(): Boolean
    {
        return;
    }

    public update(): Boolean
    {
        return;
    }

    public delete(): Boolean
    {
        return;
    }
}

export = commande;