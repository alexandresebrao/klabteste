import {Injectable, NgModule} from "@angular/core";
import {
  HTTP_INTERCEPTORS,
  HttpClient,
  HttpClientModule,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from "@angular/common/http";


@Injectable()
class ApiInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const request = req.clone({url: `/api/${req.url}`})
    return next.handle(request)
  }
}

@NgModule({
  imports: [HttpClientModule],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: ApiInterceptor, multi: true }],
  exports: [HttpClientModule]
})
export class HttpserviceModule {
  
  constructor(private http: HttpClient) { }
  
  get(url: string) {
    return this.http.get(url);
  }
  
}
