package codetest;

import codetest.PO.Result;
import codetest.controller.TripCalculatorController;
import codetest.exceptions.InvalidLocationException;
import codetest.exceptions.InvalidLocationNameException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = TripCalculatorApplication.class)
@AutoConfigureMockMvc
public class TripCalculatorApplicationTest {

    @MockBean
    private TripCalculator tc;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testById() throws Exception {
        when(tc.tripCalculate(1, 2)).thenReturn(new Result("A", "B", 1d, 2d));
        mockMvc.perform(get("/location/id/{start}/{end}", 1, 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cost", is(2.0)));
    }

    @Test
    void testByName() throws Exception {
        when(tc.tripCalculate("A", "B")).thenReturn(new Result("A", "B", 1d, 2d));
        mockMvc.perform(get("/location/{start}/{end}", "A", "B"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cost", is(2.0)));
    }

}
