import 'angular';
import App from '../app';

angular
    .element(document)
    .ready(() => {
        angular.module('chumpi-app-bootstrap', [ App.name ])
            .run(() => { /*TODO*/ });
        let body = document.getElementsByTagName("body")[0];
        angular.bootstrap(body, [ 'chumpi-app-bootstrap' ]);
    });