import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { UpdateUtilisateurModalComponent } from 'src/app/components/utilisateurs/update-utilisateur-modal/update-utilisateur-modal.component';
import { Utilisateur } from 'src/app/models/utilisateur';
import { UtilisateurService } from 'src/app/services/utilisateur.service';

@Component({
  selector: 'app-utilisateurs',
  templateUrl: './utilisateurs.component.html',
  styleUrls: ['./utilisateurs.component.scss'],
})
export class UtilisateursComponent implements OnInit {

  utilisateurs : Utilisateur[];

  constructor(
    private service : UtilisateurService,
    public modalController : ModalController
    ) { }

  ngOnInit() {
    this.initUtilisateurs()
  }

  initUtilisateurs() {
    this.service.findAll().subscribe(data => {
      this.utilisateurs = data;
    })
  }

  delete(utilisateur : Utilisateur) {
    this.service.delete(utilisateur.id_utilisateur).subscribe(data => {
      this.initUtilisateurs()
    })
  }

  activate(utilisateur : Utilisateur) {
    this.service.activate(utilisateur.id_utilisateur).subscribe(data => {
      this.initUtilisateurs()
    })
  }

  async presentModal(utilisateur : Utilisateur) {
    const modal = await this.modalController.create({
      component: UpdateUtilisateurModalComponent,
      componentProps: {
        'utilisateur': utilisateur
      }
    });
    return await modal.present();
  }

}
