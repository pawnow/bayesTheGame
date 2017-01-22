import {Component} from "@angular/core";
import {PlayerProviderService} from "../model/player-provider.service";
import {LocationsProviderService} from "../model/location-provider.service";
import {Location} from "../model/location";
import {TravelService} from "./travel.service"

@Component({
    selector: 'travel',
    template: require('./travel.html')
})
export class TravelComponent {
    constructor(private playerProviderService: PlayerProviderService,
                private locationsProviderService: LocationsProviderService,
                private travelService: TravelService) {
    }

    getLocations(): Location[] {
        return this.locationsProviderService.getLocations();
    }

    travel(location: Location): void {
        return this.travelService.travel(location, this.playerProviderService.getPlayer().name);
    }

    isTravelDisabled(location: Location) {
        return this.playerProviderService.getPlayer().location == location.name || this.playerProviderService.getPlayer().money < location.price;
    }

    goBack(): void {
        window.history.back();
    }
}