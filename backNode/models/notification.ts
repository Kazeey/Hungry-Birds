class Notification
{
    public nom : String;
    public description : String;
    public state : Boolean;

    constructor(nom: String, description: String, state: Boolean)
    {
        this.nom = nom;
        this.description = description;
        this.state = state;
    }

    public create(): Boolean
    {
        return;
    }

    public delete(): Boolean
    {
        return;
    }

    public enable(): Boolean
    {
        return;
    }

    public disable(): Boolean
    {
        return;
    }

    public associate(): Boolean
    {
        return;
    }

    public dissociate(): Boolean
    {
        return;
    }
}

export = Notification;