import { Component } from '@angular/core';
import {Score} from "../model/score";
import {PlayerProviderService} from "../model/player-provider.service";
import {HighscoreProviderService} from "../model/highscores-provider.service";

@Component({
    selector: 'highscores',
    template: require('./highscores.html')
})
export class HighscoresComponent {

    constructor(private highscoreProviderService: HighscoreProviderService,
                private playerProviderService: PlayerProviderService){
    }

    getHighscores(): Score[]{
        return this.highscoreProviderService.getHighscores();
    }

    getPlayerMoney(): number{
        return this.playerProviderService.getPlayer().money;
    }
}