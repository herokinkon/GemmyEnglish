// Angular material modules
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// Others
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
// PrimeNG table
import { AutoCompleteModule } from 'primeng/autocomplete';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { FieldsetModule } from 'primeng/fieldset';
import { FullCalendarModule } from 'primeng/fullcalendar';
import { InputTextModule } from 'primeng/inputtext';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { PickListModule } from 'primeng/picklist';
import { SelectButtonModule } from 'primeng/selectbutton';
import { TableModule } from 'primeng/table';
import { ToggleButtonModule } from 'primeng/togglebutton';
// Pages components
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AssistantTimelineComponent } from './pages/assistant-timeline/assistant-timeline.component';
import { TimelineDetailComponent } from './pages/assistant-timeline/timeline-detail/timeline-detail.component';
import { ClassDetailComponent } from './pages/class-management/class-detail/class-detail.component';
import { ClassManagementComponent } from './pages/class-management/class-management.component';
import { ExamDetailComponent } from './pages/exam-management/exam-detail/exam-detail.component';
import { ExamManagementComponent } from './pages/exam-management/exam-management.component';
import { ExamResultComponent } from './pages/exam-management/exam-result/exam-result.component';
import { LoginComponent } from './pages/login/login.component';
import { MainPageComponent } from './pages/main-page/main-page.component';
import { NewPaymentComponent } from './pages/payment-management/new-payment/new-payment.component';
import { PaymentManagementComponent } from './pages/payment-management/payment-management.component';
import { StudentPaymentListComponent } from './pages/payment-management/student-payment-list/student-payment-list.component';
import { StaffDetailComponent } from './pages/staff-management/staff-detail/staff-detail.component';
import { SalaryManagementComponent } from './pages/salary-management/salary-management.component';
import { OtherOutcomeDetailComponent } from './pages/salary-management/other-outcome-detail/other-outcome-detail.component';
import { StaffManagementComponent } from './pages/staff-management/staff-management.component';
import { StudentDetailComponent } from './pages/student-management/student-detail/student-detail.component';
import { StudentManagementComponent } from './pages/student-management/student-management.component';
import { JwtInterceptor } from './shared/authentication/jwtInterceptor';
import { CommonDetailDialogComponent } from './shared/components/common-detail-dialog/common-detail-dialog.component';
import { HeaderComponent } from './shared/components/header/header.component';
import { SideMenuComponent } from './shared/components/side-menu/side-menu.component';
import { MyProfileComponent } from './pages/my-profile/my-profile.component';
import { AccountManagementComponent } from './pages/account-management/account-management.component';
import { AccountDetailComponent } from './pages/account-management/account-detail/account-detail.component';

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
    PaymentManagementComponent,
    StudentPaymentListComponent,
    NewPaymentComponent,
    StaffManagementComponent,
    StaffDetailComponent,
    ExamManagementComponent,
    ExamDetailComponent,
    ExamResultComponent,
    AssistantTimelineComponent,
    TimelineDetailComponent,
    SalaryManagementComponent,
    OtherOutcomeDetailComponent,
    MyProfileComponent,
    AccountManagementComponent,
    AccountDetailComponent
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
    InputTextModule,
    MatSelectModule,
    MatCheckboxModule,
    AutoCompleteModule,
    DropdownModule,
    FieldsetModule,
    SelectButtonModule,
    PickListModule,
    ToggleButtonModule,
    FullCalendarModule,
    CalendarModule,
    NgxMaterialTimepickerModule,
    MatSlideToggleModule,
    OverlayPanelModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
