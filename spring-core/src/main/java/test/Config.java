package test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import test.dao.DAOC;
import test.dao.DAOD;

@Configuration
@ComponentScan(basePackages = { "test.service", "test.dao" })
public class Config {

	@Bean("daoC1")
	public DAOC getDAOC1() {
		return new DAOC();
	}

	@Bean("daoC2")
	public DAOC getDAOC2() {
		return new DAOC();
	}

	@Bean("daoD")
	@Primary
	public DAOD getDAOD() {
		return new DAOD();
	}

	@Bean("daoD2")
	public DAOD getDAOD2() {
		return new DAOD();
	}
}
