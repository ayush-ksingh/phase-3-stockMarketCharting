import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { SignupComponent } from './components/signup/signup.component';
import { LoginComponent } from './components/login/login.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { UserDashboardComponent } from './components/user/user-dashboard/user-dashboard.component';
import { CompanyListComponent } from './components/admin/company-list/company-list.component';
import { UploadDataComponent } from './components/admin/upload-data/upload-data.component';
import { StockExchangeComponent } from './components/admin/stock-exchange/stock-exchange.component';
import { IpoListComponent } from './components/admin/ipo-list/ipo-list.component';
import { ViewIpoComponent } from './components/user/view-ipo/view-ipo.component';
import { CompareCompanyComponent } from './components/user/compare-company/compare-company.component';
import { CompareSectorComponent } from './components/user/compare-sector/compare-sector.component';
import { EditCompanyComponent } from './components/admin/edit-company/edit-company.component';
import { AddCompanyComponent } from './components/admin/add-company/add-company.component';
import { AddStockComponent } from './components/admin/add-stock/add-stock.component';
import { AddIpoComponent } from './components/admin/add-ipo/add-ipo.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch:'full'},
  {path: 'signup', component: SignupComponent},
  {path: 'login', component: LoginComponent},
  {
    path: 'adminDashboard', component: AdminDashboardComponent,
    children: [
      {path: '', component: UploadDataComponent},
      {path: 'companyList', component: CompanyListComponent},
      {path: 'stockExchange', component: StockExchangeComponent},
      {path: 'ipoList', component: IpoListComponent},
      {path: 'addCompany', component: AddCompanyComponent},
      {path: 'editCompany/:id', component: EditCompanyComponent},
      {path: 'addStockExchange', component: AddStockComponent},
      {path: 'addIpo/:id', component: AddIpoComponent}
  ]
  },
  {
    path: 'userDashboard', component: UserDashboardComponent,
    children: [
      {path: '', component: ViewIpoComponent},
      {path: 'compareByCompany', component: CompareCompanyComponent},
      {path: 'compareBySector', component: CompareSectorComponent}
    ] 
  },
  {path: '**', component: NotFoundComponent}
  
  
  
  
];

@NgModule({
  declarations: [],
  exports: [RouterModule],
  imports: [
    RouterModule.forRoot(routes)
  ]
})
export class AppRoutingModule { }
