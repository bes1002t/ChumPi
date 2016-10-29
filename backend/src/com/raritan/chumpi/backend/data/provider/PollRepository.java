package com.raritan.chumpi.backend.data.provider;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import com.raritan.chumpi.backend.data.Poll;
import com.raritan.chumpi.backend.exceptions.PollNotFoundException;

public class PollRepository extends AbstractRepository<Poll> {

	public static final PollRepository INSTANCE = new PollRepository();
	
	private PollRepository() {}
	
	public Poll getPollByQuestion(String question) throws PollNotFoundException {
		for (Poll p : cache) {
			if (p.getQuestion().equals(question))
				return p;
		}
		throw new PollNotFoundException();
	}
	
	public Set<Poll> getPolls() {
		return cache.stream()	// some stream magic to filter for valid polls
				.filter(p -> LocalDate.now().isBefore(p.getDueDate())).collect(Collectors.toSet());
	}
	
	public Poll getPoll(int id) throws PollNotFoundException {
		for (Poll p : cache) {
			if (p.getId() == id)
				return p;
		}
		throw new PollNotFoundException();
	}
	
	public Poll createPoll(String question, boolean multipleChoice, String ... answers) {
		return createPoll(question, multipleChoice, LocalDate.MAX, answers);
	}
	
	public Poll createPoll(String question, boolean multipleChoice, LocalDate dueDate, String ... answers) {
		Poll p = new Poll(question, multipleChoice, dueDate, answers);
		cache.add(p);
		persist(p);
		return p;
	}

	@Override
	protected Class<Poll> getRepoType() {
		return Poll.class;
	}
}
