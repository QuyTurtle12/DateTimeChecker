package com.swr302.datetimechecker;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.swr302.datetimechecker.controllers.DateController;

@WebMvcTest(DateController.class)
public class checkDate {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testValidDate() throws Exception {
        mockMvc.perform(post("/checkDate")
                .param("day", "29")
                .param("month", "2")
                .param("year", "2020"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("success"))
                .andExpect(model().attribute("success", "29/2/2020 is a correct date time!"));
    }

    @Test
    public void testInvalidDate() throws Exception {
        mockMvc.perform(post("/checkDate")
                .param("day", "29")
                .param("month", "2")
                .param("year", "2009"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "29/2/2009 is NOT a correct date time!"));
    }

    @Test
    public void testDayOutOfRangeWithInputBiggerThan31() throws Exception {
        mockMvc.perform(post("/checkDate")
                .param("day", "32")
                .param("month", "4")
                .param("year", "2003"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Input data for Day is out of range!"));
    }

    @Test
    public void testDayOutOfRangeWithInputSmallerThan1() throws Exception {
        mockMvc.perform(post("/checkDate")
                .param("day", "0")
                .param("month", "4")
                .param("year", "2003"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Input data for Day is out of range!"));
    }

    @Test
    public void testMonthOutOfRangeWithInputBiggerThan12() throws Exception {
        mockMvc.perform(post("/checkDate")
                .param("day", "30")
                .param("month", "14")
                .param("year", "2009"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Input data for Month is out of range!"));
    }

    @Test
    public void testMonthOutOfRangeWithInputSmallerThan1() throws Exception {
        mockMvc.perform(post("/checkDate")
                .param("day", "1")
                .param("month", "0")
                .param("year", "2020"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Input data for Month is out of range!"));
    }

    @Test
    public void testYearOutOfRangeWithInputSmallerThan1000() throws Exception {
        mockMvc.perform(post("/checkDate")
                .param("day", "30")
                .param("month", "12")
                .param("year", "999"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Input data for Year is out of range!"));
    }

    @Test
    public void testYearOutOfRangeWithInputBiggerThan3000() throws Exception {
        mockMvc.perform(post("/checkDate")
                .param("day", "6")
                .param("month", "4")
                .param("year", "3001"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Input data for Year is out of range!"));
    }

    @Test
    public void testIncorrectDayFormatWithNonIntegerInput() throws Exception {
        mockMvc.perform(post("/checkDate")
                .param("day", "DD")
                .param("month", "2")
                .param("year", "2023"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Input data for Day is incorrect format!"));
    }
    
    @Test
    public void testIncorrectMonthFormatWithNonIntegerInput() throws Exception {
        mockMvc.perform(post("/checkDate")
                .param("day", "29")
                .param("month", "MM")
                .param("year", "2023"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Input data for Month is incorrect format!"));
    }

    @Test
    public void testIncorrectYearFormatWithNonIntegerInput() throws Exception {
        mockMvc.perform(post("/checkDate")
                .param("day", "29")
                .param("month", "3")
                .param("year", "YYYY"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Input data for Year is incorrect format!"));
    }

    @Test
    public void testDateWithBlankDay() throws Exception {
        mockMvc.perform(post("/checkDate")
                .param("day", "")
                .param("month", "8")
                .param("year", "2023"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Input data for Day is incorrect format!"));
    }

    @Test
    public void testDateWithBlankMonth() throws Exception {
        mockMvc.perform(post("/checkDate")
                .param("day", "6")
                .param("month", "")
                .param("year", "2003"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Input data for Month is incorrect format!"));
    }

    @Test
    public void testDateWithBlankYear() throws Exception {
        mockMvc.perform(post("/checkDate")
                .param("day", "1")
                .param("month", "12")
                .param("year", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Input data for Year is incorrect format!"));
    }

    @Test
    public void testDateWithNullNDay() throws Exception {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
        mockMvc.perform(post("/checkDate")
                .param("day", null)
                .param("month", "12")
                .param("year", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Input data for Day is incorrect format!"));
    });
    }

    @Test
    public void testDateWithNullMonth() throws Exception {
         IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
        mockMvc.perform(post("/checkDate")
                .param("day", "6")
                .param("month", null)
                .param("year", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Input data for Month is incorrect format!"));
    });
    }

    @Test
    public void testDateWithNullYear() throws Exception {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
        mockMvc.perform(post("/checkDate")
                .param("day", "1")
                .param("month", "12")
                .param("year", null))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Input data for Year is incorrect format!"));
    });
    }
}