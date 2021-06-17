import { utilisateurController } from "../controllers/utilisateurController";

const bodyParser = require('body-parser')
const jsonParser = bodyParser.json()

export const setUtilisateurRouting = (app) => {

    const endpoint = 'utilisateurs'

    app.get(`/${endpoint}`, utilisateurController.findAll);
    // app.get(`/${endpoint}/:id`, userController.findById);
    // app.post(`/${endpoint}`, jsonParser, userController.create);
    // app.patch(`/${endpoint}/:id`, jsonParser, userController.update);
    // app.delete(`/${endpoint}/:id`, userController.delete);
}