import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Player } from '../model/player';
import { PlayerProviderService } from '../model/player-provider.service';

@Component({
    selector: 'start-menu',
    template: require('./start-menu.component.html')
})
export class StartMenuComponent {
    constructor(private playerProviderService: PlayerProviderService,
                private router: Router) {
    }

    startGame():void {
        if (this.canStartGame()) {
            this.playerProviderService.createNewPlayer();
            let link = ['/game'];
            this.router.navigate(link);
        }
    }

    canStartGame():boolean {
        let playerName = this.playerProviderService.getPlayer().name;
        return playerName != null && playerName != '';
    }
}