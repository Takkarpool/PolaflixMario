import { Injectable, OnDestroy } from '@angular/core';
import { Usuario } from './usuario';
import { Cargo } from './cargo';
import { SerieEmpezada } from './serieEmpezada';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService{
  public usuario : Partial<Usuario>;
  private usuarioUrl: string;

  constructor(private http: HttpClient, private router: Router) {
    this.usuarioUrl = 'http://localhost:8080/usuarios/'+
    window.location.href.split("/")[4];
    this.usuario = {};
  }

  public find(): Observable<Usuario> {
    return this.http.get<Usuario>(this.usuarioUrl);
  }

  public anhadirSeriePendiente(usuario:any, serie:any): Observable<Usuario>{
    var putUrl = 'http://localhost:8080/usuarios/'+usuario+'/agregar-serie?id='+serie;
    var headers = new HttpHeaders({ 'content-type': 'application/json',
                    'Access-Control-Allow-Origin': '*'})
    var body=JSON.stringify(null);
    return this.http.put<Usuario>(putUrl, body,{'headers':headers})
  }

  public verCapitulo(usuario:any, serie:any, temporada:any, capitulo:any): Observable<Usuario>{
    var putUrl = 'http://localhost:8080/usuarios/'+usuario+'/ver-capitulo?serie='+serie+'&temporada='+temporada+'&capitulo='+capitulo;
    var headers = new HttpHeaders({ 'content-type': 'application/json',
                    'Access-Control-Allow-Origin': '*'})
    var body=JSON.stringify(null);
    console.log(putUrl);

    return this.http.put<Usuario>(putUrl, body,{'headers':headers});
  }

  public verCargos(usuario:any): Observable<Cargo[]>{
    var getUrl = 'http://localhost:8080/usuarios/'+usuario+'/ver-cargos';
    console.log(getUrl);

    return this.http.get<Cargo[]>(getUrl)
  }

}
