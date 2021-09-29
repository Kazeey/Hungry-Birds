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

    findByUserId = (req, res, next) => {
        connexionSQL.query(`SELECT * FROM structure WHERE id_utilisateur = ${req.params.id}`, (error, sqlResponse) => {
            if (error) 
            {
                console.log("Error: ", error);
            } 
            else
            {
                let doc = [];
                if(sqlResponse.length < 1)
                {
                    doc[0] = JSON.parse('{"response" : "Il n\'y a pas de structures pour cet utilisateur."}');
                    res.status(200)
                    .send(doc[0])
                    .end();
                }
                else
                {
                    doc[0] = sqlResponse;
                    res.status(200)
                    .send(doc[0])
                    .end();
                }
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
        connexionSQL.query(`SELECT * FROM structure WHERE siret = '${req.body.siret}'`, (error, sqlResponseSelectStructure) => {
            if(error)
                console.log("Error : " + error);
            else
            {
                let doc = [];
                if (sqlResponseSelectStructure.length > 0)
                {
                    doc[0] = JSON.parse('{"response" : "La strcture existe déjà."}');
                    res.status(200)
                    .send(doc[0])
                    .end();
                }
                else
                {
                    connexionSQL.query(`SELECT id_utilisateur FROM utilisateur WHERE mail = '${req.body.mail}'`, (error, sqlResponseSelect) => {
                    if (error)
                        console.log("Error : " + error);
                    else
                    {
                        if(sqlResponseSelect.length < 1)
                        {
                            doc[0] = JSON.parse('{"response" : "L\'id utilisateur ne peut être trouvé."}');
                            res.status(200)
                            .send(doc[0])
                            .end();
                        }
                        else
                        {
                            let query;
                            if(req.body.heure_debut && req.body.heure_fin)
                            {
                                query = `INSERT INTO structure (id_utilisateur, description, heure_debut, heure_fin, siret) VALUES (${sqlResponseSelect[0].id_utilisateur}, '${req.body.description}', '${req.body.heure_debut}', '${req.body.heure_fin}', '${req.body.siret}')`;
                            }
                            else
                            {
                                query = `INSERT INTO structure (id_utilisateur, description, siret) VALUES (${sqlResponseSelect[0].id_utilisateur}, '${req.body.description}', '${req.body.siret}')`;
                            }
                            connexionSQL.query(query, req.body, (error, sqlResponse) => {
                            if (error) 
                            {
                                console.log("Error: ", error);
                            } 
                            else 
                            {
                                doc[0] = JSON.parse('{"result" : "Structure créé."}');
                                res.status(201)
                                .send(doc[0])
                                .end();
                            }
                            });
                        }
                    }
                })        
                }
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

    createUserAndStructure = (req, res, next) => {
        let objectStructure, siret;
        let objectUser = req.body.objectUser;
        let mail = objectUser.mail;
        let phone = objectUser.telephone;

        if(req.body.objectStructure)
        {
            objectStructure = req.body.objectStructure;
            siret = objectStructure.siret;
        }

        connexionSQL.query(`SELECT * FROM utilisateur WHERE mail = '${mail}' OR telephone='${phone}'`, (error, sqlReponseSelectUser) => {
            if (error)
                console.log("Error : " + error);
            else
            {
                let doc = [];
                if (sqlReponseSelectUser.length > 0)
                {
                    doc[0] = JSON.parse('{"response" : "L\'adresse mail et/ou le numéro de téléphone sont déjà utilisés."}');
                    res.status(200)
                    .send(doc[0])
                    .end();
                }
                else
                {
                    connexionSQL.query(`SELECT * FROM structure WHERE siret = '${siret}'`, (error, sqlReponseSelectSiret) => {
                        if (error)
                            console.log("Error : " + error);
                        else
                        {
                            if (sqlReponseSelectSiret.length > 0)
                            {
                                doc[0] = JSON.parse('{"response" : "La strcture existe déjà."}');
                                res.status(200)
                                .send(doc[0])
                                .end();
                            }
                            else
                            {
                                connexionSQL.query(`INSERT INTO utilisateur SET ?`, objectUser, (error, sqlResponseInsert) => {
                                    if (error)
                                        console.log("Error : " + error);
                                    else
                                    {
                                        connexionSQL.query(`SELECT * FROM utilisateur WHERE mail = '${mail}'`, (error, sqlReponseSelectUser) => {
                                            if (error)
                                                console.log("Error : " + error);
                                            else
                                            {
                                                connexionSQL.query(`INSERT INTO structure (id_utilisateur, description, siret) VALUES (${objectUser.id_utilisateur}, '${objectStructure.description}', '${siret}')`, (error, sqlResponse) => {
                                                    if (error)
                                                        console.log("Error : " + error);
                                                    else
                                                    {
                                                        doc[0] = JSON.parse('{"result" : "Le compte utilisateur ainsi que la structure ont été créés."}');
                                                        res.status(200)
                                                        .send(doc[0])
                                                        .end();
                                                    }
                                                })
                                            }
                                        })
                                    }
                                })
                            }
                        }
                    })
                }
            }
        })
        
    }

}

export const structureController = Object.freeze(new StructureController());