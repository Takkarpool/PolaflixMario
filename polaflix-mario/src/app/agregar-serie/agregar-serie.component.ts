import { Component, OnInit } from '@angular/core';
import { SerieService } from '../serie-service.service';
import { UsuarioService } from '../usuario-service.service';
import { Serie } from '../serie';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-agregar-serie',
  templateUrl: './agregar-serie.component.html',
  styleUrls: ['./agregar-serie.component.css']
})
export class AgregarSerieComponent implements OnInit {

  series: Serie[];
  usuario: Partial<Usuario>;

  constructor(public serieService: SerieService, public usuarioService: UsuarioService) {
    this.series = [];
    this.usuario = {};

  }

  ngOnInit(): void {
    this.usuario = this.usuarioService.usuario;
    this.serieService.findAll().subscribe(data => {
      this.series = data;
    });
  }

  ngOnDestroy() {
    this.usuarioService.usuario = this.usuario;
  }

  buscador(){
    var lista : Array<Serie> = [];
    var self = this;
    var search = (<HTMLInputElement>document.getElementById("gsearch")).value;
    this.series.forEach( function(valor, indice, array) {
      if(valor.nombreSerie.startsWith(search)){
          lista.push(valor);
      }
    });
    if(lista.length == 0){
      alert("Sin resultados de la búsqueda")
    }
    this.crearDivSeries(lista);
  }

  mostrarSeries(letra:string){
    var lista : Array<Serie> = [];
    if(letra.length == 0){

    }else{
    this.series.forEach( function(valor, indice, array) {
      if(valor.nombreSerie[0] == letra){
          lista.push(valor);
      }
    });
    }

    this.crearDivSeries(lista);
  }

  crearDivSeries(lista :Serie[]){

    var self = this;
    var div :any= document.getElementById('series');
    div.innerHTML = '';
    lista.sort();
    lista.forEach( function(valor, indice, array) {
        var elemento1 = self.crearElemento("label", valor.nombreSerie, ["class", "labelsseries"]);
        elemento1.addEventListener('click', (e) => {
          self.visibleONo(valor.nombreSerie);
        });
        var elemento2 = self.crearElemento("input", null, ["type", "button", "id", valor.nombreSerie+"agregar", "value", "Agregar Serie", "class", "inputs"]);
        elemento2.addEventListener('click', (e) => {
          self.usuarioService.anhadirSeriePendiente(self.usuario.nombre, valor.idSerie).subscribe(data => {
              self.usuario = data;
          }, error => {});
        });
        var elemento3 = self.crearElemento("br", null, []);
        var elemento4 = self.crearElemento("div", null, ["id", valor.nombreSerie, "style", "display: none;","class", "tablita"]);
        var elemento5 = self.crearElemento("p", valor.sinopsis, ["class", "sinopsis"]);
        var elemento51 = self.crearElemento("p", "Creadores: "+valor.creadores.map(function(item) {return item.nombre;}), ["class", "creadores"]);
        var elemento52 = self.crearElemento("p", "Actores: "+valor.actores.map(function(item) {return item.nombre;}), ["class", "actores"]);
        var elemento6 = self.crearElemento("input", null, ["type", "button", "id", valor.nombreSerie+"cerrar", "value", "Cerrar", "class", "inputs"]);
        elemento6.addEventListener('click', (e) => {
          self.visibleONo(valor.nombreSerie);
        });
        var elemento7 = self.crearElemento("br", null, []);
        var elemento8 = self.crearElemento("br", null, []);
        elemento4.appendChild(elemento5);
        elemento4.appendChild(elemento51);
        elemento4.appendChild(elemento52);
        elemento4.appendChild(elemento6);
        elemento4.appendChild(elemento7);
        elemento4.appendChild(elemento8);
        div.appendChild(elemento1);
        div.appendChild(elemento2);
        div.appendChild(elemento3);
        div.appendChild(elemento4);
        div.appendChild(elemento7);
        div.appendChild(elemento8);
    });

  }

  crearElemento(etiqueta: string, texto: any, argumentos:any[]) {
    var nuevoElemento = document.createElement(etiqueta);
    // Si para el texto se pasa como parámetro un null es que no tiene textNode
    if (texto !== null) {
        var nuevoTexto = document.createTextNode(texto);
        nuevoElemento.appendChild(nuevoTexto);
    }
    // Si la longitud de argumentos es mayor que dos,
    // es que se han pasado atributos para el elemento
    if (argumentos.length >= 2) {
        for (var i = 0; i < argumentos.length; i = i + 2) {
            nuevoElemento.setAttribute(argumentos[i], argumentos[i + 1]);
        }
    }
    return nuevoElemento;
  }

  visibleONo(id:string) {
  var x :any= document.getElementById(id);
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}

}
