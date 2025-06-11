package de.europace.swaggeraggregator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "swagger")
public class SpecSourceProperties {

  private List<Spec> specs;

  public List<Spec> getSpecs() {
    return specs;
  }

  public void setSpecs(List<Spec> specs) {
    this.specs = specs;
  }

  public static class Spec {
    private String name;
    private String url;
    @JsonIgnore private String path;

    public String getPath() {
      return path;
    }

    public void setPath(String path) {
      this.path = path;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }
  }
}