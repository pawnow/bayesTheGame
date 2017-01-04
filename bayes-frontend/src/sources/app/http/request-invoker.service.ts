import {Injectable} from "@angular/core";
import {UrlBuilderService} from "../http/url-builder.service";
import {Headers, Http, Response, RequestOptions} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {Observable} from "rxjs/Rx";

@Injectable()
export class RequestInvokerService {

    constructor(private http: Http) {
    }

    invokeGetRequest(url: string): Observable<any> {
        return this.http.get(UrlBuilderService.buildUrl(url))
            .map((res: Response) => res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Server error'))
    }

    invokePostRequest(url: string, object: any): Observable<any> {
        let headers = new Headers({'Content-Type': 'application/json'/*, 'Accept': 'application/json'*/});
        let options = new RequestOptions({headers: headers});
        return this.http.post(UrlBuilderService.buildUrl(url), JSON.stringify(object), options)
            .map((res: Response) => res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Server error'))
    }
}