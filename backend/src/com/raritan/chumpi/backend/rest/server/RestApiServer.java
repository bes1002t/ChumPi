package com.raritan.chumpi.backend.rest.server;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;;

public class RestApiServer {

	private final static int PORT = 8080;
	private Server server;

	public RestApiServer(ResourceConfig config) {
		server = new Server(PORT);

		ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.SESSIONS);
		server.setHandler(ctx);

		initContext(ctx);

		addRestServlet(ctx, config);
		addDefaultServlet(ctx);
		setErrorHandler(ctx);
	}

	public void start() {
		try {
			server.start();
		} catch (Exception e) {
			server.destroy();
			e.printStackTrace();
		}
	}

	public void stop() {
		server.destroy();
	}

	private void initContext(ServletContextHandler ctx) {
		ClassLoader cl = DefaultServlet.class.getClassLoader();
		URL url = cl.getResource("com/raritan/chumpi/frontend");
		if (url == null) {
			throw new RuntimeException("Unable to find resource directory");
		}

		try {
			ctx.setBaseResource(Resource.newResource(url.toURI()));
			ctx.setContextPath("/");
			ctx.setWelcomeFiles(new String[] { "/index.html" });
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private void addRestServlet(ServletContextHandler ctx, ResourceConfig config) {
		ctx.addServlet(new ServletHolder(new ServletContainer(config)), "/rest/*");
	}

	private void addDefaultServlet(ServletContextHandler ctx) {
		ServletHolder defaultServlet = ctx.addServlet(DefaultServlet.class, "/*");
		defaultServlet.setInitParameter("dirAllowed", "false");
	}

	private void setErrorHandler(ServletContextHandler ctx) {
		ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
		errorHandler.addErrorPage(404, "/error.html");
		ctx.setErrorHandler(errorHandler);
	}
}
