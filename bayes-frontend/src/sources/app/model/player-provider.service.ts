import { Player } from './player';
import { Injectable } from '@angular/core';
import {RequestInvokerService} from "../http/request-invoker.service";

@Injectable()
export class PlayerProviderService{
    player:Player;
    private playerGetUrlPart1 = '/player/';
    private playerGetUrlPart2 = '/info/';
    private playerCreateUrl = '/player/create/';
    private playerNextTurn = '/player/nextTurn/';

    constructor(private requestInvokerService: RequestInvokerService) {
        this.player = { name: '', location: 'City', weather: 'Sunny', age: 0, money: 0 };
    }

    getPlayer(): Player{
        return this.player;
    }

    createNewPlayer(): void {
        this.requestInvokerService.invokePostRequest(this.playerCreateUrl, this.player)
            .subscribe(res=>{this.player = res as Player},
                err=>console.log(err)
            );
    }

    nextTurn(): void {
        this.requestInvokerService.invokePostRequest(this.playerNextTurn, this.player)
            .subscribe(res=>{this.updatePlayerInfoByHttp(); console.log(res)},
                err=>console.log(err)
            );
    }

    updatePlayerInfoByHttp(): void {
        console.error("UPDATE");
        var url: string = this.playerGetUrlPart1 + this.player.name + this.playerGetUrlPart2;

        this.requestInvokerService.invokeGetRequest(url)
            .subscribe(res=>{this.player = res as Player},
            err=>console.log(err)
        );
    }

}