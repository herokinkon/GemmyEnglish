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

const childRoutes: Routes = [
  { path: 'students', component: StudentManagementComponent },
  { path: 'student/:id', component: StudentDetailComponent },
  { path: 'classes', component: ClassManagementComponent },
  { path: 'classes/:id', component: ClassDetailComponent },
  { path: 'exams', component: ExamManagementComponent }
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
