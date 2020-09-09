import { Component, OnInit, ViewChild } from '@angular/core';
import { Ipo } from '../../../models/Ipo';
import { IpoService } from '../../../services/ipo.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-ipo',
  templateUrl: './add-ipo.component.html',
  styleUrls: ['./add-ipo.component.css']
})
export class AddIpoComponent implements OnInit {
  ipo: Ipo;
  id: number;

  @ViewChild('addIpoForm')form: any;

  constructor(private ipoService: IpoService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.ipoService.getIpoById(this.id).subscribe((response) => {
      this.ipo = response;
    });
  }

  onSubmit({value, valid}: {value: Ipo, valid: boolean}){
    if (valid){
      value.ipoId = this.id;
      this.ipoService.updateIpo(value).subscribe();
      this.router.navigate(['/adminDashboard/ipoList']); 
    }
  }

}
