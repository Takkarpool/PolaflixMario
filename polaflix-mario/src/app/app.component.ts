import { Component, OnInit, OnDestroy } from '@angular/core';
import { UsuarioService } from  './usuario-service.service';
import { Usuario } from './usuario';

@Component({
  selector: 'app-root',
  templateUrl: './usuario-Repertorio.html',
  styleUrls: ['./usuario-Repertorio.css']
})
export class AppComponent implements OnInit{
  title = 'Polaflix-Mario';

  usuario: Partial<Usuario> = {};

  constructor(public usuarioService: UsuarioService) {
    this.usuario = {};
  }

  ngOnInit() {
    this.usuarioService.find().subscribe(data => {
      this.usuario = data ;
    });
    ;
  }


}
