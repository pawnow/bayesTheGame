import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {Insurance} from "../model/insurance";
import {TurnService} from "../turnService/turn.service";

@Component({
    selector: 'game-menu',
    template: require('./game-menu.component.html')
})
export class GameMenuComponent {
    constructor(private router: Router,
                private turnService: TurnService) {
    }

    buyInsurance(insurance: Insurance): void {
        let link = ['/insurance'];
        this.router.navigate(link);
    }

    travel(): void {
        let link = ['/travel'];
        this.router.navigate(link);
    }

    itemShop(): void {
        let link = ['/shop'];
        this.router.navigate(link);
    }

    nextTurn(): void {
        this.turnService.nextTurn();
    }

}