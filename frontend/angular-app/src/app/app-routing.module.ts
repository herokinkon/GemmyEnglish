import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClassDetailComponent } from './pages/class-management/class-detail/class-detail.component';
import { ClassManagementComponent } from './pages/class-management/class-management.component';
import { StudentDetailComponent } from './pages/student-management/student-detail/student-detail.component';
import { StudentManagementComponent } from './pages/student-management/student-management.component';

const routes: Routes = [
  { path: 'students', component: StudentManagementComponent },
  { path: 'student/:id', component: StudentDetailComponent },
  { path: 'classes', component: ClassManagementComponent },
  { path: 'classes/:id', component: ClassDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
