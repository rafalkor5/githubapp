package pl.unityt.recruitment.github.client;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import pl.unityt.recruitment.github.dto.RepositoriesDataDTO;
import pl.unityt.recruitment.github.exception.InternalServiceException;
import pl.unityt.recruitment.github.exception.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GithubRestClientTest {
    private static final String EXAMPLE_OWNER = "exampleOwner";
    private static final String EXAMPLE_REPOSITORY = "exampleRepository";
    private static final String TEST_URL = "https://api.github.com/repos/exampleOwner/exampleRepository";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GithubRestClient githubRestClient;


    @Test()
    void getRepositoryDataAsDTOShouldThrowResourceNotFoundException() {
        //given
        when(restTemplate.getForObject(anyString(), anyObject())).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        //when
        //then
        assertThrows(ResourceNotFoundException.class, () -> githubRestClient.getRepositoryDataAsDTO(EXAMPLE_OWNER, EXAMPLE_REPOSITORY));
    }

    @Test()
    void getRepositoryDataAsDTOShouldThrowInternalServiceException() {
        //given
        when(restTemplate.getForObject(anyString(), anyObject())).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        //when
        //then
        assertThrows(InternalServiceException.class, () -> githubRestClient.getRepositoryDataAsDTO(EXAMPLE_OWNER, EXAMPLE_REPOSITORY));
    }

    @Test()
    void getRepositoryDataAsDTOShouldInvokeRestTemplateGetForObjectWithCorrectUrl() {
        //given
        //when
        githubRestClient.getRepositoryDataAsDTO(EXAMPLE_OWNER, EXAMPLE_REPOSITORY);
        //then
        verify(restTemplate).getForObject(TEST_URL, RepositoriesDataDTO.class);
    }
}