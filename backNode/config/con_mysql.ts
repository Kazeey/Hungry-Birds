const mysql = require('mysql');

// Configuration de la connexion à la base de données.
// Si jamais les requêtes SQL ne fonctionnent pas, voir du côté de flush privileges;
const connexionSQL = mysql.createConnection({
    host: "127.0.0.1",
    user: "root",
    password: "root",
    database: "hungrybirds"
});

export = connexionSQL;