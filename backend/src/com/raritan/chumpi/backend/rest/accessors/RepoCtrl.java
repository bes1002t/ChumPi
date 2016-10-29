package com.raritan.chumpi.backend.rest.accessors;

import java.net.URI;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.raritan.chumpi.backend.data.CommitInfo;

@Path("/repo")
public class RepoCtrl {

	private URI baseUri;

	@GET
	@Path("/last-commit")
	public CommitInfo getLastCommit() {
		return null;
	}

	@GET
	@Path("/find")
	public List<CommitInfo> findCommits(@QueryParam("dir") String dir, @QueryParam("count") int count) {
		return null;
	}

	@GET
	@Path("/last-commits")
	public List<CommitInfo> getLastCommits(@QueryParam("count") int count) {
		return null;
	}
}
