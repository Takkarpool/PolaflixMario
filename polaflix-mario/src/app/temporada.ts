import { Capitulo } from './capitulo';

export interface Temporada {
  id: number;
  numTemporada: number;
  capitulos: Array<Capitulo>;
}
