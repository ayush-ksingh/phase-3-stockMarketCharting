import { Component, OnInit } from '@angular/core';
import { IpoService } from 'src/app/services/ipo.service';
import { Ipo } from 'src/app/models/Ipo';

@Component({
  selector: 'app-view-ipo',
  templateUrl: './view-ipo.component.html',
  styleUrls: ['./view-ipo.component.css']
})
export class ViewIpoComponent implements OnInit {
  ipos: Ipo[];

  constructor(private ipoService: IpoService) { }

  ngOnInit(): void {
    this.ipoService.getAllIpos().subscribe((response) => {
      this.ipos = response;
    });

  }

}
