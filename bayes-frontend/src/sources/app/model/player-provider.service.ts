import { Player } from './player';
import { Injectable } from '@angular/core';
import { RequestInvokerService } from "../http/request-invoker.service";
import { InitializationService } from "../turnService/initialization.service";

@Injectable()
export class PlayerProviderService{
    player:Player;
    private playerGetUrlPart1 = '/player/';
    private playerGetUrlPart2 = '/info/';
    private playerCreateUrl = '/player/create/';

    constructor(private requestInvokerService: RequestInvokerService,
                private initializationService: InitializationService) {
        this.player = { name: '', location: 'City', weather: 'Sunny', age: 0, money: 0 };
    }

    getPlayer(): Player{
        return this.player;
    }

    createNewPlayer(): void {
        this.requestInvokerService.invokePostRequest(this.playerCreateUrl, this.player)
            .subscribe(res=>{this.player = res as Player},
                err=>console.log(err),
                () => {
                    this.initializationService.initialize(this.player.name);
                }
            );
    }

    updatePlayerInfoByHttp(): void {
        var url: string = this.playerGetUrlPart1 + this.player.name + this.playerGetUrlPart2;

        this.requestInvokerService.invokeGetRequest(url)
            .subscribe(res=>{this.player = res as Player},
            err=>console.log(err)
        );
    }

}