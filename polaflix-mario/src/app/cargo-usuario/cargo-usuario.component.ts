import { Component, OnInit } from '@angular/core';
import { UsuarioService } from  '../usuario-service.service';
import { Usuario } from '../usuario';
import { ChangeDetectorRef } from '@angular/core';
import { Cargo } from '../cargo';
import 'rxjs/add/operator/catch';

@Component({
  selector: 'app-cargo-usuario',
  templateUrl: './cargo-usuario.component.html',
  styleUrls: ['./cargo-usuario.component.css']
})
export class CargoUsuarioComponent implements OnInit {

  public cargos : Cargo[] = [];
  public cargo: number = 0;
  public usuario : any;

  constructor(public usuarioService: UsuarioService, public cRef: ChangeDetectorRef) {
    this.usuario = this.usuarioService.usuario;

  }

  ngOnInit() {
    var self = this;
    this.usuarioService.verCargos(this.usuario.nombre).subscribe(data => {
      self.changeArray(data, self);}, error => {console.log(error);
        alert("Error: " + error.status + "\n" + error.message)})
  }

  changeArray(items:any, self:any) : void{
    self.cargos = items;
    self.cargo = self.cargos.length;
    self.cRef.detectChanges();
  }

  subirCargo(){
    if (this.cargo < this.cargos!.length){
      this.cargo += 1;
    }
  }

  bajarCargo(){
    if (this.cargo > 1){
      this.cargo -= 1;
    }
  }

}
