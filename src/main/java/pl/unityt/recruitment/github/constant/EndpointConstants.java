package pl.unityt.recruitment.github.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EndpointConstants {
    public static final String BASE_PATH_URL = "https://api.github.com";
    public static final String REPOSITORY_DATA_ENDPOINT = "/repos/{owner}/{repository_name}";

}
