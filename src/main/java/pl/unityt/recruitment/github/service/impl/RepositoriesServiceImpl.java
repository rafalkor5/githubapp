package pl.unityt.recruitment.github.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.unityt.recruitment.github.client.GithubRestClient;
import pl.unityt.recruitment.github.dto.RepositoriesDataDTO;
import pl.unityt.recruitment.github.service.RepositoriesService;

@Service
@RequiredArgsConstructor
@Slf4j
public class RepositoriesServiceImpl implements RepositoriesService {
    private final GithubRestClient githubRestClient;

    @Override
    public RepositoriesDataDTO getRepositoryData(String owner, String repositoryName)
            throws ResponseStatusException {
        return githubRestClient.getRepositoryDataAsDTO(owner, repositoryName);
    }
}
