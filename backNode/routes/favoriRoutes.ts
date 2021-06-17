import { favoriController } from "../controllers/favoriController";

const bodyParser = require('body-parser')
const jsonParser = bodyParser.json()

export const setfavoriRouting = (app) => {

    const endpoint = 'favoris'

    app.get(`/${endpoint}`, favoriController.findAll);
    app.get(`/${endpoint}/:id`, favoriController.findById);
    app.post(`/${endpoint}`, jsonParser, favoriController.create);
    app.patch(`/${endpoint}/:id`, jsonParser, favoriController.update);
    app.delete(`/${endpoint}/:id`, favoriController.delete);
}