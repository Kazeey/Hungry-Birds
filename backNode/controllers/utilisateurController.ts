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
        connexionSQL.query(`SELECT * FROM utilisateur WHERE mail = '${req.body.mail}' OR telephone='${req.body.telephone}'`, (error, sqlResponseSelect) => {
            if(error)
                console.log("Error : ", error);
            else
            {
                let doc = [];
                if(sqlResponseSelect.length > 0)
                {
                    doc[0] = JSON.parse('{"response" : "L\'adresse mail et/ou le numéro de téléphone sont déjà utilisés."}');
                    res.status(200)
                    .send(doc[0])
                    .end();
                }
                else
                {
                    connexionSQL.query(`INSERT INTO utilisateur SET ?`, req.body, (error, sqlResponseInsert) => {
                    if (error) 
                    {
                        console.log("Error: ", error);
                    } 
                    else 
                    {
                        doc[0] = JSON.parse('{"result" : "Utilisateur créé."}');
                        res.status(201)
                        .send(doc[0])
                        .end();
                    }
                });
                }
            }           
        })
        
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