import { connexionSQL } from '../config/mysql.config';

class UtilisateurController {

    findAll = () => {
        connexionSQL.query("SELECT * FROM utilisateur", (err, res) => {
            if (err) {
                console.log("Error: ", err);
            } else {
                console.log(res);
            }
        });
    };

}

export const utilisateurController = Object.freeze(new UtilisateurController());