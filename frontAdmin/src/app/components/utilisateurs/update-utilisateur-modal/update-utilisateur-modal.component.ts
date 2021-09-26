import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ModalController } from '@ionic/angular';
import { Utilisateur } from 'src/app/models/utilisateur';

@Component({
  selector: 'app-update-utilisateur-modal',
  templateUrl: './update-utilisateur-modal.component.html',
  styleUrls: ['./update-utilisateur-modal.component.scss'],
})
export class UpdateUtilisateurModalComponent implements OnInit {

  userForm : FormGroup;
  @Input() utilisateur : Utilisateur;

  constructor(
    public modalController: ModalController,
    private fb : FormBuilder
    ) { 
      this.userForm = this.fb.group({
        nom : "",
        prenom : "",
        mail : "",
        telephone : "",
        adresse : "",
        ville : "",
        code_postal : "",
      })
  }

  ngOnInit() {
    this.userForm.patchValue(this.utilisateur)
  }

  dismiss() {
    this.modalController.dismiss({
      'dismissed': true
    });
  }

  update() {
    console.log(this.userForm.value)
  }

}
