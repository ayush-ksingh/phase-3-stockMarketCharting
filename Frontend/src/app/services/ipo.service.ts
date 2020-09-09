import { Injectable } from '@angular/core';
import { Ipo } from '../models/Ipo';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class IpoService {
  getAllIpo: string;
  updateIpoUrl: string;
  getIpoUrl: string;

  constructor(private http: HttpClient) {
    this.getAllIpo = "http://localhost:8003/company-ws/ipo/viewIpo";
    this.updateIpoUrl = "http://localhost:8003/company-ws/ipo/updateIpo";
    this.getIpoUrl = "http://localhost:8003/company-ws/ipo/getIpoById";
   }

  getAllIpos(): Observable<Ipo[]> {
    return this.http.get<Ipo[]>(this.getAllIpo);
  }

  updateIpo(value: Ipo): Observable<Ipo> {
    return this.http.put<Ipo>(this.updateIpoUrl, value, httpOptions);
  }

  getIpoById(id: number): Observable<Ipo> {
    return this.http.get<Ipo>(this.getIpoUrl + `/${id}`);
  }
}
