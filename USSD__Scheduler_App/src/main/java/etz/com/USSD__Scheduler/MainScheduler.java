package etz.com.USSD__Scheduler;

import org.quartz.JobBuilder;
import java.io.FileReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MainScheduler {

	private static Properties properties;

	public static void main(String[] args) throws SchedulerException, ParseException, SQLException {

		getScheduler();

		USSD_Logger ussd_history_data = new USSD_Logger();

		ussd_history_data.history_u_logger();
		System.out.println(ussd_history_data.history_u_logger());

	}

	public static void getScheduler() throws SchedulerException {
		try (FileReader reader = new FileReader("cfg\\config.properties")) {
		
			properties = new Properties();
			properties.load(reader);

		} catch (Exception e) {

			e.printStackTrace();

		}

		JobDetail j = JobBuilder.newJob(USSD_Logger.class).build();

		Trigger t = TriggerBuilder.newTrigger().withIdentity("CroneTrigger")
				.withSchedule(CronScheduleBuilder.cronSchedule(properties.getProperty("CRON_JOB_TIME"))).build();


		Scheduler s = StdSchedulerFactory.getDefaultScheduler();
		s.start();
		s.scheduleJob(j, t);

	}

}
