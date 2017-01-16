import {Injectable} from "@angular/core";
import {RequestInvokerService} from "../http/request-invoker.service";
import {Score} from "./score";

@Injectable()
export class HighscoreProviderService {
    private getHighscoresUrl = '/highscores';
    private requestSent = false;
    private scores = [];

    constructor(private requestInvokerService: RequestInvokerService){
    }

    getHighscores(): Score[]{
        this.reloadHighscores();
        return this.scores;
    }

    reloadHighscores(): void{
        if(this.requestSent == false){
            this.requestSent = true;
            var url: string = this.getHighscoresUrl;
            this.requestInvokerService.invokeGetRequest(url)
                .subscribe(res => {
                        this.scores = res as Score[];
                        this.scores.sort((a: any, b: any) => {
                            if (a.money > b.money) {
                                return -1;
                            } else if (a.money < b.money) {
                                return 1;
                            } else {
                                return 0;
                            }
                        });
                    },
                    err => console.log(err)
                );
        }
    }

}