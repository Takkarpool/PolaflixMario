import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SeriesUsuarioComponent } from './series-usuario/series-usuario.component';
import { SerieService } from './serie-service.service';
import { UsuarioService } from './usuario-service.service';
import { AgregarSerieComponent } from './agregar-serie/agregar-serie.component';
import { CargoUsuarioComponent } from './cargo-usuario/cargo-usuario.component';
import { VisualizarSerieComponent } from './visualizar-serie/visualizar-serie.component';
import { LOCALE_ID } from '@angular/core';
import { registerLocaleData } from '@angular/common';


import localePy from '@angular/common/locales/es-PY';
import localePt from '@angular/common/locales/pt';
import localeEn from '@angular/common/locales/en';
import localeEsAr from '@angular/common/locales/es-AR';

// registrar los locales con el nombre que quieras utilizar a la hora de proveer
registerLocaleData(localePy, 'es');
registerLocaleData(localePt, 'pt');
registerLocaleData(localeEn, 'en')
registerLocaleData(localeEsAr, 'es-Ar');


@NgModule({
  declarations: [
    AppComponent,
    SeriesUsuarioComponent,
    AgregarSerieComponent,
    CargoUsuarioComponent,
    VisualizarSerieComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [SerieService,
              UsuarioService,
            { provide: LOCALE_ID, useValue: "es-Ar" }],
  bootstrap: [AppComponent]
})
export class AppModule { }
