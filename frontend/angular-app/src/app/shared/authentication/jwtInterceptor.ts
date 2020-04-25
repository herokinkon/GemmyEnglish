import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConstant } from '../app-constant.service';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
  })
export class JwtInterceptor implements HttpInterceptor {
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = localStorage.getItem(AppConstant.STORED_TOKEN);
        if (token) {
            request = request.clone({
                setHeaders: {
                    'X-Token': `${token}`
                }
            });
        }
        return next.handle(request);
    }

}
