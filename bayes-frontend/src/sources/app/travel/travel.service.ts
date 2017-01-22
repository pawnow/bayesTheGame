import {Injectable} from "@angular/core";
import {Location} from "../model/location";
import {RequestInvokerService} from "../http/request-invoker.service";
import {PlayerProviderService} from "../model/player-provider.service";

@Injectable()
export class TravelService {
private playerUrl = '/player/';
private postLocationsUrl = '/travel/';

constructor(private requestInvokerService: RequestInvokerService,
            private playerProviderService: PlayerProviderService) {}

    travel(location: Location, playerName: string): void {
        var url = this.playerUrl + playerName + this.postLocationsUrl;

        this.requestInvokerService.invokePostRequest(url, location)
            .subscribe(res => {
                },
                (err) => console.log(err),
                () => this.playerProviderService.updatePlayerInfoByHttp()
            );
    }
}