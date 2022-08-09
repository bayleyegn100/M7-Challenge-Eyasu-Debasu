package com.mymusicstore.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mymusicstore.musicstorecatalog.model.Label;
import com.mymusicstore.musicstorecatalog.repository.LabelRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LabelController.class)
public class LabelControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LabelRepository labelRepository;
    private Label label;
    private ObjectMapper mapper = new ObjectMapper();
    private String labelJson;
    private List<Label> labelList = new ArrayList<>();

    @Before
    public void setup() {
        this.labelRepository = mock(LabelRepository.class);
        label = new Label();
        label.setName("Afro Records");
        label.setId(65L);
        label.setWebsite("www.afrorecords.com");


        Label label1 = new Label();
        label1.setName("Afro Records");
        label1.setWebsite("www.afrorecords.com");

        List<Label> labelList1 = Arrays.asList(label);
        Optional<Label> findLabelById = Optional.of(label);

        doReturn(labelList1).when(labelRepository).findAll();
        doReturn(label).when(labelRepository).save(label1);
        doReturn(findLabelById).when(labelRepository).findById(65L);

    }
    @Test
    public void getLabelByIdShouldReturnALabel() throws Exception {
        Optional<Label> actualRecommendedLabel = labelRepository.findById(65L);
        Label expectedRecommendedLabel = label;
        assertEquals(expectedRecommendedLabel, actualRecommendedLabel.get());

    }

    @Test
    public void getLabelByIdStatusShouldReturnOkForNonExistingId() throws Exception {

        doReturn(Optional.empty()).when(labelRepository).findById(22L);

        mockMvc.perform(
                        get("/label/22"))
                .andExpect(status().isOk()
                );
    }

    @Test
    public void updateLabelByIdsShouldUpdateALabelAndReturn200StatusCode() throws Exception {
        label = new Label();
        label.setName("Afro Records");
        label.setId(65L);
        label.setWebsite("www.afrorecords.com");

        labelJson = mapper.writeValueAsString(label);
        mockMvc.perform(
                put("/label")
                        .content(labelJson)
                        .contentType(MediaType.APPLICATION_JSON)
        );
    }

    @Test
    public void deleteLabelByIdShouldDeleteALabelAndReturn200StatusCode() throws Exception {
        mockMvc.perform(delete("/label/65")).andExpect(status().isNoContent());
    }

    @Test
    public void getAllLabelShouldReturnAListAnd200() throws Exception {

        List<Label> actualRecommendedLabel = labelRepository.findAll();
        assertEquals(1, actualRecommendedLabel.size());

    }
    @Test
    public void addLabelShouldReturnNewLabelAnd201() throws Exception {

        Label expectedResult = new Label();
        expectedResult.setName("Afro Records");
        expectedResult.setWebsite("www.afrorecords.com");

        label.setName("Afro Records");
        label.setId(65L);
        label.setWebsite("www.afrorecords.com");

        Label labelWithId = new Label();
        labelWithId.setId(65L);
        labelWithId.setName("Afro Records");
        labelWithId.setWebsite("www.afrorecords.com");

        Label actualRecommendedLabel = labelRepository.save(expectedResult);
        assertEquals(labelWithId, actualRecommendedLabel);
    }

}