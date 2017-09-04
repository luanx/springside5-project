package com.wantdo.examples.bootapi.api.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Configuration
@Profile(Profiles.NOT_PRODUCTION)
public class H2ConsoleConfiguration implements ServletContextInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
       initH2Console(servletContext);
    }

    private void initH2Console(ServletContext servletContext) {
        ServletRegistration.Dynamic h2ConsoleServlet = servletContext.addServlet("H2Console",
                new WebServlet());
        h2ConsoleServlet.addMapping("/h2/*");
        h2ConsoleServlet.setInitParameter("-properties", "src/main/resources/");
        h2ConsoleServlet.setLoadOnStartup(1);
    }
}
