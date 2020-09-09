import { Component, OnInit, ViewChild } from '@angular/core';
import { CompanyService } from '../../../services/company.service';
import { Company } from 'src/app/models/Company';
import { Router, ActivatedRoute } from '@angular/router';
import { FlashMessagesService } from 'angular2-flash-messages';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-company',
  templateUrl: './edit-company.component.html',
  styleUrls: ['./edit-company.component.css']
})
export class EditCompanyComponent implements OnInit {
  company: Company;
  id: number;
  @ViewChild('editCompanyForm')form: any;

  constructor(
    private companyService: CompanyService,
    private router: Router,
    private route: ActivatedRoute,
    private flashMessage: FlashMessagesService
    ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.companyService.getCompany(this.id).subscribe((response) => {
      this.company = response;
    });
  }

  onSubmit({value, valid}: {value: Company, valid: boolean}){
    if (valid){
      value.companyId = this.id;
      this.companyService.updateCompany(value).subscribe();
      this.router.navigate(['/adminDashboard']);
    }
  }
  
  

}
