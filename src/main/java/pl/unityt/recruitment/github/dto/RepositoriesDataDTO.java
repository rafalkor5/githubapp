package pl.unityt.recruitment.github.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.time.ZonedDateTime;


@Data
public class RepositoriesDataDTO {

    @JsonAlias("full_name")
    private String fullName;
    private String description;
    @JsonAlias("clone_url")
    private String cloneUrl;
    @JsonAlias("watchers")
    private String stars;
    @JsonAlias("created_at")
    private ZonedDateTime createdAt;

}
