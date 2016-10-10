package com.newsweaver.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsweaver.LotterySystemApplication;
import com.newsweaver.lottery.dto.LineStatusDTO;
import com.newsweaver.lottery.dto.StatusDTO;
import com.newsweaver.lottery.dto.TicketDTO;
import com.newsweaver.lottery.model.Line;
import com.newsweaver.lottery.service.ticket.TicketService;
import com.newsweaver.lottery.service.ticket.TicketServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by gary on 09/10/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LotterySystemApplication.class)
@WebAppConfiguration
public class LotteryControllerTest {

    @Resource
    LotteryController lotteryController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    TicketService ticketService;

    private MockMvc mvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(lotteryController).build();
    }

    @Test
    public void testCreateTicket() throws Exception{
        int numberOfLines = 4;

        TicketDTO testDTO = getTicketDTO();

        ticketService = new TicketServiceImpl() {
            @Override
            public TicketDTO createTicket(int input) {
                Assert.assertEquals(numberOfLines,input);
                return testDTO;
            }
        };

        Whitebox.setInternalState(lotteryController, "ticketService", ticketService);

        String request = "/v1/tickets/create";

        mvc.perform(MockMvcRequestBuilders.post(request)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("numberOfLines", String.valueOf(numberOfLines)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(mvcResult -> {
            String jsonResponse = mvcResult.getResponse().getContentAsString();
            try {
                TicketDTO returnDTO = objectMapper.readValue(jsonResponse, TicketDTO.class);
                Assert.assertEquals(testDTO.getId(),returnDTO.getId());
                Assert.assertThat(testDTO.getLines(), CoreMatchers.equalTo(returnDTO.getLines()));
            } catch (Exception e) {
                Assert.fail("The response does not parse to TicketDTO");
            }
        });

    }

    @Test
    public void testUpdateTicket() throws Exception{
        int numberOfLines = 4;
        String ticketId = "ticket_1";

        TicketDTO testDTO = getTicketDTO();

        ticketService = new TicketServiceImpl() {
            @Override
            public TicketDTO addLinesToTicket(String id, int lines) {
                Assert.assertEquals(ticketId,id);
                Assert.assertEquals(numberOfLines,lines);
                return testDTO;
            }
        };

        Whitebox.setInternalState(lotteryController, "ticketService", ticketService);

        String request = "/v1/tickets/update/" + ticketId;


        mvc.perform(MockMvcRequestBuilders.post(request)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("numberOfLines", String.valueOf(numberOfLines)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(mvcResult -> {
            String jsonResponse = mvcResult.getResponse().getContentAsString();
            try {
                TicketDTO returnDTO = objectMapper.readValue(jsonResponse, TicketDTO.class);
                Assert.assertEquals(testDTO.getId(), returnDTO.getId());
                Assert.assertThat(testDTO.getLines(), CoreMatchers.equalTo(returnDTO.getLines()));
            } catch (Exception e) {
                Assert.fail("The response does not parse to TicketDTO");
            }
        });

    }

    @Test
    public void testCheckTicket() throws Exception{
        int numberOfLines = 4;
        String ticketId = "ticket_1";

        StatusDTO testDTO = getTicketStatusDTO();

        ticketService = new TicketServiceImpl() {
            @Override
            public StatusDTO checkTicket(String id) {
                Assert.assertEquals(ticketId,id);
                return testDTO;
            }
        };

        Whitebox.setInternalState(lotteryController, "ticketService", ticketService);

        String request = "/v1/tickets/check/" + ticketId;


        mvc.perform(MockMvcRequestBuilders.put(request)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("numberOfLines", String.valueOf(numberOfLines)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(mvcResult -> {
            String jsonResponse = mvcResult.getResponse().getContentAsString();
            try {
                StatusDTO returnDTO = objectMapper.readValue(jsonResponse, StatusDTO.class);
                Assert.assertEquals(testDTO.getTicketId(), returnDTO.getTicketId());
                Assert.assertThat(testDTO.getStatuses(), CoreMatchers.equalTo(returnDTO.getStatuses()));
            } catch (Exception e) {
                Assert.fail("The response does not parse to StatusDTO");
            }
        });

    }

    //Ticket DTO for tests
    public TicketDTO getTicketDTO() {
        List<Integer> arrayList = Arrays.asList(1, 2, 3);
        List<Line> lines = Collections.singletonList(new Line(arrayList));
        return new TicketDTO(lines);
    }

    public StatusDTO getTicketStatusDTO() {
        return new StatusDTO(Collections.singletonList(new LineStatusDTO(new Line(), 5)),"ticket_1");
    }
}
