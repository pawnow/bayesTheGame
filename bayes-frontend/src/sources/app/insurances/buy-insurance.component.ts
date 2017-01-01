import { Component } from '@angular/core';
import {Insurance} from "../model/insurance";
import {PlayerInsuranceProviderService} from "../model/player-insurance-provider.service";
import {InsuranceProviderService} from "../model/insurance-provider.service";
import {PlayerProviderService} from "../model/player-provider.service";

@Component({
    selector: 'buy-insurance',
    template: require('./buy-insurance.html')
})
export class BuyInsuranceComponent {
    constructor(
        private playerProviderService: PlayerProviderService,
        private playerInsuranceProviderService: PlayerInsuranceProviderService,
        private insuranceProviderService: InsuranceProviderService){
    }

    getInsurances(): Insurance[]{
        return this.insuranceProviderService.getInsurances();
    }

    buyInsurance(insurance: Insurance): void{
        var playerName: string = this.playerProviderService.getPlayer().name;
        this.playerInsuranceProviderService.buyInsurance(insurance, playerName);
    }

    isBuyDisabled(insurance: Insurance): boolean{
        var insuranceAlreadyBough: boolean = this.playerInsuranceProviderService.getInsurances().filter(x => x.name == insurance.name).length > 0;
        return this.playerProviderService.getPlayer().money < insurance.price || insuranceAlreadyBough;
    }

    goBack(): void {
        window.history.back();
    }
}