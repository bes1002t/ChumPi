class ApplistController {
    constructor(private $location: ng.ILocationService) {}

    goTo(path: string) {
        this.$location.path(path);
    }
}

export default {
    name: 'piApplist',
    config: {
        templateUrl: 'src/applist/pi-applist.html',
        controller: ApplistController
    }
};
