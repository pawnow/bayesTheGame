import { Player } from '../model/player';
import { Injectable } from '@angular/core';
import {RequestInvokerService} from "../http/request-invoker.service";
import {InsuranceProviderService} from "../model/insurance-provider.service";
import {LocationsProviderService} from "../model/locations-provider.service";
import {PlayerInsuranceProviderService} from "../model/player-insurance-provider.service";
import {PlayerProviderService} from "../model/player-provider.service";

@Injectable()
export class TurnService{
    private playerNextTurn = '/player/nextTurn/';

    constructor(private requestInvokerService: RequestInvokerService,
                private insuranceProviderService: InsuranceProviderService,
                private locationsProviderService: LocationsProviderService,
                private playerProviderService: PlayerProviderService,
                private playerInsuranceProviderService:PlayerInsuranceProviderService) {
    }

    nextTurn(): void {
        var playerName: string = this.playerProviderService.getPlayer().name;
        this.requestInvokerService.invokePostRequest(this.playerNextTurn, this.playerProviderService.getPlayer())
            .subscribe(res=>{console.log(res)},
                err=>console.log(err),
                () => {
                    this.playerProviderService.updatePlayerInfoByHttp();
                    this.locationsProviderService.updateLocationListByHttp();
                    this.insuranceProviderService.updateInsuranceListByHttp(playerName);
                    this.playerInsuranceProviderService.updatePlayerInsuranceListByHttp(playerName)
                }
            );
    }

}