import { panierController } from "../controllers/panierController";

const bodyParser = require('body-parser')
const jsonParser = bodyParser.json()

export const setpanierRouting = (app) => {

    const endpoint = 'paniers'

    app.get(`/${endpoint}`, panierController.findAll);
    app.get(`/${endpoint}/:id`, panierController.findById);
    app.post(`/${endpoint}`, jsonParser, panierController.create);
    app.patch(`/${endpoint}/:id`, jsonParser, panierController.update);
    app.delete(`/${endpoint}/:id`, panierController.delete);
}