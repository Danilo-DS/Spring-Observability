package br.com.springwithobservability.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import net.ttddyy.observation.boot.autoconfigure.ProxyDataSourceBuilderCustomizer;

@Configuration
public class MonitoringConfig {
	
	@Autowired
	private ZipkinTracerComponent zipkinTracerComponent;
	
	//@Bean
	TimedAspect timedAspect(MeterRegistry registry) {
		return new TimedAspect(registry);
	}
	
	@Bean
	ObservedAspect observedAspect(ObservationRegistry registry) {
		registry.observationConfig().observationHandler(zipkinTracerComponent);
		return new ObservedAspect(registry);
	}
	
	@Bean
	ProxyDataSourceBuilderCustomizer proxyDataSourceBuilderCustomizer(){
	    return (builder, dataSource, beanName, dataSourceName) -> {
	        builder.name(beanName);
	    };
	}

	//@Bean
	Timer histogramBuilder(MeterRegistry registry) {
		
		return Timer.builder("custom.histogram")
				.publishPercentiles(0.5, 0.7, 0.95, 0.99) // median and 95th percentile (1)
				.publishPercentileHistogram(Boolean.TRUE) // (2)
				.serviceLevelObjectives(
					Duration.ofMillis(10),
					Duration.ofMillis(100),
					Duration.ofMillis(500),
					Duration.ofMillis(1000)
				) // (3)
			   .minimumExpectedValue(Duration.ofMillis(1)) // (4)
			   .maximumExpectedValue(Duration.ofSeconds(10))
			   .register(registry);
	}
}
