import express from 'express';
import { connexionSQL } from './config/mysql.config';
import { setUtilisateurRouting } from './routes/utilisateurRoutes';

const app = express();
const port = 3001;

//Connexion BDD MySQL
connexionSQL.connect();

//Lancement serveur
app.listen(port, () => {
    console.log(`Connected | Port : ${port}`)
})

//Définition des routes
setUtilisateurRouting(app);

