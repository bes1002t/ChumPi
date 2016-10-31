import 'angular-animate';
import 'angular-aria';
import 'angular-material';
import 'angular-route';

import * as jquery from 'jquery';
window['$'] = jquery;

import { EventService } from './events/EventService';

import Applist from './applist/pi-applist';
import CoffeeStats from './coffee/pi-coffee-stats';
import Home from './home/pi-home';
import Manage from './manage/pi-manage';
import Polls from './polls/pi-polls';
import PollManager from './manage/pi-poll-manager';
import Xkcd from './comics/pi-xkcd';

let requires = [
    'ngMaterial',
    'ngRoute'
];

let configTheme = ($mdIconProvider, $mdThemingProvider) => {
    $mdIconProvider
        .icon('apps', './assets/svg/apps.svg', 48)
        .icon('back', './assets/svg/back.svg', 48)
        .icon('coffee', './assets/svg/coffee.svg', 48)
        .icon('home', './assets/svg/home.svg', 48)
        .icon('poll', './assets/svg/poll.svg', 48);
    $mdThemingProvider
        .theme('default')
        .primaryPalette('brown')
        .accentPalette('red');
};

let configRoutes = ($routeProvider) => {
    $routeProvider
        .when('/', { template: '<pi-home></pi-home>', hideToolbar: true })
        .when('/apps', { template: '<pi-applist></pi-applist>', title: 'Applications' })
        .when('/coffee', { template: '<pi-coffee-stats></pi-coffee-stats>', title: 'Coffee Statistics' })
        .when('/games/2048', { templateUrl: 'src/games/2048.html', title: '2048' })
        .when('/manage', { template: '<pi-manage></pi-manage>', title: 'Management' })
        .when('/manage/polls', { template: '<pi-poll-manager></pi-poll-manager>', title: 'Manage Polls' })
        .when('/polls', { template: '<pi-polls></pi-polls>', title: 'Polls' })
        .when('/xkcd', { template: '<pi-xkcd></pi-xkcd>', title: 'XKCD' });
};

let initRootScope = ($rootScope: ng.IScope, $window: ng.IWindowService) => {
    let updateTime = () => {
        let pad = (n: number) => n < 10 ? `0${n}` : `${n}`;
        let now = new Date();
        let minutes = pad(now.getMinutes());
        let seconds = pad(now.getSeconds());
        $rootScope['time'] = `${now.getHours()}:${minutes}:${seconds}`;
        $rootScope['date'] = now.toLocaleDateString();
        $rootScope.$applyAsync();
        setTimeout(() => updateTime(), 1000);
    }
    updateTime();

    $rootScope.$on('$routeChangeSuccess', (_event, current, previous) => {
        if (!current) return;
        $rootScope['showToolbar'] = !current.$$route.hideToolbar;
        $rootScope['title'] = current.$$route.title;
    });

    $rootScope['back'] = () => {
        $window.history.back();
    }
};

let startEventing = (eventService: EventService) => {
    eventService.start();
}

export default
    angular.module('chumpi-app', requires)
           .config(configTheme)
           .config(configRoutes)
           .service("eventService", EventService)
           .component(Applist.name, Applist.config)
           .component(CoffeeStats.name, CoffeeStats.config)
           .component(Home.name, Home.config)
           .component(Manage.name, Manage.config)
           .component(Polls.name, Polls.config)
           .component(PollManager.name, PollManager.config)
           .component(Xkcd.name, Xkcd.config)
           .run(initRootScope)
           .run(startEventing);
