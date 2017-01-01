import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Player } from '../model/player';
import { PlayerProviderService } from '../model/player-provider.service';
import { PlayerInsuranceProviderService } from '../model/player-insurance-provider.service';
import { InsuranceProviderService } from '../model/insurance-provider.service';
import {Insurance} from "../model/insurance";
import {LocationsProviderService} from "../model/locations-provider.service";

@Component({
    selector: 'game-menu',
    template: require('./game-menu.component.html')
})
export class GameMenuComponent {
    constructor(private router:Router,
                private playerProviderService:PlayerProviderService,
                private insuranceProviderService: InsuranceProviderService,
                private locationsProviderService: LocationsProviderService,
                private playerInsuranceProviderService:PlayerInsuranceProviderService) {
    }

    buyInsurance(insurance: Insurance):void {
        let link = ['/insurance'];
        this.router.navigate(link);
    }

    travel():void {
        let link = ['/travel'];
        this.router.navigate(link);
    }

    itemShop():void {
        let link = ['/shop'];
        this.router.navigate(link);
    }

    nextTurn():void {
        var playerName: string = this.playerProviderService.getPlayer().name;
        this.playerProviderService.nextTurn();
        this.locationsProviderService.updateLocationListByHttp();
        this.insuranceProviderService.updateInsuranceListByHttp();
        this.playerInsuranceProviderService.updatePlayerInsuranceListByHttp(playerName);
    }

}