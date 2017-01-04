import {Injectable} from "@angular/core";
import {InsuranceProviderService} from "../model/insurance-provider.service";

@Injectable()
export class InitializationService {
    constructor(private insuranceProviderService: InsuranceProviderService) {
    }

    initialize(playerName: string): void {
        this.insuranceProviderService.updateInsuranceListByHttp(playerName);
    }

}