import { Injectable } from '@angular/core';
import { Serie } from './serie';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SerieService {
  private seriesUrl: string;

  constructor(private http: HttpClient) {
    this.seriesUrl = 'http://localhost:8080/series';
  }

  public findAll(): Observable<Serie[]> {
    return this.http.get<Serie[]>(this.seriesUrl);
  }
}
