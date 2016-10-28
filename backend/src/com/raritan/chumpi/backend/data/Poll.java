package com.raritan.chumpi.backend.data;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Poll {

	private static int latestId = 0;
	private final int pollId;
	private final String question;
	private final Map<String, Integer> answerVoteMap = new HashMap<>();
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
			answerVoteMap.put(a, 0);
		}
	}
	
	public Set<String> getAnswers() {
		return answerVoteMap.keySet();
	}

	public void voteForAnswer(String text) {
		int votes = answerVoteMap.get(text);
		answerVoteMap.put(text, votes + 1);
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
	
	public Map<String, Integer> getVotes() {
		return Collections.unmodifiableMap(answerVoteMap);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Poll '" + question + "'");
		return sb.toString();
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
	
}
