package de.europace.swaggeraggregator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.europace.swaggeraggregator.model.SpecSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerUiController {

  private final SpecSourceProperties specProperties;

  @Autowired
  private ObjectMapper objectMapper;

  public SwaggerUiController(SpecSourceProperties specProperties) {
    this.specProperties = specProperties;
  }

  @GetMapping("/aggregated-swagger-ui")
  public String swaggerUi(Model model) throws JsonProcessingException {
    model.addAttribute("specs", specProperties.getSpecs());
    return "aggregated-swagger-ui";
  }
}