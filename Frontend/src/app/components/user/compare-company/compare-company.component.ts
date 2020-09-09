import { Component, OnInit, ViewChild } from '@angular/core';
import { SectorsService } from '../../../services/sectors.service';
import { Company } from '../../../models/Company';
import { Compare2Company } from '../../../models/Compare2Company';
import { CompanyService } from '../../../services/company.service';
import { StockPrice } from 'src/app/models/StockPrice';

@Component({
  selector: 'app-compare-company',
  templateUrl: './compare-company.component.html',
  styleUrls: ['./compare-company.component.css']
})
export class CompareCompanyComponent implements OnInit {
  stockPrices: StockPrice[];
  compare: Compare2Company = {
    companyName1: '',
    stockExchangeName1: '',
    companyName2: '',
    stockExchangeName2: '',
    from: new Date(),
    to: new Date()
  }

  result: boolean = false;

  @ViewChild('sortCompanyForm') form: any;

  constructor(private sectorsService: SectorsService,
    private companyService: CompanyService) { }

  ngOnInit(): void {
  }

  onSubmit({ value, valid }: { value: Compare2Company, valid: boolean }) {
    if (!valid) {
      alert('Please fill out the form')
    } else {
      console.log(this.compare.companyName1)
      this.companyService.getStockPriceByCompanies(value).subscribe((response) => {
        this.stockPrices = response;
      });
      this.result = true;
    }
  }


}
