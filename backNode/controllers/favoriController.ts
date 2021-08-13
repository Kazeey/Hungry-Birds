import { connexionSQL } from '../config/mysql.config';

class FavoriController {

    findAll = (req, res, next) => {
        connexionSQL.query(`SELECT * FROM favori`, (error, sqlResponse) => {
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
        connexionSQL.query(`SELECT * FROM favori WHERE id_favori = ${req.params.id}`, (error, sqlResponse) => {
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
        connexionSQL.query(`INSERT INTO favori SET ?`, req.body, (error, sqlResponse) => {
            if (error) 
            {
                console.log("Error: ", error);
            } 
            else 
            {
                res.status(201)
                .send("favori créé avec succès.")
                .end();
            }
        });
    };

    update = (req, res, next) => {
        connexionSQL.query(`DELETE FROM favori WHERE id_favori = ${req.params.id}`, (error, sqlResponse) => {
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

    delete = (req, res, next) => {
        connexionSQL.query(``, (error, sqlResponse) => {
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