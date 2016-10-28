import 'angular-animate';
import 'angular-aria';
import 'angular-material';
import 'angular-route';

import Applist from './applist/pi-applist';
import Home from './home/pi-home';

let requires = [
    'ngMaterial',
    'ngRoute'
];

let configTheme = ($mdIconProvider, $mdThemingProvider) => {
    $mdIconProvider
        .icon('apps', './assets/svg/apps.svg', 48)
        .icon('home', './assets/svg/home.svg', 48);
    $mdThemingProvider
        .theme('default')
        .primaryPalette('brown')
        .accentPalette('red');
};

let configRoutes = ($routeProvider) => {
    $routeProvider
        .when('/', { template: '<pi-home></pi-home>', hideToolbar: true })
        .when('/apps', { template: '<pi-applist></pi-applist>' });
};

let initRootScope = ($rootScope: ng.IScope, $location: ng.ILocationService) => {
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
        $rootScope['showToolbar'] = !current.$$route.hideToolbar;
    });

    $rootScope['goHome'] = () => {
        $location.path('/');
    }
};

export default
    angular.module('chumpi-app', requires)
           .config(configTheme)
           .config(configRoutes)
           .component(Applist.name, Applist.config)
           .component(Home.name, Home.config)
           .run(initRootScope);