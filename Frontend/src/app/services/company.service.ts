import { Injectable } from '@angular/core';
import { Company } from '../models/Company';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Compare2Company } from '../models/Compare2Company';
import { StockPrice } from '../models/StockPrice';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  companyListUrl: string;
  companyAddUrl: string;
  getCompanyUrl: string;
  updateCompanyUrl: string;
  deleteCompanyUrl: string;
  newUrl: string;

  constructor(private http: HttpClient) {
    this.companyListUrl = "http://localhost:8088/company-service/company-ws/company/getAllCompany";
    this.companyAddUrl = "http://localhost:8003/company-ws/company/addCompany";
    this.getCompanyUrl = "http://localhost:8003/company-ws/company/getCompany";
    this.updateCompanyUrl = "http://localhost:8003/company-ws/company/updateCompany";
    this.deleteCompanyUrl = "http://localhost:8003/company-ws/company/deleteCompany";
    this.newUrl = "http://localhost:8003/company-ws/company/compare/stockPrices";

   }

  getCompanies(): Observable<Company[]> {
    return this.http.get<Company[]>(this.companyListUrl);
  }

  addCompany(value: Company): Observable<Company>{
    return this.http.post<Company>(this.companyAddUrl, value, httpOptions);
  }

  getCompany(id: number): Observable<Company> {
    return this.http.get<Company>(this.getCompanyUrl + `/${id}`);
  }

  updateCompany(value: Company): Observable<Company>{
    return this.http.put<Company>(this.updateCompanyUrl, value, httpOptions);
  }

  deleteCompany(id: number): Observable<Company>{
    return this.http.delete<Company>(this.deleteCompanyUrl + `/${id}`);
  }

  getStockPriceByCompanies(value: Compare2Company): Observable<StockPrice[]>{
    return this.http.post<StockPrice[]>(this.newUrl, value, httpOptions);
  }
}
