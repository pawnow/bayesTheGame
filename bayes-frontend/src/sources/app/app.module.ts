import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AppComponent} from "./app.component";
import {AppRoutingModule} from "./app-routing.module";
import {PlayerProviderService} from "./model/player-provider.service";
import {StartMenuComponent} from "./startMenu/start-menu.component";
import {GameMenuComponent} from "./gameMenu/game-menu.component";
import {InsuranceProviderService} from "./model/insurance-provider.service";
import {PlayerInsuranceProviderService} from "./model/player-insurance-provider.service";
import {BuyInsuranceComponent} from "./insurances/buy-insurance.component";
import {PlayerInsuranceComponent} from "./insurances/player-insurance.component";
import {PlayerInfoComponent} from "./playerInfo/player-info.component";
import {LocationsProviderService} from "./model/locations-provider.service";
import {TravelComponent} from "./travel/travel.component";
import {RequestInvokerService} from "./http/request-invoker.service";
import {TurnService} from "./turnService/turn.service";
import {InitializationService} from "./turnService/initialization.service";

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
        TravelComponent
    ],
    providers: [
        PlayerProviderService,
        InsuranceProviderService,
        LocationsProviderService,
        RequestInvokerService,
        TurnService,
        InitializationService,
        PlayerInsuranceProviderService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}