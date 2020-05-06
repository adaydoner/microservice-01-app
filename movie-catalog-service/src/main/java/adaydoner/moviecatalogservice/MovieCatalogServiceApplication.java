package adaydoner.moviecatalogservice;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class MovieCatalogServiceApplication {

    @Bean
    @LoadBalanced
    WebClient.Builder builder() {
        return WebClient.builder();
    }
	
	@Bean
	public WebClient getWebClient(WebClient.Builder builder){
		HttpClient httpClient = HttpClient.create()
				.tcpConfiguration(client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000) // time period in which our client should establish a connection with a target host.
				.doOnConnected(connection -> {
					connection.addHandlerLast(new ReadTimeoutHandler(3000, TimeUnit.MILLISECONDS)); // maximum time of inactivity between two data packets when waiting for the server's response.
					connection.addHandlerLast(new WriteTimeoutHandler(3000, TimeUnit.MILLISECONDS)); // maximum time of inactivity between two data packets when sending the request to the server.
				}));
		
		return builder.clientConnector(new ReactorClientHttpConnector(httpClient)).build();
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
