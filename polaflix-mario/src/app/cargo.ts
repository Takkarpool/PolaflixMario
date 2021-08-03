import { CapituloCargo } from './capituloCargo';

export interface Cargo {
  id: number;
  cuotaFinal: number;
  fechaCargo : string;
  cuotaFija : number;
  listaCapitulosVistos : Array<CapituloCargo>;
}
