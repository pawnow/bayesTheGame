import {Injectable} from "@angular/core";
import {Location} from "./location";
import {RequestInvokerService} from "../http/request-invoker.service";

@Injectable()
export class LocationsProviderService {
    locations: Location[];
    private locationsUrl = '/locations/';

    constructor(private requestInvokerService: RequestInvokerService) {
        this.locations = []
    }

    getLocations(): Location[] {
        return this.locations;
    }

    updateLocationListByHttp(): void {
        this.requestInvokerService.invokeGetRequest(this.locationsUrl)
            .subscribe(res => {
                    this.locations = res as Location[]
                },
                (err) => console.log(err)
            );
    }
}