import { structureController } from "../controllers/structureController";

const bodyParser = require('body-parser')
const jsonParser = bodyParser.json()

export const setstructureRouting = (app) => {

    const endpoint = 'structures'

    app.get(`/${endpoint}`, structureController.findAll);
    app.get(`/${endpoint}/:id`, structureController.findById);
    app.get(`/${endpoint}/user/:id`, structureController.findByUserId);
    app.post(`/${endpoint}`, jsonParser, structureController.create);
    app.post(`/${endpoint}/userandstructure`, jsonParser, structureController.createUserAndStructure);
    app.patch(`/${endpoint}/:id`, jsonParser, structureController.update);
    app.delete(`/${endpoint}/:id`, structureController.delete);
}