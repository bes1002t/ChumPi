package com.raritan.chumpi.backend.data;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Poll {
	
	public class Answer {
		final int index;
		final String answer;
		public Answer(int index, String answer) {
			this.index = index;
			this.answer = answer;
		}
	}

	private static int latestId = 0;
	private final int pollId;
	private final String question;
	private final Map<Answer, Integer> answerVoteMap = new HashMap<>();
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
			answerVoteMap.put(new Answer(answerVoteMap.size(), a), 0);
		}
	}
	
	public Set<Answer> getAnswers() {
		return answerVoteMap.keySet();
	}

	public void voteForAnswer(int index) {
		if (index < answerVoteMap.size()) {
			Answer answer = answerVoteMap.keySet().stream().filter(ans -> ans.index == index).
					collect(Collectors.toList()).get(0);
			int votes = answerVoteMap.get(answer);
			answerVoteMap.put(answer, votes + 1);
		}
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
				other.answerVoteMap.equals(answerVoteMap);
	}
	
	public Map<Answer, Integer> getVotes() {
		return Collections.unmodifiableMap(answerVoteMap);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Poll '" + question + "'");
		for (Entry<Answer, Integer> vote : answerVoteMap.entrySet()) {
			sb.append("\n  " + vote.getKey().answer + " (" + vote.getValue() + ")");
		}
		return sb.toString();
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
	
}
