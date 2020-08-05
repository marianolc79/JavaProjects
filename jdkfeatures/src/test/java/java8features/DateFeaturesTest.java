package java8features;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

import org.junit.Test;

public class DateFeaturesTest {
	@Test
	public void testFeatures() {

		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		LocalDateTime dateTime = LocalDateTime.now();
		ZonedDateTime zoned = ZonedDateTime.now();

		Set<String> allZoneIds = ZoneId.getAvailableZoneIds();

		allZoneIds.stream().forEach(x -> System.out.println(x));
		int i = 0;
	}
}
