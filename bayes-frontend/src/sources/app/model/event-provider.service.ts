import { Injectable } from '@angular/core';
import {Event} from "./event";
import { Router } from '@angular/router';

@Injectable()
export class EventProviderService{
    private events:Event[];

    constructor(private router:Router) {
        this.events = []
    }

    getEvents(): Event[]{
        return this.events;
    }

    addNewEvent(events: Event[]): void{
        this.events.push(...events);
        if(this.events.find(x => x.description == "You die") != undefined){
            let link = ['/highscores'];
            this.router.navigate(link);
        }
    }
}