import { loginController } from "../controllers/loginController";

const bodyParser = require('body-parser')
const jsonParser = bodyParser.json()

export const setLoginRouting = (app) => {

    const endpoint = 'login'

    app.post(`/${endpoint}/connect`, jsonParser, loginController.login);
    app.post(`/${endpoint}/changeStatusAccount`, jsonParser, loginController.changeStatus);
    app.post(`/${endpoint}/forgetPassword`, jsonParser, loginController.forgetPassword);
}