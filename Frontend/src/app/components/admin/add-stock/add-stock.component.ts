import { Component, OnInit, ViewChild } from '@angular/core';
import { StockExchange } from '../../../models/StockExchange';
import { StockExchangeService } from '../../../services/stock-exchange.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-stock',
  templateUrl: './add-stock.component.html',
  styleUrls: ['./add-stock.component.css']
})
export class AddStockComponent implements OnInit {
  stockExchange: StockExchange = {
    stockExchangeName: '',
    brief: '',
    address: '',
    remarks: ''
  }

  @ViewChild('addStockExchangeForm')form: any;

  constructor(private stockExchangeService: StockExchangeService,
    private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit({value, valid}: {value: StockExchange, valid: boolean}){
    if (!valid){
      alert('Fill out the form correctly')
    } else {
      this.stockExchangeService.addStockExchange(value).subscribe();
      this.router.navigate(['/adminDashboard/stockExchange']);
    }
  }

}
