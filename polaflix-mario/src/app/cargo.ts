import { CapituloCargo } from './capituloCargo';

export interface Cargo {
  id: number;
  cuotaFinal: number;
  fechaCargo : string;
  listaCapitulosVistos : Array<CapituloCargo>;
}
