import { Component } from '@angular/core';
import {PlayerProviderService} from "../model/player-provider.service";
import {Player} from "../model/player";

@Component({
    selector: 'player-info',
    template: require('./player-info.html')
})
export class PlayerInfoComponent {
    constructor(
        private playerProviderService: PlayerProviderService){
    }

    getPlayer(): Player{
        var player: Player = this.playerProviderService.getPlayer();
        if(player != undefined){
            return this.playerProviderService.getPlayer();
        }
        else{
            return new Player()
        }
    }
}