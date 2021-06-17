import express from 'express';
import { connexionSQL } from './config/mysql.config';

const app = express();
const port = 3001;

connexionSQL.connect(error => {
    if(error) {
        throw error;
    } else {
        console.log("Connecté à la base de données Hungry Birds");
    }
});

app.listen(port, () => {
    console.log(`Connected | Port : ${port}`)
})

// connexionSQL.query('SELECT * from utilisateur', function(err, rows, fields) {
//     if (err) throw err;
//     console.log(rows);
// });

