import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { IonicModule, IonicRouteStrategy } from '@ionic/angular';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UtilisateursComponent } from './pages/utilisateurs/utilisateurs.component';
import { StructuresComponent } from './pages/structures/structures.component';
import { UpdateUtilisateurModalComponent } from './components/utilisateurs/update-utilisateur-modal/update-utilisateur-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    UtilisateursComponent,
    StructuresComponent,
    UpdateUtilisateurModalComponent
  ],
  entryComponents: [],
  imports: [
    BrowserModule, 
    IonicModule.forRoot(), 
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [{ provide: RouteReuseStrategy, useClass: IonicRouteStrategy }],
  bootstrap: [AppComponent],
})
export class AppModule {}
