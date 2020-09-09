import { Component, OnInit, ElementRef } from '@angular/core';
import { UploadService } from '../../../services/upload.service';
import { UploadSummary } from '../../../models/UploadSummary';

@Component({
  selector: 'app-upload-data',
  templateUrl: './upload-data.component.html',
  styleUrls: ['./upload-data.component.css']
})
export class UploadDataComponent implements OnInit {
  uploaded: boolean = false;
  uploadSummary: UploadSummary;

  constructor(private element: ElementRef,
    private uploadService: UploadService) { }

  ngOnInit(): void {
  }

  onFileUpload() {
    let target: any = this.element.nativeElement.querySelector('#file').files[0];
    this.uploadService.getUploadSummary(target).subscribe(
      value => {
        if (value != null) {
          this.uploaded = true;
          this.uploadSummary = value;
        }
      });
  }

}
