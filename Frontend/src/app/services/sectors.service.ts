import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Sector } from '../models/Sector';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Company } from '../models/Company';
import { Compare2Sector } from '../models/Compare2Sector';
import { CompareCompaniesInSector } from '../models/CompareCompaniesInSector';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class SectorsService {
  getAllSectorsUrl: string;
  getAllCompanyBySectorUrl: string;
  compareUrl: string;

  constructor(private http: HttpClient) {
    this.getAllSectorsUrl = "http://localhost:8083/getAllSector";
    this.getAllCompanyBySectorUrl = "http://localhost:8083/getCompany"
    this.compareUrl = "http://localhost:8005/sector-ws/compare/2sector"
   }

  getAllSectors(): Observable<Sector[]>{
    return this.http.get<Sector[]>(this.getAllSectorsUrl);
  }

  getAllCompanyBySector(sectorName: string): Observable<Company[]>{
    return this.http.get<Company[]>(this.getAllCompanyBySectorUrl + `/${sectorName}`);
  }

  getComparedSectorList(value: Compare2Sector): Observable<CompareCompaniesInSector[]>{
    return this.http.post<CompareCompaniesInSector[]>(this.compareUrl, value, httpOptions);
  }
}
