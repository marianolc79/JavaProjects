package test;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.service.ServiceA;

public class XMLConfigTest {
	private ApplicationContext context;

	@BeforeEach
	void init() {
		context = new ClassPathXmlApplicationContext("appContext.xml");
	}

	@Test
	void testScopePrototype() {
		ServiceA srvA1 = (ServiceA) context.getBean("serviceA");
		ServiceA srvA2 = (ServiceA) context.getBean("serviceA");
		Assert.assertNotSame(srvA1, srvA2);
	}

	@Test
	void testScopeSingleton() {
		ServiceA srvB1 = (ServiceA) context.getBean("serviceB");
		ServiceA srvB2 = (ServiceA) context.getBean("serviceB");
		Assert.assertSame(srvB1, srvB2);
	}

	@Test
	void testLazy() {
		ServiceA srvB1 = (ServiceA) context.getBean("serviceC");
	}

}
