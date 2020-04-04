import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudentManagementComponent } from './pages/student-management/student-management.component';
import { ClassManagementComponent } from './pages/class-management/class-management.component';

const routes: Routes = [
  { path: 'student', component: StudentManagementComponent },
  { path: 'classes', component: ClassManagementComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
