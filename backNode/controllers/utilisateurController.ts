import mysql from "mysql";

class UtilisateurController {

    findAll = () => {
        mysql.query("SELECT * FROM utilisateur", (err, res) => {
            if (err) {
                console.log("Error: ", err);
            } else {
                console.log(res);
            }
        });
    };

}

export const utilisateurController = Object.freeze(new UtilisateurController());