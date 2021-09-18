import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SeriesUsuarioComponent } from './series-usuario/series-usuario.component';
import { AppComponent } from './app.component';
import { AgregarSerieComponent } from './agregar-serie/agregar-serie.component';
import { CargoUsuarioComponent } from './cargo-usuario/cargo-usuario.component';
import { VisualizarSerieComponent } from './visualizar-serie/visualizar-serie.component';

const routes: Routes = [
  { path: '', redirectTo: '/usuarios/aaa', pathMatch: 'full'},
  { path: 'usuarios/:nombreUsuario', component: SeriesUsuarioComponent},
  { path: 'agregarSerie', component: AgregarSerieComponent},
  { path: 'cargo', component: CargoUsuarioComponent},
  { path: 'verSerie/:idSerie', component: VisualizarSerieComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
