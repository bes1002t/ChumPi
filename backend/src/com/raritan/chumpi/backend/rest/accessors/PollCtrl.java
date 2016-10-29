package com.raritan.chumpi.backend.rest.accessors;

import java.util.List;
import java.util.Set;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.raritan.chumpi.backend.data.Poll;
import com.raritan.chumpi.backend.data.provider.PollRepository;
import com.raritan.chumpi.backend.exceptions.PollNotFoundException;

@Path("/polls")
public class PollCtrl {

	private PollRepository repo = PollRepository.INSTANCE;

	@GET
	@Path("/get")
	public Set<Poll> getPolls() {
		return repo.getPolls();
	}

	@POST
	@Path("/create")
	public Boolean createPoll(
			@FormParam("question") String question,
			@FormParam("multipleChoice") Boolean multipleChoice,
			@FormParam("dueDate") String dueDate,
			@FormParam("choice") List<String> choices
		) {
		repo.createPoll(question, multipleChoice, null, choices.toArray(new String[choices.size()]));

		return true;
	}

	@POST
	@Path("/edit")
	public Boolean editPoll(
			@FormParam("question") String question,
			@FormParam("multipleChoice") Boolean multipleChoice,
			@FormParam("dueDate") String dueDate,
			@FormParam("choice") List<String> choices
		) {
		return false;
	}

	@POST
	@Path("/remove")
	public Boolean removePoll(@FormParam("id") int id) {
		return false;
	}

	@POST
	@Path("/vote")
	public Boolean vote(@FormParam("pollId") int pollId, @FormParam("choiceIndex") int choiceIndex) {
		try {
			repo.getPoll(pollId).voteForAnswer(choiceIndex);
			return true;
		} catch (PollNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
}
