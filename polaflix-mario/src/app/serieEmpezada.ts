import { Serie } from './serie';

export interface SerieEmpezada {
  idSerieEmpezada: number;
  ultimoCapituloVisto : number;
  ultimaTemporadaVista : number;
  serie : Serie;
}
