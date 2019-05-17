package test;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import test.service.ServiceB;

class AnnotationConfigTest {
	private ApplicationContext ctx = null;

	@BeforeEach
	void setUp() throws Exception {
		ctx = new AnnotationConfigApplicationContext(Config.class);
	}

	@Test
	void test() {
		ServiceB srv = ctx.getBean(ServiceB.class);
		Assert.assertNotNull(srv.getDaoC1());
		Assert.assertNotNull(srv.getDaoC2());
		Assert.assertNotNull(srv.getDaoD());
	}

}
