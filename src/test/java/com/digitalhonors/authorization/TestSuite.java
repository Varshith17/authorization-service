package com.digitalhonors.authorization;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses({ AuthController.class, CustomerDetailsServiceTest.class, JwtUtilTests.class })

public class TestSuite {

}
