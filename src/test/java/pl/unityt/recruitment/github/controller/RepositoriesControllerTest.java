package pl.unityt.recruitment.github.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.unityt.recruitment.github.dto.RepositoriesDataDTO;
import pl.unityt.recruitment.github.exception.InternalServiceException;
import pl.unityt.recruitment.github.exception.ResourceNotFoundException;
import pl.unityt.recruitment.github.service.impl.RepositoriesServiceImpl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RepositoriesController.class)
class RepositoriesControllerTest {
    private static final String testUri = "/repositories/testOwner/testRepositoryName";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositoriesServiceImpl repositoriesServiceImpl;

    @Mock
    private RepositoriesDataDTO repositoriesDataDTO;

    @Test
    void getRepositoriesShouldReturnStatusOK() throws Exception {
        //given
        when(repositoriesServiceImpl.getRepositoryData(anyString(), anyString())).thenReturn(repositoriesDataDTO);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(testUri)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getRepositoriesShouldReturnStatusNotFound() throws Exception {
        //given
        when(repositoriesServiceImpl.getRepositoryData(anyString(), anyString())).thenThrow(new ResourceNotFoundException(testUri));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(testUri)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getRepositoriesShouldReturnStatusIsInternalServerError() throws Exception {
        //given
        when(repositoriesServiceImpl.getRepositoryData(anyString(), anyString())).thenThrow(new InternalServiceException());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .get(testUri)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }
}