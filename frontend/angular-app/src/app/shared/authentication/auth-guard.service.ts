import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
    providedIn: 'root'
})
export class AuthGuardService implements CanActivate {
    constructor(public auth: AuthService, public router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, routerState: RouterStateSnapshot): boolean {
        if (!this.auth.isAuthenticated()) {
            this.router.navigate(['login'], { queryParams: { returnUrl: routerState.url } });
            return false;
        }
        return true;
    }
}
