package pl.unityt.recruitment.github.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import pl.unityt.recruitment.github.dto.RepositoriesDataDTO;
import pl.unityt.recruitment.github.exception.InternalServiceException;
import pl.unityt.recruitment.github.exception.ResourceNotFoundException;

import static pl.unityt.recruitment.github.constant.EndpointConstants.BASE_PATH_URL;
import static pl.unityt.recruitment.github.constant.EndpointConstants.REPOSITORY_DATA_ENDPOINT;

@Component
@RequiredArgsConstructor
@Slf4j
public class GithubRestClient {
    private final RestTemplate restTemplate;

    public RepositoriesDataDTO getRepositoryDataAsDTO(String owner, String repositoryName) throws ResponseStatusException {
        try {
            return restTemplate.getForObject(getRepositoryDataEndpoint(owner, repositoryName), RepositoriesDataDTO.class);
        } catch (HttpClientErrorException e) {
            log.error("Resource not found", e);
            throw new ResourceNotFoundException(getRepositoryDataEndpoint(owner, repositoryName));
        } catch (HttpServerErrorException e) {
            log.error("Internal server error", e);
            throw new InternalServiceException();
        } catch (Exception e) {
            log.error("Something went wrong", e);
            throw new InternalServiceException();
        }
    }

    private String getRepositoryDataEndpoint(String owner, String repositoryName) {
        return BASE_PATH_URL + REPOSITORY_DATA_ENDPOINT
                .replace("{owner}", owner)
                .replace("{repository_name}", repositoryName);
    }

}
