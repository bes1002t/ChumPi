package com.raritan.chumpi.backend.rest.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;;

public class RestApiServer {

	private final static int PORT = 28080;
	private Server server;

	private String baseDir;
	private String[] welcomePagePath;
	private String errorPagePath;
	private String ctxPath = "/";


	public RestApiServer(ServerConfig serverConfig, ResourceConfig resConfig) {
		this.baseDir = serverConfig.getBaseDir();
		this.errorPagePath = serverConfig.getErrorPagePath();
		this.welcomePagePath = new String[] { serverConfig.getWelcomePagePath() };

		server = new Server(PORT);
		ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.SESSIONS);
		server.setHandler(ctx);

		initContext(ctx);

		addRestServlet(ctx, resConfig);
		addDefaultServlet(ctx);
		setErrorHandler(ctx);
	}

	public void start() {
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
			server.destroy();
		}
	}

	public void stop() {
		server.destroy();
	}

	private void initContext(ServletContextHandler ctx) {
		ctx.setResourceBase(baseDir);
		ctx.setContextPath(ctxPath);
		ctx.setWelcomeFiles(welcomePagePath);
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
		errorHandler.addErrorPage(404, errorPagePath);
		ctx.setErrorHandler(errorHandler);
	}
}
