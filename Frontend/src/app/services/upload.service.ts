import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UploadSummary } from '../models/UploadSummary';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'undefined'})
}

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  url: string;

  constructor(private http: HttpClient) { 
    this.url = "http://localhost:8004/upload-ws/upload";
  }

  getUploadSummary(file: any):Observable<UploadSummary>{
    const formData = new FormData();
    formData.append('file', file, file.name);
    return this.http.post(this.url, formData);
  }
}


