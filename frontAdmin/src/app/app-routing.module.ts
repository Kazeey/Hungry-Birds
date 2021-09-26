import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { StructuresComponent } from './pages/structures/structures.component';
import { UtilisateursComponent } from './pages/utilisateurs/utilisateurs.component';

const routes: Routes = [
  { path: "utilisateurs", component: UtilisateursComponent },
  { path: "structures", component: StructuresComponent },
  { path: '**', redirectTo: '/utilisateurs', pathMatch: 'full'}
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
