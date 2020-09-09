import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FlashMessagesModule } from 'angular2-flash-messages';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { UploadDataComponent } from './components/admin/upload-data/upload-data.component';
import { AddCompanyComponent } from './components/admin/add-company/add-company.component';
import { ViewIpoComponent } from './components/user/view-ipo/view-ipo.component';
import { CompareCompanyComponent } from './components/user/compare-company/compare-company.component';
import { AppRoutingModule } from './app-routing.module';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { UserDashboardComponent } from './components/user/user-dashboard/user-dashboard.component';
import { NavbarAdminComponent } from './components/navbar-admin/navbar-admin.component';
import { NavbarUserComponent } from './components/navbar-user/navbar-user.component';
import { CompanyListComponent } from './components/admin/company-list/company-list.component';
import { EditCompanyComponent } from './components/admin/edit-company/edit-company.component';
import { StockExchangeComponent } from './components/admin/stock-exchange/stock-exchange.component';
import { IpoListComponent } from './components/admin/ipo-list/ipo-list.component';
import { AddIpoComponent } from './components/admin/add-ipo/add-ipo.component';
import { CompareSectorComponent } from './components/user/compare-sector/compare-sector.component';
import { AddStockComponent } from './components/admin/add-stock/add-stock.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    UploadDataComponent,
    AddCompanyComponent,
    ViewIpoComponent,
    CompareCompanyComponent,
    AdminDashboardComponent,
    UserDashboardComponent,
    NavbarAdminComponent,
    NavbarUserComponent,
    CompanyListComponent,
    EditCompanyComponent,
    StockExchangeComponent,
    IpoListComponent,
    AddIpoComponent,
    CompareSectorComponent,
    AddStockComponent,
    NotFoundComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    FlashMessagesModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
