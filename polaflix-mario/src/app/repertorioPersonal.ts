
import { Serie } from './serie';
import { SerieEmpezada } from './serieEmpezada';

export interface RepertorioPersonal {
  id: number;
  seriesEmpezadas : Array<SerieEmpezada>;
  seriesFinalizadas : Array<SerieEmpezada>;
  seriesPendientes : Array<Serie>;
}
