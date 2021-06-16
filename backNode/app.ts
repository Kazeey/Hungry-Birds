import Login from './models/login/index';
import Account from './models/account/index';
import Notification from './models/Notifications/index';

const express = require('express');
const app = express();
const port = 3001;

app.listen(port, () => {
    console.log(`Connected | Port : ${port}`)
    let test = new Login("Jean", "Pierre");
    test.connect();
})