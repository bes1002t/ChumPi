declare class EventSource {
    constructor(url: string);
    onmessage: (event: { data: string }) => void;
}

export class EventService {

    private eventSource: EventSource;

    constructor(private $mdToast) {}

    public start() {
        console.log("Starting event service ...");
        this.eventSource = new EventSource("/rest/sse/listen");
        this.eventSource.onmessage = event => {
            if (event && event.data){
                let m = event.data.match("keypress:([0-9]+)");
                if (m) this.onKeyPress(+m[1]);
            }
        };
    }

    private onKeyPress(key: number) {
        let toast = this.$mdToast.simple()
            .textContent(`Keypress: ${key}`);
        this.$mdToast.show(toast);
    }

}
