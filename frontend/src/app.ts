import 'angular-material';

import { AppController } from './AppController';
import Home from './home/home';

export default
    angular.module('chumpi-app', [ 'ngMaterial' ])
        .config(($mdThemingProvider) => {
             console.log("Config");
             $mdThemingProvider
                .theme('default')
                .primaryPalette('brown')
                .accentPalette('red');
         })
        .controller('AppController', AppController)
        .component(Home.name, Home.config);