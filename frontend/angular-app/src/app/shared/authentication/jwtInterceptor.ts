import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { AppConstant } from '../app-constant.service';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { NotificationService } from '../service/notification.service';

@Injectable({
    providedIn: 'root'
})
export class JwtInterceptor implements HttpInterceptor {
    constructor(private notificationService: NotificationService) { }
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = localStorage.getItem(AppConstant.STORED_TOKEN);
        if (token) {
            request = request.clone({
                setHeaders: {
                    'X-Token': `${token}`
                }
            });
        }
        return next.handle(request).pipe(catchError(err => this.handleHttpError(err)));
    }

    handleHttpError(error: HttpErrorResponse): Observable<never> {
        if (error.error.message) {
            this.notificationService.addErrorMessage('', error.error.message);
        } else {
            this.notificationService.addErrorMessage('', 'Request failed to connect server');
        }
        return throwError(error);
    }
}
