package com.raritan.chumpi.backend.rest.server;

import org.glassfish.jersey.server.ResourceConfig;

import com.raritan.chumpi.backend.rest.accessors.CalendarCtrl;
import com.raritan.chumpi.backend.rest.accessors.CiCtrl;
import com.raritan.chumpi.backend.rest.accessors.CoffeeStatsCtrl;
import com.raritan.chumpi.backend.rest.accessors.DilbertComicCtrl;
import com.raritan.chumpi.backend.rest.accessors.EventChannel;
import com.raritan.chumpi.backend.rest.accessors.GalleryCtrl;
import com.raritan.chumpi.backend.rest.accessors.GolemNewsCtrl;
import com.raritan.chumpi.backend.rest.accessors.HeiseNewsCtrl;
import com.raritan.chumpi.backend.rest.accessors.IssueTrackerCtrl;
import com.raritan.chumpi.backend.rest.accessors.MotdCtrl;
import com.raritan.chumpi.backend.rest.accessors.PollCtrl;
import com.raritan.chumpi.backend.rest.accessors.RepoCtrl;
import com.raritan.chumpi.backend.rest.accessors.UserCtrl;
import com.raritan.chumpi.backend.rest.accessors.UserSettingsCtrl;
import com.raritan.chumpi.backend.rest.accessors.WeatherCtrl;
import com.raritan.chumpi.backend.rest.accessors.XkcdComicCtrl;

public class RestConfigurator extends ResourceConfig {

	public RestConfigurator() {
		register(GsonWriter.class);
		register(GsonReader.class);
		register(new CalendarCtrl());
		register(new CiCtrl());
		register(new CoffeeStatsCtrl());
		register(new DilbertComicCtrl());
		register(EventChannel.getInstance());
		register(new GalleryCtrl());
		register(new GolemNewsCtrl());
		register(new HeiseNewsCtrl());
		register(new IssueTrackerCtrl());
		register(new MotdCtrl());
		register(new PollCtrl());
		register(new RepoCtrl());
		register(new UserCtrl());
		register(new UserSettingsCtrl());
		register(new UserSettingsCtrl());
		register(new WeatherCtrl());
		register(new XkcdComicCtrl());
	}
}
