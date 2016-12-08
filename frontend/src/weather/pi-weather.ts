interface Answer {
    index: number;
    answer: string;
    votes: number;
}

interface Poll {
    pollId: number;
    question: string;
    answers: Answer[];
}

interface Option {
    title: string;
    votes: number;
}

class PollsController {
    poll: Poll;
    selected: string;
    isShowResults: boolean;
    totalVotes: number;

    constructor() {
        $.getJSON('/rest/polls/get', (polls: Poll[]) => {
            if (polls && polls.length) {
                this.poll = polls[polls.length - 1];
            } else {
                // Fake poll
                this.poll = {
                    pollId: 42,
                    question: "Welches Bier magst Du am liebsten",
                    answers: [
                        { index: 0, answer: "Beck's", votes: 3 },
                        { index: 1, answer: "Fiedler", votes: 7 },
                        { index: 2, answer: "Kozel", votes: 5 },
                        { index: 3, answer: "Schöfferhofer", votes: 4 },
                        { index: 4, answer: "Zwickl", votes: 6 }
                    ]
                };
            }
        });
    }

    vote() {
        let answer = this.poll.answers[+this.selected];
        ++answer.votes;

        if (this.poll.pollId) {
            $.ajax({
                url: '/rest/polls/vote',
                type: 'POST',
                data: {
                    pollId: this.poll.pollId,
                    choiceIndex: answer.index
                }
            });
        }

        this.showResults();
    }

    showResults() {
        this.totalVotes = 0;
        for (let answer of this.poll.answers) {
            this.totalVotes += answer.votes;
        }
        this.isShowResults = true;
    }

    back() {
        this.isShowResults = false;
        this.selected = null;
    }

    getPercent(answer: Answer) {
        if (!this.totalVotes) return 0;
        return answer.votes / this.totalVotes * 100;
    }
}

export default {
    name: 'piPolls',
    config: {
        templateUrl: 'src/polls/pi-polls.html',
        controller: PollsController
    }
};
