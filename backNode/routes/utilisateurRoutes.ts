import { utilisateurController } from "../controllers/utilisateurController";

const bodyParser = require('body-parser')
const jsonParser = bodyParser.json()

export const setUtilisateurRouting = (app) => {

    const endpoint = 'utilisateurs'

    app.get(`/${endpoint}`, utilisateurController.findAll);
    app.get(`/${endpoint}/:id`, utilisateurController.findById);
    app.post(`/${endpoint}`, jsonParser, utilisateurController.create);
    app.patch(`/${endpoint}/:id`, jsonParser, utilisateurController.update);
    app.delete(`/${endpoint}/:id`, utilisateurController.delete);
}