package br.com.redhat.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import br.com.redhat.CustomException;

@Component
public class StarterRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		onException(CustomException.class)
			.handled(true)
			.log("${exception.message}")
    		.log("[HELLO WORLD WITH EXCEPTION]")
		;
		
		from("timer:timer-component?period=5000")
			.routeId("initial-timer")
			.log("[ROUTE START]")
		.to("direct:say-hello-world")
		;
		
		from("direct:say-hello-world")
			.log("[HELLO WORLD]")
		;
		
		from("timer:second-timer-component?period=20000")
		.routeId("exception-timer")
			.log("[ROUTE WITH EXCEPTION START]")
		.to("direct:say-hello-world-with-exception")
		;
		
		from("direct:say-hello-world-with-exception")
		.process(e -> {
			throw new CustomException("Failed!");
		})
		;
		
	}
}
