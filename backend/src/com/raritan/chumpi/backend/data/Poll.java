package com.raritan.chumpi.backend.data;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.raritan.chumpi.backend.data.provider.PollRepository;

public class Poll {
	
	public class Answer {
		final int index;
		final String answer;
		int votes;
		public Answer(int index, String answer, int votes) {
			this.index = index;
			this.answer = answer;
			this.votes = votes;
		}
		@Override
		public boolean equals(Object obj) {
			if (! (obj instanceof Answer))
				return false;
			Answer other = (Answer) obj;
			return other.index == index && other.answer.equals(answer);
		}
		@Override
		public int hashCode() {
			return index * 31 + answer.hashCode();
		}

		@Override
		public String toString() {
			String className = this.getClass().getSimpleName();
			return String.format("%s(index=%d, answer=%s, votes=%d)", className, index, answer.substring(0, Math.min(30, answer.length())), votes);
		}
	}

	private static int latestId = 0;
	private final int pollId;
	private final String question;
	private final Set<Answer> answers = new HashSet<>();
	private final boolean multipleChoice;
	private final LocalDate dueDate;
	
	public Poll(String question, boolean multipleChoice, String ... answers) {
		this(question, multipleChoice, LocalDate.MAX, answers);
	}
	
	public Poll(String question, boolean multipleChoice, LocalDate dueDate, String ... answers) {
		this(latestId++, question, multipleChoice, dueDate, answers);
	}
	
	public Poll(int id, String question, boolean multipleChoice, LocalDate dueDate, String ... answers) {
		this.pollId = id;
		this.question = question;
		this.multipleChoice = multipleChoice;
		this.dueDate = dueDate;
		for (String a : answers) {
			this.answers.add(new Answer(this.answers.size(), a, 0));
		}
	}
	
	public Set<Answer> getAnswers() {
		return Collections.unmodifiableSet(answers);
	}

	public void voteForAnswer(int index) {
		if (index < answers.size()) {
			Answer answer = answers.stream().filter(ans -> ans.index == index).
					collect(Collectors.toList()).get(0);
			answer.votes++;
		}
		PollRepository.INSTANCE.update(this);
	}
	
	public String getQuestion() {
		return question;
	}
	
	public int getId() {
		return pollId;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Poll))
			return false;
		Poll other = (Poll) obj;
		
		return other.pollId == pollId &&
				other.question.equals(question) &&
				other.multipleChoice == multipleChoice &&
				other.dueDate.equals(dueDate) &&
				other.answers.equals(answers);
	}
	
	public Set<Answer> getVotes() {
		return Collections.unmodifiableSet(answers);
	}
	
	public String toText() {
		StringBuilder sb = new StringBuilder("Poll '" + question + "'");
		for (Answer vote : answers) {
			sb.append("\n  " + vote.answer + " (" + vote.votes + ")");
		}
		return sb.toString();
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	@Override
	public String toString() {
		String className = this.getClass().getSimpleName();
		return String.format("%s(pollId=%d, question=%s, multipleChoice=%b, dueDate=%s)", className, pollId, question.substring(0, Math.min(30, question.length())), multipleChoice, dueDate.toString());
	}
}
