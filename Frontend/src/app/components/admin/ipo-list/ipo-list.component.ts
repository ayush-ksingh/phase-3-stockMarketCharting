import { Component, OnInit } from '@angular/core';
import { Ipo } from '../../../models/Ipo';
import { IpoService } from '../../../services/ipo.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ipo-list',
  templateUrl: './ipo-list.component.html',
  styleUrls: ['./ipo-list.component.css']
})
export class IpoListComponent implements OnInit {
  ipos: Ipo[];

  constructor(private ipoService: IpoService,
    private router: Router) { }

  ngOnInit(): void {
    this.ipoService.getAllIpos().subscribe((response) => {
      this.ipos = response;
    });
  }

}
