import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {StartMenuComponent} from "./startMenu/start-menu.component";
import {GameMenuComponent} from "./gameMenu/game-menu.component";
import {TravelComponent} from "./travel/travel.component";
import {BuyInsuranceComponent} from "./insurances/buy-insurance.component";
import {HighscoresComponent} from "./highscores/highscores.component";

const routes: Routes = [
    {
        path: '',
        redirectTo: '/start',
        pathMatch: 'full'
    },
    {
        path: 'start',
        component: StartMenuComponent
    },
    {
        path: 'game',
        component: GameMenuComponent
    },
    {
        path: 'travel',
        component: TravelComponent
    },
    {
        path: 'insurance',
        component: BuyInsuranceComponent
    },
    {
        path: 'highscores',
        component: HighscoresComponent
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}