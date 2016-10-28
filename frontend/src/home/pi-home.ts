class HomeController {
    constructor(private $scope: ng.IScope,
                private $location: ng.ILocationService)
    {}

    openApps() {
        this.$location.path('/apps');
    }
}

export default {
    name: 'piHome',
    config: {
        templateUrl: 'src/home/pi-home.html',
        controller: HomeController
    }
};
