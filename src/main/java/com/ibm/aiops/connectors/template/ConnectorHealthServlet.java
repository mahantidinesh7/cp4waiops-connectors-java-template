package com.ibm.aiops.connectors.template;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/h/*")
public class ConnectorHealthServlet extends HttpServlet {
    static final Logger logger = Logger.getLogger(ConnectorHealthServlet.class.getName());

    @Inject
    ManagerInstance instance;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String subPath = req.getPathInfo();
        if (subPath == null) {
            super.doGet(req, resp);
            return;
        }

        switch (req.getPathInfo()) {
        case "/live":
            instance.getConnectorManager().handleLiveRequest(req, resp);
            break;
        case "/ready":
            instance.getConnectorManager().handleReadyRequest(req, resp);
            break;
        case "/metrics":
            instance.getConnectorManager().handleMetricsRequest(req, resp);
            break;
        default:
            logger.info("hello world!");
            super.doGet(req, resp);
        }
    }
}