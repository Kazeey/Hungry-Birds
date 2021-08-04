import { connexionSQL } from '../config/mysql.config';

class CommandeController {

    findAll = (req, res, next) => {
        connexionSQL.query(`SELECT * FROM commande`, (error, sqlResponse) => {
            if (error) {
                console.log("Error: ", error);
            } else {
                res.status(200)
                .send(sqlResponse)
                .end();
            }
        });
    };

    findById = (req, res, next) => {
        connexionSQL.query(`SELECT * FROM commande WHERE id_commande = ${req.params.id}`, (error, sqlResponse) => {
            if (error) {
                console.log("Error: ", error);
            } else {
                res.status(200)
                .send(sqlResponse)
                .end();
            }
        });
    };

    create = (req, res, next) => {
        connexionSQL.query(`INSERT INTO commande SET ?`, req.body, (error, sqlResponse) => {
            if (error) {
                console.log("Error: ", error);
            } else {
                res.status(201)
                .send("commande créée avec succès.")
                .end();
            }
        });
    };

    update = (req, res, next) => {
        connexionSQL.query(`DELETE FROM commande WHERE id_commande = ${req.params.id}`, (error, sqlResponse) => {
            if (error) {
                console.log("Error: ", error);
            } else {
                res.status(201)
                .send(`L'commande avec l'id ${req.params.id} a été supprimé.`)
                .end();
            }
        });
    };

    delete = (req, res, next) => {
        connexionSQL.query(``, (error, sqlResponse) => {
            if (error) {
                console.log("Error: ", error);
            } else {
                res.status(201)
                .send(`L'commande avec l'id ${req.params.id} a été supprimé.`)
                .end();
            }
        });
    };

}

export const commandeController = Object.freeze(new CommandeController());