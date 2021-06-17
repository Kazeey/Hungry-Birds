import express from 'express';
import { connexionSQL } from './config/mysql.config';
import { setUtilisateurRouting } from './routes/utilisateurRoutes';

const app = express();
const port = 3001;

//Connexion BDD MySQL
connexionSQL.connect(error => {
    if(error) {
        throw error;
    } else {
        console.log("Connecté à la base de données Hungry Birds");
    }
});

//Lancement serveur
app.listen(port, () => {
    console.log(`Connected | Port : ${port}`)
})

//Définition des routes
setUtilisateurRouting(app);

