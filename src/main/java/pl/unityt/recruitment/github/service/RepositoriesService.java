package pl.unityt.recruitment.github.service;

import org.springframework.web.server.ResponseStatusException;
import pl.unityt.recruitment.github.dto.RepositoriesDataDTO;

public interface RepositoriesService {
    RepositoriesDataDTO getRepositoryData(String owner, String repositoryName)
            throws ResponseStatusException;
}
