package com.clovirtualfashion.homework.config;

import io.micrometer.core.instrument.Meter.Id;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Configuration
public class MetricConfig {

  private static final String HTTP_CLIENT_REQUESTS = "http.client.requests";
  private static final String HTTP_SERVER_REQUESTS = "http.server.requests";

  @Bean
  public MeterRegistryCustomizer<MeterRegistry> configureMetricFilters() {
    return registry -> registry.config()
        .meterFilter(denyConfig())
        .meterFilter(tagConfig())
        .meterFilter(distributionStatisticConfig());
  }

  private MeterFilter denyConfig() {
    return MeterFilter.deny(
        id -> {
          String uri = id.getTag("uri");
          return uri != null
                 && (uri.equals("")
                     || uri.startsWith("/**")
                     || uri.startsWith("/health-check")
                     || uri.startsWith("/health")
                     || uri.startsWith("/metrics")
                     || uri.startsWith("/favicon.ico"));
        });
  }

  private MeterFilter tagConfig() {
    return new MeterFilter() {
      @Override
      public Id map(Id id) {
        if (!id.getName().startsWith(HTTP_CLIENT_REQUESTS)) {
          return id;
        }

        List<Tag> tags = id.getTags().stream().map(tag -> {
              String tagKey = tag.getKey();
              String uri = tag.getValue();
              if (!tagKey.equals("uri") || !uri.contains("?")) {
                return tag;
              }
              return Tag.of(tagKey, uri.substring(0, uri.indexOf('?')));
            })
            .collect(toList());

        return id.replaceTags(tags);
      }
    };
  }

  private MeterFilter distributionStatisticConfig() {
    return new MeterFilter() {
      @Override
      public DistributionStatisticConfig configure(Id id, DistributionStatisticConfig config) {
        if (id.getName().startsWith(HTTP_SERVER_REQUESTS) || id.getName().startsWith(HTTP_CLIENT_REQUESTS)) {
          return config.merge(DistributionStatisticConfig.builder()
                                  .percentilesHistogram(true)
                                  .percentiles(0.5, 0.9, 0.95, 0.99, 0.999)
                                  .percentilePrecision(1)
                                  .expiry(Duration.ofMinutes(1))
                                  .bufferLength(2)
                                  .build());
        }
        return config;
      }
    };
  }
}
