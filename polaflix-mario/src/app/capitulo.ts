import { Video } from './video';

export interface Capitulo {
  id: number;
  numero: number;
  titulo: string;
  descripcion:string;
  videoCapitulo: Video;
}
