import { Component, OnInit, ViewChild } from '@angular/core';
import { Company } from 'src/app/models/Company';
import { CompanyService } from 'src/app/services/company.service';
import { FlashMessagesService } from 'angular2-flash-messages';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.css']
})
export class AddCompanyComponent implements OnInit {
  company: Company = {
    companyName: '',
    stockExchangeName: '',
    sector: '',
    brief: '',
    boardOfDirectors: '',
    ceo: '',
    stockCode: ''
  };

  @ViewChild('addCompanyForm')form: any;

  constructor(private companyService: CompanyService,
    private flashMessage: FlashMessagesService,
    private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit({value, valid}: {value: Company, valid: boolean}){
    if (!valid){
      alert('Please fill out the form')
  } else {
    this.companyService.addCompany(value).subscribe();
    this.flashMessage.show('Company Data Saved...', {
      cssClass: 'alert-success', timeout: 3000
    });
    this.form.reset();
    this.router.navigate(['/adminDashboard/companyList']);
    }
  }
}
