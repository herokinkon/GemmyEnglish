import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './shared/components/header/header.component';
import { SideMenuComponent } from './shared/components/side-menu/side-menu.component';

// Angular material modules
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatListModule } from '@angular/material/list';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatDialogModule } from '@angular/material/dialog';
import { MatGridListModule } from '@angular/material/grid-list';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatNativeDateModule} from '@angular/material/core';
import {MatTabsModule} from '@angular/material/tabs';
import {MatSelectModule} from '@angular/material/select';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatCardModule} from '@angular/material/card';

// PrimeNG table
import { TableModule } from 'primeng/table';
import {InputTextModule} from 'primeng/inputtext';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

// Pages components
import { StudentManagementComponent } from './pages/student-management/student-management.component';
import { StudentDetailComponent } from './pages/student-management/student-detail/student-detail.component';
import { ClassManagementComponent } from './pages/class-management/class-management.component';
import { ClassDetailComponent } from './pages/class-management/class-detail/class-detail.component';
import { CommonDetailDialogComponent } from './shared/components/common-detail-dialog/common-detail-dialog.component';
import { LoginComponent } from './pages/login/login.component';
import { MainPageComponent } from './pages/main-page/main-page.component';
import { JwtInterceptor } from './shared/authentication/jwtInterceptor';
import { ExamManagementComponent } from './pages/exam-management/exam-management.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SideMenuComponent,
    StudentManagementComponent,
    StudentDetailComponent,
    ClassManagementComponent,
    ClassDetailComponent,
    CommonDetailDialogComponent,
    LoginComponent,
    MainPageComponent,
    ExamManagementComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatListModule,
    MatDividerModule,
    MatButtonModule,
    MatInputModule,
    MatDialogModule,
    MatGridListModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatTabsModule,
    MatCardModule,
    FormsModule,
    TableModule,
    HttpClientModule,
    FontAwesomeModule,
    InputTextModule,
    MatSelectModule,
    MatCheckboxModule
  ],
  providers: [ { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
