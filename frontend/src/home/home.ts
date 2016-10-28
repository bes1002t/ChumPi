class HomeController {
    now: string;
    timer: number;

    constructor(private $scope: ng.IScope) {
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
}

export default {
    name: 'home',
    config: {
        templateUrl: 'src/home/home.html',
        controller: HomeController
    }
};
