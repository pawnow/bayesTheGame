import { Injectable } from '@angular/core';
import {Event} from "./event";

@Injectable()
export class EventProviderService{
    private events:Event[];

    constructor() {
        this.events = []
    }

    getEvents(): Event[]{
        return this.events;
    }

    addNewEvent(events: Event[]): void{
        this.events.push(...events);
    }
}