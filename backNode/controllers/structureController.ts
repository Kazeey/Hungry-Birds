import { connexionSQL } from '../config/mysql.config';

class StructureController {

    findAll = (req, res, next) => {
        connexionSQL.query(`SELECT * FROM structure`, (error, sqlResponse) => {
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
        connexionSQL.query(`SELECT * FROM structure WHERE id_structure = ${req.params.id}`, (error, sqlResponse) => {
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
        connexionSQL.query(`INSERT INTO structure SET ?`, req.body, (error, sqlResponse) => {
            if (error) 
            {
                console.log("Error: ", error);
            } 
            else 
            {
                res.status(201)
                .send("structure créé avec succès.")
                .end();
            }
        });
    };

    update = (req, res, next) => {
        connexionSQL.query(`DELETE FROM structure WHERE id_structure = ${req.params.id}`, (error, sqlResponse) => {
            if (error) 
            {
                console.log("Error: ", error);
            } 
            else 
            {
                res.status(201)
                .send(`L'structure avec l'id ${req.params.id} a été supprimé.`)
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
                .send(`L'structure avec l'id ${req.params.id} a été supprimé.`)
                .end();
            }
        });
    };

}

export const structureController = Object.freeze(new StructureController());