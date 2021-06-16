import connexionSQL from '../../config/con_mysql'

class Login 
{
    public login    : String;
    public password : String;

    constructor(login: String, password: String)
    {
        this.login = login;
        this.password = password;
    }

    public connect(): boolean
    {
        console.log(connexionSQL);
        return true;
    }

    public forgotPassword(): String
    {
        return;
    }
}

export = Login;