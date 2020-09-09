import { Component, OnInit } from '@angular/core';
import { User } from '../../models/User';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userTypeAdmin: boolean;
  userTypeUser: boolean;

  constructor() { }

  ngOnInit(): void {

  }

  toggleFunc(ref: string) {
    if (ref==='admin'){
      this.userTypeAdmin = true;
      this.userTypeUser = false;
    }
    if (ref==='user'){
      this.userTypeUser = true;
      this.userTypeAdmin = false;
    }
  }
}
