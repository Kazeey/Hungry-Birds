import { commandeController } from "../controllers/commandeController";

const bodyParser = require('body-parser')
const jsonParser = bodyParser.json()

export const setcommandeRouting = (app) => {

    const endpoint = 'commandes'

    app.get(`/${endpoint}`, commandeController.findAll);
    app.get(`/${endpoint}/:id`, commandeController.findById);
    app.post(`/${endpoint}`, jsonParser, commandeController.create);
    app.patch(`/${endpoint}/:id`, jsonParser, commandeController.update);
    app.delete(`/${endpoint}/:id`, commandeController.delete);
}