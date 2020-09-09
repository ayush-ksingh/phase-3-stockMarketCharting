import { Component, OnInit } from '@angular/core';
import { Company } from '../../../models/Company';
import { CompanyService } from '../../../services/company.service';
import { Router } from '@angular/router';
import { FlashMessagesService } from 'angular2-flash-messages';
import { ResourceLoader } from '@angular/compiler';

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css']
})
export class CompanyListComponent implements OnInit {
  companies: Company[];

  constructor(private companyService: CompanyService,
    private router: Router,
    private flashMessage: FlashMessagesService) { }

  ngOnInit(): void {
    this.companyService.getCompanies().subscribe((response) => {
      this.companies = response;
    });
  }

  onDelete(id: number){
    this.companyService.deleteCompany(id).subscribe();
    this.flashMessage.show('Company Data deleted', {
      cssClass: 'alert-success', timeout: 3000
    })
    window.location.reload();
    // this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    // this.router.onSameUrlNavigation = 'reload';
    // this.router.navigate(['/adminDashboard/companyList']);
  }

}
