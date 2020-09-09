import { Component, OnInit, ViewChild } from '@angular/core';
import { CompareCompaniesInSector } from '../../../models/CompareCompaniesInSector';
import { Compare2Sector } from '../../../models/Compare2Sector';
import { SectorsService } from '../../../services/sectors.service';

@Component({
  selector: 'app-compare-sector',
  templateUrl: './compare-sector.component.html',
  styleUrls: ['./compare-sector.component.css']
})
export class CompareSectorComponent implements OnInit {
  compare: Compare2Sector = {
    sectorName1: '',
    sectorName2: '',
    from: new Date,
    to: new Date
  };

  result: boolean = false;

  output: CompareCompaniesInSector[];

  @ViewChild('sort2Sector')form: any;

  constructor(private sectorsService: SectorsService) { }

  ngOnInit(): void {
  }

  onSubmit({value, valid}: {value: Compare2Sector, valid: boolean}){
    if (!valid){
      alert('Please fill the form correctly')
    } else {
      this.sectorsService.getComparedSectorList(value).subscribe((response) => {
        this.output = response;
      });
      this.result = true;
    }
  }

}
