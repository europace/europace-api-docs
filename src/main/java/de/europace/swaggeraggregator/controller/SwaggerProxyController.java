package de.europace.swaggeraggregator.controller;

import de.europace.swaggeraggregator.model.SpecSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/specs")
public class SwaggerProxyController {

  private final SpecSourceProperties properties;
  private final RestTemplate restTemplate = new RestTemplate();

  @Autowired
  public SwaggerProxyController(SpecSourceProperties properties) {
    this.properties = properties;
  }

  @GetMapping("/{name}")
  public ResponseEntity<InputStreamResource> getSpec(@PathVariable String name) {
    var spec = properties.getSpecs().stream()
        .filter(s -> s.getPath().equalsIgnoreCase(name))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Spec not found: " + name));

    ResponseEntity<String> response = restTemplate.getForEntity(spec.getUrl(), String.class);
    if (!response.getStatusCode().is2xxSuccessful()) {
      return ResponseEntity.status(response.getStatusCode()).build();
    }

    byte[] bytes = response.getBody().getBytes(StandardCharsets.UTF_8);
    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(new InputStreamResource(new ByteArrayInputStream(bytes)));
  }
}