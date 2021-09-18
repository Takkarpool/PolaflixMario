import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../usuario-service.service';
import { SerieService } from '../serie-service.service';
import { Serie } from '../serie';
import { SerieEmpezada } from '../serieEmpezada';
import { Usuario } from '../usuario';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-visualizar-serie',
  templateUrl: './visualizar-serie.component.html',
  styleUrls: ['./visualizar-serie.component.css']
})
export class VisualizarSerieComponent implements OnInit {

  public usuario : any;
  private usuarioUrl: string = '';
  private idSerie: any;
  public pendiente: boolean = false;
  public serie: any;
  public temporada : any;
  public serieEmpezada : any;

  constructor(public usuarioService: UsuarioService, public serieService: SerieService) {
    this.idSerie = window.location.href.split("/")[4];
    this.usuario = {};
    this.serie = {};
    this.serieEmpezada = {};
  }

  ngOnInit() {
    this.serieService.findbyId(this.idSerie).subscribe(data => {
      this.serie = data;
    }, error => {console.log(error);
      alert("Error: " + error.status + "\n" + error.message)});
    var self = this;
    console.log(self.serie);
    this.usuario = this.usuarioService.usuario;
    this.usuario.repertorioUsuario.seriesPendientes.forEach( function(valor:Serie, indice: number, array: Array<Serie>) {
      if(valor.idSerie == self.idSerie){
        self.pendiente = true;
        self.temporada = 0;
      }
    });

    this.buscarSerie(self);

  }

  buscarSerie(self: any){
    this.usuario.repertorioUsuario.seriesEmpezadas.forEach( function(valor:SerieEmpezada, indice: number, array: Array<SerieEmpezada>) {
      if(valor.serie.idSerie == self.idSerie){
        self.pendiente = false;
        self.serieEmpezada = valor;
        self.temporada = self.serieEmpezada.ultimaTemporadaVista-1;
      }
    });

    this.usuario.repertorioUsuario.seriesFinalizadas.forEach(function(valor:SerieEmpezada, indice: number, array: Array<SerieEmpezada>) {
      if(valor.serie.idSerie == self.idSerie){
        self.pendiente = false;
        self.serieEmpezada = valor;
        self.temporada = self.serieEmpezada.ultimaTemporadaVista-1;
      }
    });

  }

  async verCapitulo(numCapitulo:number){
    var self = this;
    if(this.pendiente == true){
      self.usuario = await this.usuarioService.verCapitulo(this.usuario.nombre, this.serie.idSerie,
        this.serie.temporadas[this.temporada].numTemporada, numCapitulo).toPromise();
    }else{
      self.usuario = await this.usuarioService.verCapitulo(this.usuario.nombre, this.serie.idSerie,
        this.serie.temporadas[this.temporada].numTemporada, numCapitulo).toPromise();

    }
    this.buscarSerie(self);
  }

  ngOnDestroy() {
    this.usuarioService.usuario = this.usuario;
  }

  restaTemporada(){
    if(this.temporada>0){
      this.temporada -= 1;
    }
  }

  sumaTemporada(){
    if(this.serie.temporadas.length - 1 > this.temporada){
        this.temporada += 1;
    }
  }

}
