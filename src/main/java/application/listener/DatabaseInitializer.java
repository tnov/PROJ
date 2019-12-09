package application.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lib.database.ConnectionManager;

public class DatabaseInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContextListener.super.contextInitialized(sce);
		ConnectionManager.getInstance().addConnection("empdb","jdbc:postgresql://localhost:5432/empdb", "empuser","empuser");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ConnectionManager.getInstance().destroy();
		ServletContextListener.super.contextDestroyed(sce);
	}

}
