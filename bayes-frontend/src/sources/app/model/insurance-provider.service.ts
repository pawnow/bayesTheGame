import {Injectable} from "@angular/core";
import {Insurance} from "./insurance";
import {RequestInvokerService} from "../http/request-invoker.service";

@Injectable()
export class InsuranceProviderService {
    private insurances: Insurance[];
    private getInsurancesUrl = '/insurances/';

    constructor(private requestInvokerService: RequestInvokerService) {}

    getInsurances(): Insurance[] {
        return this.insurances;
    }

    updateInsuranceListByHttp(playerName: string): void {
        var url = this.getInsurancesUrl + playerName;

        this.requestInvokerService.invokeGetRequest(url)
            .subscribe(res => {
                    this.insurances = res as Insurance[]
                },
                (err) => console.log(err)
            );

    }
}