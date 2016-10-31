class ManageController {
    headline: string;
    message: string;

    constructor($scope: ng.IScope) {
        $.getJSON('/rest/motd/get', data => {
            this.headline = data.headline;
            this.message = data.message;
            $scope.$applyAsync();
        });
    }

    clearMotd() {
        $.ajax({
            url: '/rest/motd/clear',
            type: 'POST',
        });
    }

    saveMotd() {
        $.ajax({
            url: '/rest/motd/set',
            type: 'POST',
            data: {
                headline: this.headline,
                message: this.message
            }
        });
    }
}

export default {
    name: 'piManage',
    config: {
        templateUrl: 'src/manage/pi-manage.html',
        controller: ManageController
    }
};
