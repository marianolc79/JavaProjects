package test.service;

import test.dao.DAOA;
import test.dao.DAOB;

public class ServiceA {
	private DAOA daoA;
	private DAOB daoB;

	public DAOA getDaoA() {
		return daoA;
	}

	public void setDaoA(DAOA daoA) {
		this.daoA = daoA;
	}

	public DAOB getDaoB() {
		return daoB;
	}

	public void setDaoB(DAOB daoB) {
		this.daoB = daoB;
	}

}
