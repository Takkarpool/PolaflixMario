import { RepertorioPersonal } from './repertorioPersonal';
import { Cargo } from './cargo'

export interface Usuario {
  nombre: string;
  cuotaFija : number;
  cargos :Array<Cargo>;
  repertorioUsuario : RepertorioPersonal;

}
