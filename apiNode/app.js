const express = require('express');
const app = express();
const cors = require('cors');
const bodyParser = require('body-parser');

app.use(cors())

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({ extended: true }))

// Import des fonctions
const loginImport        = require('./functions/login/index.js');
const accountImport      = require('./functions/account/index.js');
const notificationImport = require('./functions/notifications/index.js');

// ------ Login ------ 
app.post('/api/login' , loginImport.data.login);  // login to an existing account

// ------ Account ------
app.get('/api/account/create'  , accountImport.data.create);    // If you want to create an account
app.post('/api/account/update' , accountImport.data.update);    // If you want to update informations
app.post('/api/account/delete' , accountImport.data.delete);    // If you want to delete an account

// ------ Notifications ------
app.post('/api/notification/submit' , notificationImport.data.submit); // Send notifications to user

app.listen(3001, function() {
    console.log("connected");
}); 
