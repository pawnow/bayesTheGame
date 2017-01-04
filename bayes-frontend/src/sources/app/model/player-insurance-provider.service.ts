import {Injectable} from "@angular/core";
import {Insurance} from "./insurance";
import {RequestInvokerService} from "../http/request-invoker.service";
import {PlayerProviderService} from "./player-provider.service";

@Injectable()
export class PlayerInsuranceProviderService {
    insurances: Insurance[];
    private playerUrl = '/player/';
    private playerInsurancesGetUrl = '/insurances/';
    private playerInsurancesPostUrl = '/buyInsurance/';

    constructor(private requestInvokerService: RequestInvokerService,
                private playerProviderService: PlayerProviderService) {
        this.insurances = []
    }

    buyInsurance(insurance: Insurance, playerName: string): void {
        var url = this.playerUrl + playerName + this.playerInsurancesPostUrl;

        this.requestInvokerService.invokePostRequest(url, insurance)
            .subscribe(res => {
                    this.insurances = res as Insurance[]
                },
                (err) => console.log(err),
                () => this.playerProviderService.updatePlayerInfoByHttp()
            );
    }

    getInsurances(): Insurance[] {
        return this.insurances;
    }

    updatePlayerInsuranceListByHttp(playerName: string): void {
        var url = this.playerUrl + playerName + this.playerInsurancesGetUrl;

        this.requestInvokerService.invokeGetRequest(url)
            .subscribe(res => {
                    this.insurances = res as Insurance[]
                },
                (err) => console.log(err)
            );
    }
}