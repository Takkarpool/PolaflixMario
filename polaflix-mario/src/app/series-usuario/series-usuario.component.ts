import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { UsuarioService } from  '../usuario-service.service';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-series-usuario',
  templateUrl: './series-usuario.component.html',
  styleUrls: ['./series-usuario.component.css']
})
export class SeriesUsuarioComponent implements OnInit, OnDestroy {

    usuario: Partial<Usuario>;

    constructor(public usuarioService: UsuarioService) {
        this.usuario = {};
    }

    ngOnInit() {
      this.usuarioService.find().subscribe(data => {
        this.usuario = data ;
      });
    }

    ngOnDestroy() {
      this.usuarioService.usuario = this.usuario;
    }
}
