import { connexionSQL } from '../config/mysql.config';

class FavoriController {

    findAll = (req, res, next) => {
        console.log("test");
        connexionSQL.query(`SELECT * FROM favoris`, (error, sqlResponse) => {
            if (error) 
                console.log("Error: ", error);
            else 
            {
                let doc = [];

                if(sqlResponse[0] == null)
                    doc[0] = "Il n'y a aucun favoris.";
                else
                    doc[0] = sqlResponse;

                res.status(200)
                .send(doc[0])
                .end();
            }
        });
    };

    findById = (req, res, next) => {
        connexionSQL.query(`SELECT * FROM favoris WHERE id_favori = ${req.params.id}`, (error, sqlResponse) => {
            if (error) 
                console.log("Error: ", error);
            else 
            {
                let doc = [];

                if(sqlResponse[0] == null)
                    doc[0] = "Il n'y a aucun favoris.";
                else
                    doc[0] = sqlResponse[0];

                res.status(200)
                .send(doc[0])
                .end();
            }
        });
    };

    create = (req, res, next) => {
        connexionSQL.query(`INSERT INTO favoris SET ?`, req.body, (error, sqlResponse) => {
            if (error) 
                console.log("Error: ", error);
            else 
            {
                res.status(201)
                .send("favori créé avec succès.")
                .end();
            }
        });
    };

    update = (req, res, next) => {
        connexionSQL.query(`UPDATE favoris SET ? WHERE id_favori = ${req.params.id_favori}`, req.params.id_utilisateur, (error, sqlResponse) => {
            if (error) 
                console.log("Error: ", error);
            else 
            {
                res.status(201)
                .send(`L'Le favori avec l'id ${req.params.id} a été mis ) jour.`)
                .end();
            }
        });
    };

    delete = (req, res, next) => {
        connexionSQL.query(`DELETE FROM favoris WHERE id_favori = ${req.params.id}`, (error, sqlResponse) => {
            if (error) 
            {
                console.log("Error: ", error);
            } 
            else 
            {
                res.status(201)
                .send(`L'favori avec l'id ${req.params.id} a été supprimé.`)
                .end();
            }
        });
    };

}

export const favoriController = Object.freeze(new FavoriController());