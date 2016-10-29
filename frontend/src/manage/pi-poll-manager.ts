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

class PollManagerController {
    question: string;
    answer1: string;
    answer2: string;
    answer3: string;
    answer4: string;
    answer5: string;
    answer6: string;

    create() {
        let choice = [
            this.answer1,
            this.answer2,
            this.answer3,
            this.answer4,
            this.answer5,
            this.answer6
        ].filter(answer => !!answer);

        $.ajax({
            url: '/rest/polls/create',
            type: 'POST',
            data: {
                question: this.question,
                multipleChoice: false,
                dueDate: "",
                choice: choice,
            }
        });
    }
}

export default {
    name: 'piPollManager',
    config: {
        templateUrl: 'src/manage/pi-poll-manager.html',
        controller: PollManagerController
    }
};
