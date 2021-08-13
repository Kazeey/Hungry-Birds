import { connexionSQL } from '../config/mysql.config';

class UtilisateurController {

    findAll = (req, res, next) => {
        connexionSQL.query(`SELECT * FROM utilisateur`, (error, sqlResponse) => {
            if (error) 
            {
                console.log("Error: ", error);
            } 
            else 
            {
                res.status(200)
                .send(sqlResponse)
                .end();
            }
        });
    };

    findById = (req, res, next) => {
        connexionSQL.query(`SELECT * FROM utilisateur WHERE id_utilisateur = ${req.params.id}`, (error, sqlResponse) => {
            if (error) 
            {
                console.log("Error: ", error);
            } 
            else 
            {
                res.status(200)
                .send(sqlResponse)
                .end();
            }
        });
    };

    create = (req, res, next) => {
        connexionSQL.query(`INSERT INTO utilisateur SET ?`, req.body, (error, sqlResponse) => {
            if (error) 
            {
                console.log("Error: ", error);
            } 
            else 
            {
                res.status(201)
                .send("Utilisateur créé avec succès.")
                .end();
            }
        });
    };

    update = (req, res, next) => {
        connexionSQL.query(`DELETE FROM utilisateur WHERE id_utilisateur = ${req.params.id}`, (error, sqlResponse) => {
            if (error) 
            {
                console.log("Error: ", error);
            } 
            else 
            {
                res.status(201)
                .send(`L'utilisateur avec l'id ${req.params.id} a été supprimé.`)
                .end();
            }
        });
    };

    delete = (req, res, next) => {
        connexionSQL.query(``, (error, sqlResponse) => {
            if (error) 
            {
                console.log("Error: ", error);
            } 
            else 
            {
                res.status(201)
                .send(`L'utilisateur avec l'id ${req.params.id} a été supprimé.`)
                .end();
            }
        });
    };

}

export const utilisateurController = Object.freeze(new UtilisateurController());