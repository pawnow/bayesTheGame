import {Component} from "@angular/core";
import {Insurance} from "../model/insurance";
import {PlayerInsuranceProviderService} from "../model/player-insurance-provider.service";

@Component({
    selector: 'player-insurance',
    template: require('./player-insurance.html')
})
export class PlayerInsuranceComponent {
    constructor(private playerInsuranceProviderService: PlayerInsuranceProviderService) {
    }

    getInsurances(): Insurance[] {
        return this.playerInsuranceProviderService.getInsurances();
    }
}