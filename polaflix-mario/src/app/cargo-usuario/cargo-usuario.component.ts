import { Component, OnInit } from '@angular/core';
import { UsuarioService } from  '../usuario-service.service';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-cargo-usuario',
  templateUrl: './cargo-usuario.component.html',
  styleUrls: ['./cargo-usuario.component.css']
})
export class CargoUsuarioComponent implements OnInit {

  usuario: Partial<Usuario>;
  cargo: number = 0;

  constructor(public usuarioService: UsuarioService) {
      this.usuario = {};
  }

  ngOnInit() {
    this.usuario = this.usuarioService.usuario;
    this.cargo = this.usuario.cargos!.length;
  }

  subirCargo(){
    if (this.cargo < this.usuario.cargos!.length){
      this.cargo += 1;
    }
  }

  bajarCargo(){
    if (this.cargo > 1){
      this.cargo -= 1;
    }
  }

}
