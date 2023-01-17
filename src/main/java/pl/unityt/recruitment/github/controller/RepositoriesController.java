package pl.unityt.recruitment.github.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.unityt.recruitment.github.dto.RepositoriesDataDTO;
import pl.unityt.recruitment.github.service.impl.RepositoriesServiceImpl;


@RestController
@RequiredArgsConstructor
public class RepositoriesController {
    private final RepositoriesServiceImpl repositoriesServiceImpl;

    @GetMapping("/repositories/{owner}/{repositoryName}")
    public RepositoriesDataDTO repositories(@PathVariable String owner, @PathVariable String repositoryName) throws ResponseStatusException {
        return repositoriesServiceImpl.getRepositoryData(owner, repositoryName);
    }
}
