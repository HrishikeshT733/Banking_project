import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token'); // Retrieve the token from storage

    if (token) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}` // Attach the token to the request headers
        }
      });
    }

    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401 || error.status === 403) {
           alert('Your session has expired. Please log in again.');
          // Token has expired or is invalid
          localStorage.removeItem('token'); // Clear the token from storage
          this.router.navigate(['/login']); // Redirect the user to the login page
        }
        return throwError(() => error); // Propagate the error
      })
    );
  }
}
