import { Component, OnInit } from '@angular/core';
import { Structure } from 'src/app/models/stucture';
import { StructureService } from 'src/app/services/structure.service';
import { UtilisateurService } from 'src/app/services/utilisateur.service';

@Component({
  selector: 'app-structures',
  templateUrl: './structures.component.html',
  styleUrls: ['./structures.component.scss'],
})
export class StructuresComponent implements OnInit {

  structures : Structure[] = []

  constructor(
    private service : StructureService,
    private utilisateurService : UtilisateurService
    ) { }

  ngOnInit() {
    this.initStructures()
  }

  initStructures() {
    this.service.findAll().subscribe(data => {
      this.structures = data;
      for(let structure of this.structures) {
        this.utilisateurService.findById(structure.id_utilisateur).subscribe(data =>{
        structure.utilisateur = data
      })
    }
    })
  }

  update(s : Structure) {
    
  }

  delete(s : Structure) {
    this.utilisateurService.delete(s.utilisateur[0].id_utilisateur).subscribe(() => {
      this.initStructures()
    })
  }

  activate(s : Structure) {
    this.utilisateurService.activate(s.utilisateur[0].id_utilisateur).subscribe(data => {
      this.initStructures()
    })
  }

}
