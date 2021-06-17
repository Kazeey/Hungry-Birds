class panier 
{
    public id_panier : number;
    public id_structure : number;
    public description : String;
    public date_debut : number;
    public date_fin : number;
    public prix : Number;
    public quantite : number;

    constructor(id_panier: number, id_structure: number, description: String, date_debut: number, date_fin: number, prix:Number, quantite: number)
    {
        this.id_panier = id_panier;
        this.id_structure = id_structure;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.quantite = quantite;
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

export = panier;