import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Structure } from '../models/stucture';

const endpoint = "structures"

@Injectable({
  providedIn: 'root'
})
export class StructureService {

  constructor(private http : HttpClient) { }

  findAll() {
    return this.http.get<Structure[]>(`${environment.apiUrl}/${endpoint}`)
  }

  findById(id : number) {
    return this.http.get<Structure>(`${environment.apiUrl}/${endpoint}/${id}`)
  }

  delete(id : number) {
    return this.http.delete<string>(`${environment.apiUrl}/${endpoint}/${id}`)
  }
}
