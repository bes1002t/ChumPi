class XkcdController {
    url: string;

    constructor() {
        $.ajax("/rest/xkcd/latest").then(url => {
            this.url = url;
        });
    }
}

export default {
    name: 'piXkcd',
    config: {
        templateUrl: 'src/comics/pi-xkcd.html',
        controller: XkcdController
    }
};
