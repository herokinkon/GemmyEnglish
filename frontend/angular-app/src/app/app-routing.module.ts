import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudentManagementComponent } from './pages/student-management/student-management.component';
import { ClassManagementComponent } from './pages/class-management/class-management.component';
import { ExamManagementComponent } from './pages/exam-management/exam-management.component';
import { StudentDetailComponent } from './pages/student-management/student-detail/student-detail.component';
import { LoginComponent } from './pages/login/login.component';
import { MainPageComponent } from './pages/main-page/main-page.component';
import { ClassDetailComponent } from './pages/class-management/class-detail/class-detail.component';
import { AuthGuardService } from './shared/authentication/auth-guard.service';
import { PaymentManagementComponent } from './pages/payment-management/payment-management.component';
import { StaffManagementComponent } from './pages/staff-management/staff-management.component';
import { StaffDetailComponent } from './pages/staff-management/staff-detail/staff-detail.component';
import { SalaryManagementComponent } from './pages/salary-management/salary-management.component';

const childRoutes: Routes = [
  { path: 'students', component: StudentManagementComponent },
  { path: 'student/:id', component: StudentDetailComponent },
  { path: 'classes', component: ClassManagementComponent },
  { path: 'classes/:id', component: ClassDetailComponent },
  { path: 'payment', component: PaymentManagementComponent },
  { path: 'staffs', component: StaffManagementComponent },
  { path: 'staff/:id', component: StaffDetailComponent },
  { path: 'exams', component: ExamManagementComponent },
  { path: 'salary', component: SalaryManagementComponent },
];

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: MainPageComponent, children: childRoutes, canActivate: [AuthGuardService] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
