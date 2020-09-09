import { Injectable } from '@angular/core';
import { StockExchange } from '../models/StockExchange';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class StockExchangeService {
  getAllStockExchangesUrl: string;
  addStockExchangeUrl: string;

  constructor(private http: HttpClient) { 
    this.getAllStockExchangesUrl = "http://localhost:8004/exchange-ws/getAllExchanges";
    this.addStockExchangeUrl = "http://localhost:8004/exchange-ws/addExchange";
  }

  getStockExchanges(): Observable<StockExchange[]> {
    return this.http.get<StockExchange[]>(this.getAllStockExchangesUrl);
  }

  addStockExchange(value: StockExchange): Observable<StockExchange>{
    return this.http.post<StockExchange>(this.addStockExchangeUrl, value, httpOptions);
  }
}
