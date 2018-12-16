package org.ab.student;

import java.net.URI;

import org.ab.student.config.AppResourceConfig;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
	
	private static final URI BASE_URI = URI.create(System.getProperty("context_url", "http://localhost:8080/"));

    public static void main(final String... args) throws InterruptedException {
        
        final Channel server = createServer();

        createShutdownHook(server);
        log.info("HTTP/2 Server Started");
        log.info("Listening @ {}", BASE_URI);
        log.info("Ctrl+C to stop");
        Thread.currentThread().join();
    }
    
    public static Channel createServer() {
    	final ResourceConfig resourceConfig = new AppResourceConfig();
    	return NettyHttpContainerProvider.createHttp2Server(BASE_URI, resourceConfig, null);
    }

    private static void createShutdownHook(final Channel server) {
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> server.close())
        );
    }
}
