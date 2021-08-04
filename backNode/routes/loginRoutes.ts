import { loginController } from "../controllers/loginController";

const bodyParser = require('body-parser')
const jsonParser = bodyParser.json()

export const setLoginRouting = (app) => {

    const endpoint = 'login'

    app.post(`/${endpoint}`, jsonParser, loginController.findUser);
}