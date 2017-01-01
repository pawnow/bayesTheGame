import { Component } from '@angular/core';
import {Insurance} from "../model/insurance";
import {PlayerInsuranceProviderService} from "../model/player-insurance-provider.service";
import {InsuranceProviderService} from "../model/insurance-provider.service";
import {PlayerProviderService} from "../model/player-provider.service";
import {LocationsProviderService} from "../model/locations-provider.service";
import {Location} from "../model/location";

@Component({
    selector: 'travel',
    template: require('./travel.html')
})
export class TravelComponent {
    constructor(
        private playerProviderService: PlayerProviderService,
        private locationsProviderService: LocationsProviderService){
    }

    getLocations(): Location[]{
        return this.locationsProviderService.getLocations();
    }

    travel(location: Location): void{
        return this.locationsProviderService.travel(location, this.playerProviderService.getPlayer().name);
    }

    isTravelDisabled(location: Location){
        return this.playerProviderService.getPlayer().location == location.name;
    }

    goBack(): void {
        window.history.back();
    }
}