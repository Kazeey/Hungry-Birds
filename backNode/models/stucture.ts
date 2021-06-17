class structure 
{
    public id_structure : number;
    public id_utilisateur : number
    public description : String;
    public heure_debut : number;
    public heure_fin : number;
    public siret : string;

    constructor( id_structure: number, id_utilisateur: number, description: String, heure_debut: number, heure_fin: number, siret:string)
    {
        this.id_structure = id_structure;
        this.id_utilisateur = id_utilisateur;
        this.description = description;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.siret = siret;
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

export = structure;