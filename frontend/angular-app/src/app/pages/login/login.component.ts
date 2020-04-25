import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/shared/authentication/auth.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isValid = true;
  message: string;

  username: string;
  password: string;
  private returnUrl: string;

  constructor(private authService: AuthService, private router: Router, private route: ActivatedRoute) {
    // redirect to home if already logged in
    if (this.authService.isAuthenticated()) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit(): void {
    this.returnUrl = this.route.snapshot.queryParams.returnUrl || '/';
  }

  login() {
    this.authService.login(this.username, this.password).subscribe(re => this.router.navigate([this.returnUrl]));
  }

}
