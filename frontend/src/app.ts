import 'angular-material';
import 'angular-route';

import { AppController } from './AppController';
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
        .when('/', { template: '<pi-home></pi-home>' });
};

export default
    angular.module('chumpi-app', requires)
           .config(configTheme)
           .config(configRoutes)
           .controller('AppController', AppController)
           .component(Home.name, Home.config);