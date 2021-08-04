import { connexionSQL } from '../config/mysql.config';

class LoginController {

    findUser = (req, res, next) => {
        connexionSQL.query(`SELECT * FROM utilisateur WHERE mail = '${req.body.mail}' AND password = '${req.body.password}'`, (error, sqlResponse) => {
            if (error) {
                console.log("Error: ", error);
            } else {
                res.status(200)
                .send(sqlResponse[0])
                .end();
            }
        });
    };
}

export const loginController = Object.freeze(new LoginController());