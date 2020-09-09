import { Component, OnInit } from '@angular/core';
import { StockExchange } from '../../../models/StockExchange';
import { StockExchangeService } from '../../../services/stock-exchange.service';

@Component({
  selector: 'app-stock-exchange',
  templateUrl: './stock-exchange.component.html',
  styleUrls: ['./stock-exchange.component.css']
})
export class StockExchangeComponent implements OnInit {
  stocks: StockExchange[];
  constructor(private stockExchangeService: StockExchangeService) { }

  ngOnInit(): void {
    this.stockExchangeService.getStockExchanges().subscribe((response) => {
      this.stocks = response;
    });
  }

}
