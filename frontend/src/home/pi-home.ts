class HomeController {
    time: string;
    date: string;

    private timer: number;

    constructor(private $scope: ng.IScope,
                private $location: ng.ILocationService)
    {
        this.updateTime();
        $scope.$on('$destroy', () => {
            clearTimeout(this.timer);
        })
    }

    private updateTime() {
        let pad = (n: number) => n < 10 ? `0${n}` : `${n}`;
        let now = new Date();
        let minutes = pad(now.getMinutes());
        let seconds = pad(now.getSeconds());
        this.time = `${now.getHours()}:${minutes}:${seconds}`;
        this.date = now.toLocaleDateString();
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
