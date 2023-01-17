package pl.unityt.recruitment.github.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import pl.unityt.recruitment.github.client.GithubRestClient;
import pl.unityt.recruitment.github.dto.RepositoriesDataDTO;
import pl.unityt.recruitment.github.service.impl.RepositoriesServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RepositoriesServiceImplTest {

    @Mock
    private GithubRestClient githubRestClient;

    @Mock
    private RepositoriesDataDTO repositoriesDataDTO;

    @InjectMocks
    private RepositoriesServiceImpl repositoriesServiceImpl;

    @Test
    void getRepositoryDataShouldReturnRepositoryData() throws ResponseStatusException {
        //given
        when(githubRestClient.getRepositoryDataAsDTO(anyString(), anyString())).thenReturn(repositoriesDataDTO);
        //when
        //then
        assertNotNull(repositoriesServiceImpl.getRepositoryData(anyString(), anyString()));
    }

}