package tdd;

import java.util.Date;

public class DateHelperMock implements IDateHelper {

	private Date date;

	public DateHelperMock(Date date) {
		this.date = date;
	}

	@Override
	public Date getDate() {
		return date;
	}

}
