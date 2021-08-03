import { Creador } from './creador';
import { Actor } from './actor';
import { Temporada } from './temporada';

export interface Serie {
  idSerie: number;
  nombreSerie: string;
  sinopsis: string;
  creadores: Array<Creador>;
  actores:Array<Actor>;
  temporadas: Array<Temporada>
}
