import {Injectable} from "@angular/core";
import {InsuranceProviderService} from "../model/insurance-provider.service";
import {LocationsProviderService} from "../model/location-provider.service";

@Injectable()
export class InitializationService {
    constructor(private insuranceProviderService: InsuranceProviderService,
                private locationsProviderService: LocationsProviderService) {}

    initialize(playerName: string): void {
        this.insuranceProviderService.updateInsuranceListByHttp(playerName);
        this.locationsProviderService.updateLocationListByHttp();
    }

}