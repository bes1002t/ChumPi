class HomeController {
    motd: {
        heading: string;
        lines: string[];
    };

    constructor(private $scope: ng.IScope,
                private $location: ng.ILocationService)
    {
        this.updateMotd();
    }

    private updateMotd() {
        $.getJSON('/rest/motd/get', data => {
            if (data) {
                this.motd = {
                    heading: data.headline,
                    lines: data.message.split("\n")
                };
            }
        });
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
