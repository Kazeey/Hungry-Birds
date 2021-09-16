import { connexionSQL } from '../config/mysql.config';
import { transporter } from '../config/mail.config';

const nodemailer = require('nodemailer');
class LoginController {

    login = (req, res, next) => {
        connexionSQL.query(`SELECT * FROM utilisateur WHERE mail = '${req.body.mail}' AND password = '${req.body.password}'`, (error, sqlResponse) => {
            if (error)
                console.log("Error: ", error);
            else 
            {
                let doc = [];

                if(sqlResponse[0] == null)
                    doc[0] = JSON.parse('{"response" : "Il n\'y a pas d\'utilisateur qui corresponde à ces informations."}');
                else if (sqlResponse[0].statut == 0)
                    doc[0] = JSON.parse('{"blocked" : "Le compte utilisateur est bloqué."}');
                else
                    doc[0] = sqlResponse[0];
                
                res.status(200)
                .send(doc[0])
                .end();
            }
        });
    };

    changeStatus = (req, res, next) => {
        connexionSQL.query(`UPDATE utilisateur SET statut = '${req.body.statut}' WHERE mail = '${req.body.mail}'`, (error, sqlResponse) => {
            if (error)
                console.log("Error: ", error);
            else 
            {
                let doc = [];
                doc[0] = JSON.parse('{"response" : "La modification a été effectuée."}');
                
                res.status(200)
                .send(doc[0])
                .end();
            }
        });
    };

    forgetPassword = (req, res, next) => {
        connexionSQL.query(`SELECT * FROM utilisateur WHERE mail = '${req.body.mail}'`, (error, sqlResponse) => {
            if (error)
                console.log("Error : ", error);
            else
            {
                let doc = [];
                doc[0] = JSON.parse('{"response" : "S\'il exite un compte rattaché à cette adresse mail, le mot de passe a été modifié."}');

                if (sqlResponse[0] == null)
                {
                    res.status(200)
                    .send(doc[0])
                    .end();

                    return;
                }

                let tempPassword = Math.floor(Math.random() * 1000000) + 1000000;

                let mailOptions = {
                    from : 'projethungrybirds@yahoo.com',
                    to : req.body.mail,
                    subject : '[Hungry Birds] - Nouveau mot de passe',
                    html : "<p>Bonjour, veuillez trouver votre nouveau mot de passe temporaire : <b>" + tempPassword + "</b>. Nous vous invitons à le changer le plus rapidement possible.</p>"
                }; 

                transporter.sendMail(mailOptions, function(err, info) {
                    if (err)
                        console.log("erreur lors de l'envoi : ", err)
                    else
                    {
                        console.log("Mail envoyé : ", info.response);

                        connexionSQL.query(`UPDATE utilisateur SET password = '${tempPassword}' WHERE mail = '${req.body.mail}'`, (error, sqlResponse) => {
                            if (error)
                                console.log("Error : ", error);

                            res.status(200)
                            .send(doc[0])
                            .end();
                        });
                    }
                });
            }
        });
    };
}

export const loginController = Object.freeze(new LoginController());