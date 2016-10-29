interface Option {
    title: string;
    votes: number;
}

class PollsController {
    title = "Welches Bier magst Du am liebsten?";
    options: Option[] = [
        { title: "Beck's", votes: 3 },
        { title: "Fiedler", votes: 7 },
        { title: "Kozel", votes: 5 },
        { title: "Schöfferhofer", votes: 4 },
        { title: "Zwickl", votes: 6 },
    ];
    selected: string;
    isShowResults: boolean;
    totalVotes: number;

    vote() {
        this.showResults();
    }

    showResults() {
        this.totalVotes = 0;
        for (let option of this.options) {
            this.totalVotes += option.votes;
        }
        this.isShowResults = true;
    }

    back() {
        this.isShowResults = false;
        this.selected = null;
    }

    getPercent(option: Option) {
        if (!this.totalVotes) return 0;
        return option.votes / this.totalVotes * 100;
    }
}

export default {
    name: 'piPolls',
    config: {
        templateUrl: 'src/polls/pi-polls.html',
        controller: PollsController
    }
};
