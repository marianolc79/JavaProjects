package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import test.dao.DAOC;
import test.dao.DAOD;

@Component
public class ServiceB {

	@Autowired
	@Qualifier("daoC1")
	private DAOC daoC1;

	@Autowired
	@Qualifier("daoC2")
	private DAOC daoC2;

	@Autowired
	private DAOD daoD;

	public DAOC getDaoC1() {
		return daoC1;
	}

	public DAOC getDaoC2() {
		return daoC2;
	}

	public DAOD getDaoD() {
		return daoD;
	}

}
