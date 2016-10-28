class HomeController {
    now: string;
    timer: number;

    constructor(private $scope: ng.IScope,
                private $location: ng.ILocationService)
    {
        this.updateTime();
        $scope.$on('$destroy', () => {
            clearTimeout(this.timer);
        })
    }

    private updateTime() {
        let now = new Date();
        this.now = `${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}`;
        this.$scope.$applyAsync();
        this.timer = setTimeout(() => this.updateTime(), 1000);
    }

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
