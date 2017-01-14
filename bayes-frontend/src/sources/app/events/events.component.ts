import { Component } from '@angular/core';
import {Event} from "../model/event";
import {EventProviderService} from "../model/event-provider.service";

@Component({
    selector: 'events',
    template: require('./events.html')
})
export class EventComponent {
    constructor(
        private eventProviderService: EventProviderService){
    }

    getEvents(): Event[]{
        return this.eventProviderService.getEvents();
    }
}