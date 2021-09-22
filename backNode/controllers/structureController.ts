import { connexionSQL } from '../config/mysql.config';
import fetch from 'node-fetch';

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
        connexionSQL.query(`SELECT id_utilisateur FROM utilisateur WHERE mail = '${req.body.mail}'`, (error, sqlResponseSelect) => {
            if (error)
                console.log("Error : " + error);
            else
            {
                let doc = [];
                if(sqlResponseSelect.length < 1)
                {
                    doc[0] = JSON.parse('{"response" : "L\id utilisateur ne peut être trouvé."}');
                    res.status(200)
                    .send(doc[0])
                    .end();
                }
                else
                {
                    connexionSQL.query(`INSERT INTO structure (id_utilisateur, description, siret) VALUES (${sqlResponseSelect[0].id_utilisateur}, '${req.body.description}', '${req.body.siret}')`, req.body, (error, sqlResponse) => {
                        if (error) 
                        {
                            console.log("Error: ", error);
                        } 
                        else 
                        {
                            res.status(201)
                            .send("Structure créé avec succès.")
                            .end();
                        }
                    });
                }
            }
        })        
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