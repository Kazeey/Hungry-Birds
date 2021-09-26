import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Utilisateur } from '../models/utilisateur';

const endpoint : string = "utilisateurs";

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {

  constructor(private http : HttpClient) { }

  findAll() {
    return this.http.get<Utilisateur[]>(`${environment.apiUrl}/${endpoint}`)
  }

  findById(id : number) {
    return this.http.get<Utilisateur>(`${environment.apiUrl}/${endpoint}/${id}`)
  }

  delete(id : number) {
    return this.http.delete<string>(`${environment.apiUrl}/${endpoint}/${id}`)
  }

  activate(id: number) {
    return this.http.delete<string>(`${environment.apiUrl}/${endpoint}/activate/${id}`)
  }
}
