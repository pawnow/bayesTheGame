import { Injectable } from '@angular/core';

const PORT: string = '8081';
const HOST: string = 'localhost';
const ROOT_URL: string = 'http://' + HOST + ':' + PORT;

export class UrlBuilderService{
    static buildUrl(path: string): string{
        return ROOT_URL + path;
    }
}