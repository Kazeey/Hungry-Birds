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
                        console.log("Error: ", error);
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
        connexionSQL.query(`UPDATE utilisateur SET ? WHERE id_utilisateur='${req.body.id_utilisateur}'`, req.body, (error, sqlResponse) => {
            if (error) 
                console.log("Error: ", error);
            else 
            {
                res.status(201)
                .send(JSON.parse('{"result" : "Utilisateur modifié."}'))
                .end();
            }
        });
    };

    delete = (req, res, next) => {
        connexionSQL.query(`UPDATE utilisateur SET statut=0 WHERE (id_utilisateur = ${req.params.id})`, (error, sqlResponse) => {
            if (error) 
            {
                console.log("Error: ", error);
            } 
            else 
            {
                res.status(200)
                .send()
                .end();
            }
        });
    };

    activer = (req, res, next) => {
        connexionSQL.query(`UPDATE utilisateur SET statut=1 WHERE (id_utilisateur = ${req.params.id})`, (error, sqlResponse) => {
            if (error) 
            {
                console.log("Error: ", error);
            } 
            else 
            {
                res.status(200)
                .send()
                .end();
            }
        });
    };

}

export const utilisateurController = Object.freeze(new UtilisateurController());