package etz.com.USSD__Scheduler;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class USSD_Logger implements Job {

	private static Properties properties;

	static PreparedStatement statement;

	private static String sql;
	private static String start__date;
	private static String end__date;
	private static String incremented_date;

	static Connection con = DBConnection.dbConnection();

	public USSD_Logger() {

		try (FileReader reader = new FileReader("cfg\\config.properties")) {

			properties = new Properties();
			properties.load(reader);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static void main(String[] Args) throws ParseException, SQLException {

		history_u_logger();

	}

	public void execute(JobExecutionContext context) throws JobExecutionException {

		try {

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate_dateFormat1 = new Date();
			String startDate = df.format(startDate_dateFormat1);
			System.out.println("StartDate1" + " " + startDate);

			Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
			Calendar c = Calendar.getInstance();
			// c.setTime(startDate_dateFormat);
			c.setTime(date1);
			// c.add(Calendar.HOUR, -24);
			c.add(Calendar.DAY_OF_MONTH, -1);
			c.getTime();
			String formatted = df.format(c.getTime());

			System.out.println(formatted);

			System.out.println(ussd_u_logger(formatted));

			System.out.println("Scheduler in working mode");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static String ussd_u_logger(String newDate) throws ParseException, SQLException {

		try {
			System.out.println("Date received::" + "     " + newDate);

			try (FileReader reader = new FileReader("cfg\\config.properties")) {
				Properties properties = new Properties();
				properties.load(reader);
				sql = properties.getProperty("sql");

			} catch (Exception e) {
				e.printStackTrace();
			}

			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(newDate);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			String startDate = dateFormat.format(date);

			Date date1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00").parse(startDate);

			System.out.println("StartDate1" + " " + startDate);

			Calendar c = Calendar.getInstance();
			// c.setTime(startDate_dateFormat);
			c.setTime(date1);
			c.add(Calendar.HOUR, 6);

			Date endDate_dateFormat1 = c.getTime();
			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
			String endDateString = df2.format(endDate_dateFormat1);

			System.out.println("endDate1 " + endDateString);

			Date startDate_dateFormat2 = endDate_dateFormat1;
			System.out.println("StartDate2" + " " + df2.format(startDate_dateFormat2));
			Calendar c2 = Calendar.getInstance();
			// c.setTime(startDate_dateFormat2);
			c2.setTime(startDate_dateFormat2);
			c2.add(Calendar.HOUR, 6);

			Date endDate_dateFormat2 = c2.getTime();
			DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
			String endDateString2 = df3.format(endDate_dateFormat2);
			System.out.println("endDate2 " + endDateString2);

			Date startDate_dateFormat3 = endDate_dateFormat2;
			System.out.println("StartDate3" + " " + df2.format(startDate_dateFormat3));
			Calendar c3 = Calendar.getInstance();
			// c.setTime(date1);
			c3.setTime(startDate_dateFormat3);
			c3.add(Calendar.HOUR, 6);

			Date endDate_dateFormat3 = c3.getTime();
			DateFormat df4 = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
			String endDateString3 = df4.format(endDate_dateFormat3);
			System.out.println("endDate3 " + endDateString3);

			Date startDate_dateFormat4 = endDate_dateFormat3;
			System.out.println("StartDate3" + " " + df3.format(startDate_dateFormat4));
			Calendar c4 = Calendar.getInstance();
			// c.setTime(date1);
			c4.setTime(startDate_dateFormat4);
			c4.add(Calendar.HOUR, 6);

			Date endDate_dateFormat4 = c4.getTime();
			DateFormat df5 = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
			String endDateString4 = df5.format(endDate_dateFormat4);
			System.out.println("endDate4 " + endDateString4);

			String failed = "06";
			try {

				PreparedStatement statement1 = con.prepareStatement(sql);
				statement1.setString(1, startDate);
				statement1.setString(2, endDateString);
				statement1.executeUpdate();

			} catch (Exception e) {
				System.out.println(e);

				String sql2 = "INSERT INTO history_u_logger_db(start_date, end_date, status) VALUES(?,?,?)";
				statement = con.prepareStatement(sql2);
				statement.setString(1, start__date);
				statement.setString(2, endDateString);
				statement.setString(3, failed);
				System.out.println(sql2);
				statement.executeUpdate();
				return failed;
			}

			try {

				PreparedStatement statement2 = con.prepareStatement(sql);
				statement2.setString(1, endDateString);
				statement2.setString(2, endDateString2);
				statement2.executeUpdate();

			} catch (Exception e) {
				System.out.println(e);

				String sql2 = "INSERT INTO history_u_logger_db(start_date, end_date, status) VALUES(?,?,?)";
				statement = con.prepareStatement(sql2);
				statement.setString(1, start__date);
				statement.setString(2, endDateString2);
				statement.setString(3, failed);
				System.out.println(sql2);
				statement.executeUpdate();
				return failed;
			}

			try {

				PreparedStatement statement3 = con.prepareStatement(sql);
				statement3.setString(1, endDateString2);
				statement3.setString(2, endDateString3);
				statement3.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);

				String sql2 = "INSERT INTO history_u_logger_db(start_date, end_date, status) VALUES(?,?,?)";
				statement = con.prepareStatement(sql2);
				statement.setString(1, start__date);
				statement.setString(2, endDateString3);
				statement.setString(3, failed);
				System.out.println(sql2);
				statement.executeUpdate();
				return failed;
			}

			try {
				PreparedStatement statement4 = con.prepareStatement(sql);
				statement4.setString(1, endDateString3);
				statement4.setString(2, endDateString4);
				statement4.executeUpdate();

				// System.out.println("SQL4::"+" "+statement4.toString());

				// }

			} catch (Exception e) {
				System.out.println(e);

				String sql2 = "INSERT INTO history_u_logger_db(start_date, end_date, status) VALUES(?,?,?)";
				statement = con.prepareStatement(sql2);
				statement.setString(1, start__date);
				statement.setString(2, endDateString4);
				statement.setString(3, failed);
				System.out.println(sql2);
				statement.executeUpdate();
				return failed;
			}

			return "00";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "06";
	}

	public static String history_u_logger() throws ParseException, SQLException {

		try (FileReader reader = new FileReader("cfg\\config.properties")) {
			Properties properties = new Properties();
			properties.load(reader);
			start__date = properties.getProperty("start__date");
			end__date = properties.getProperty("end__date");

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(end__date);
		String result = ussd_u_logger(start__date);
		System.out.println(result);
		// for ()

		Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(start__date);
		Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end__date);

//		  for (Date date = date1; date.before(date2); date = ((Date) date).plusDays(1))
//		  {

		// (date1.compareTo(date2) < 0) {
		Calendar c = Calendar.getInstance();
		// c.setTime(startDate_dateFormat);
		c.setTime(date1);

		Calendar c2 = Calendar.getInstance();
		// c.setTime(startDate_dateFormat);
		c2.setTime(date2);

		for (Calendar date = c; date.before(c2); date.add(Calendar.DATE, 1)) {

			if (ussd_u_logger(start__date).equals("00")) {

				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// c.add(Calendar.HOUR, -24);
				c.getTime();
				incremented_date = df.format(c.getTime());

				System.out.println(start__date);
				ussd_u_logger(incremented_date);
				start__date = incremented_date;

			}
		}

		return result;

	}

}
