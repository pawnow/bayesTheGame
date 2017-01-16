import {Injectable} from "@angular/core";
import {Insurance} from "./insurance";
import {RequestInvokerService} from "../http/request-invoker.service";

@Injectable()
export class InsuranceProviderService {
    private insurances: Insurance[];
    private getInsurancesUrl = '/insurances/';

    constructor(private requestInvokerService: RequestInvokerService) {
        this.insurances = [
            /*{
             name: "Health",
             price: 200
             },
             {
             name: "Car",
             price: 10
             },
             {
             name: "House",
             price: 100
             }*/
        ]
    }

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