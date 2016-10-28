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

let configTheme = ($mdThemingProvider) => {
    $mdThemingProvider
        .theme('default')
        .primaryPalette('brown')
        .accentPalette('red');
};

let configRoutes = ($routeProvider) => {
    $routeProvider
        .when('/', { template: '<pi-home></pi-home>' })
        .when('/apps', { template: '<pi-applist></pi-applist>' });
};

export default
    angular.module('chumpi-app', requires)
           .config(configTheme)
           .config(configRoutes)
           .component(Applist.name, Applist.config)
           .component(Home.name, Home.config);