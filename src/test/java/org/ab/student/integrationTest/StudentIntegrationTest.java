package org.ab.student.integrationTest;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

import org.ab.student.App;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

public class StudentIntegrationTest {
	static Channel server;
	
	@BeforeClass
	public static void setUp() {
		server = App.createServer();
	}
	
	@AfterClass
	public static void tearDown( ) {
		ChannelFuture future = server.close();
		future.awaitUninterruptibly();
	}

	@Test
	public void testFindById() {
		get("/students/3004").then().statusCode(200).assertThat().body("id", equalTo(3004), 
				"first", equalTo("Tyler"));
	}
	
	//Etc., etc. TODO: add counterparts of DaoTest

}
