import 'angular-material';
import 'angular-route';

import { AppController } from './AppController';
import Home from './home/home';

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
        .when('/', { template: '<home></home>' });
};

export default
    angular.module('chumpi-app', requires)
           .config(configTheme)
           .config(configRoutes)
           .controller('AppController', AppController)
           .component(Home.name, Home.config);