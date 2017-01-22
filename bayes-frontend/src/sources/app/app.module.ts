import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AppRoutingModule} from './app-routing.module';
import { PlayerProviderService } from './model/player-provider.service';
import { StartMenuComponent } from './startMenu/start-menu.component';
import { GameMenuComponent } from './gameMenu/game-menu.component';
import { InsuranceProviderService } from "./model/insurance-provider.service";
import { PlayerInsuranceProviderService } from "./model/player-insurance-provider.service";
import { BuyInsuranceComponent } from "./insurances/buy-insurance.component";
import { PlayerInsuranceComponent } from "./insurances/player-insurance.component";
import { PlayerInfoComponent } from "./playerInfo/player-info.component";
import { LocationsProviderService } from "./model/location-provider.service.ts";
import { TravelComponent } from "./travel/travel.component";
import {RequestInvokerService} from "./http/request-invoker.service";
import {TurnService} from "./turnService/turn.service";
import {InitializationService} from "./turnService/initialization.service";
import {EventProviderService} from "./model/event-provider.service";
import {EventComponent} from "./events/events.component";
import {HighscoresComponent} from "./highscores/highscores.component";
import {HighscoreProviderService} from "./model/highscores-provider.service";
import {TravelService} from "./travel/travel.service"

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule,
        HttpModule,
    ],
    declarations: [
        AppComponent,
        StartMenuComponent,
        GameMenuComponent,
        BuyInsuranceComponent,
        PlayerInsuranceComponent,
        PlayerInfoComponent,
        TravelComponent,
        EventComponent,
        HighscoresComponent
    ],
    providers: [
        PlayerProviderService,
        InsuranceProviderService,
        LocationsProviderService,
        TravelService,
        RequestInvokerService,
        TurnService,
        InitializationService,
        EventProviderService,
        PlayerInsuranceProviderService,
        HighscoreProviderService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}