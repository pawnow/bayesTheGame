import { Injectable } from '@angular/core';
import {Insurance} from "./insurance";
import {Location} from "./location";
import {RequestInvokerService} from "../http/request-invoker.service";
import {PlayerProviderService} from "./player-provider.service";

@Injectable()
export class LocationsProviderService{
    locations:Location[];
    private playerUrl = '/player/';
    private locationsUrl = '/locations/';
    private postLocationsUrl = '/travel/';

    constructor(private requestInvokerService: RequestInvokerService,
                private playerProviderService: PlayerProviderService) {
        this.locations =[
        ]
    }

    travel(location:Location, playerName: string): void{
        var url = this.playerUrl + playerName + this.postLocationsUrl;

        this.requestInvokerService.invokePostRequest(url, location)
            .subscribe(res=>{},
                (err)=>console.log(err),
                ()=>this.playerProviderService.updatePlayerInfoByHttp()
            );
    }

    getLocations(): Location[]{
        return this.locations;
    }

    updateLocationListByHttp(): void {
        this.requestInvokerService.invokeGetRequest(this.locationsUrl)
            .subscribe(res=>{this.locations = res as Location[]},
                (err)=>console.log(err)
            );
    }
}