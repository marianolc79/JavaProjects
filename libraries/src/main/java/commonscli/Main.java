package commonscli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
	public static void main(String[] args) throws ParseException {
		// create Options object
		Options options = new Options();

		// add t option
		options.addRequiredOption("w", "way", true, "way of migration (forward/backward)");
		options.addRequiredOption("t", "table", true, "table (notifications/subscriptions)");
		try {

			CommandLineParser parser = new DefaultParser();
			CommandLine cmd = parser.parse(options, args);
		} catch (ParseException e) {
			// automatically generate the help statement
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("main", options);
		}
	}
}
