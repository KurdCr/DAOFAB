package com.daofab.daofab;

import com.daofab.daofab.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CorsConfig.class)
public class DaofabApplication {

  public static void main(String[] args) {
    SpringApplication.run(DaofabApplication.class, args);
  }
}
