import express from 'express';
import { connexionSQL } from './config/mysql.config';
import { setUtilisateurRouting } from './routes/utilisateurRoutes';
import { setstructureRouting } from './routes/structureRoutes';
import { setpanierRouting } from './routes/panierRoutes';
import { setfavoriRouting } from './routes/favoriRoutes';
import { setcommandeRouting } from './routes/commandeRoutes';
import { setLoginRouting } from './routes/loginRoutes';

const app = express();
const port = 3001;

//Connexion BDD MySQL
connexionSQL.connect(error => {
    if(error) 
        throw error;
    else 
        console.log("Connecté à la base de données Hungry Birds");

});

//Lancement serveur
app.listen(port, () => {
    console.log(`Connected | Port : ${port}`)
})

//Définition des routes
setUtilisateurRouting(app);
setLoginRouting(app);
setstructureRouting(app);
setpanierRouting(app);
setfavoriRouting(app);
setcommandeRouting(app);

