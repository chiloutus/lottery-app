package com.newsweaver.lottery.service.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsweaver.lottery.ServiceConfiguration;
import com.newsweaver.lottery.dto.StatusDTO;
import com.newsweaver.lottery.dto.TicketDTO;
import com.newsweaver.lottery.model.Ticket;
import com.newsweaver.lottery.persistence.TicketRepository;
import com.newsweaver.lottery.service.EntityConverter;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;

import static org.mockito.Matchers.any;

/**
 * Created by gary on 10/10/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class TicketServiceTest {

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Resource
    private EntityConverter entityConverter;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private EntityConverter mockConverter;

    @Mock
    private TicketRepository ticketRepository;

    private static final String TICKET_ID = "ticket_1";


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateTicket() throws IOException{
        int numberOfLines = 4;

        Ticket ticket = objectMapper.readValue(getInputTicketString(), Ticket.class);

        TicketDTO dto = entityConverter.convert(ticket);


        Mockito.when(ticketRepository.createTicket(any(Ticket.class))).thenReturn(ticket);

        Mockito.when(mockConverter.convert(any(Ticket.class))).thenReturn(dto);

        TicketDTO returnedTicket = ticketService.createTicket(numberOfLines);

        Assert.assertEquals(returnedTicket.getId(), dto.getId());
        Assert.assertThat(returnedTicket.getLines(), CoreMatchers.equalTo(dto.getLines()));
    }

    @Test
    public void testUpdateTicket() throws IOException{
        int numberOfLines = 2;

        Ticket ticket = objectMapper.readValue(getInputTicketString(), Ticket.class);
        Ticket updatedTicket = objectMapper.readValue(getUpdatedTicketString(), Ticket.class);

        TicketDTO updatedTicketDTO = entityConverter.convert(updatedTicket);


        Mockito.when(ticketRepository.findTicketById(TICKET_ID)).thenReturn(ticket);
        Mockito.when(mockConverter.convert(any(Ticket.class))).thenReturn(updatedTicketDTO);

        TicketDTO returnedTicket = ticketService.addLinesToTicket(TICKET_ID, numberOfLines);

        Assert.assertEquals(returnedTicket.getId(), updatedTicket.getId());
        Assert.assertEquals(returnedTicket.getLines(), updatedTicket.getLines());
    }

    @Test
    public void testCheckTicket() throws IOException{

        Ticket ticket = objectMapper.readValue(getInputTicketString(), Ticket.class);

        TicketDTO ticketDTO = entityConverter.convert(ticket);


        Mockito.when(ticketRepository.findTicketById(TICKET_ID)).thenReturn(ticket);
        Mockito.when(mockConverter.convert(any(Ticket.class))).thenReturn(ticketDTO);

        StatusDTO ticketStatus = ticketService.checkTicket(TICKET_ID);

        Assert.assertEquals(ticketStatus.getTicketId(), TICKET_ID);
        //These should be ordered in in terms of result, as well as correct
        Assert.assertEquals(ticketStatus.getStatuses().get(0).getResult(), 0);
        Assert.assertEquals(ticketStatus.getStatuses().get(1).getResult(), 1);
        Assert.assertEquals(ticketStatus.getStatuses().get(2).getResult(), 5);
        Assert.assertEquals(ticketStatus.getStatuses().get(3).getResult(), 10);
    }

    private String getInputTicketString() {
        return " {\n" +
                "    \"id\": \"ticket_1\",\n" +
                "    \"lines\": [\n" +
                "      {\n" +
                "        \"numbers\": [\n" +
                "          0,\n" +
                "          1,\n" +
                "          1\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"numbers\": [\n" +
                "          1,\n" +
                "          1,\n" +
                "          1\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"numbers\": [\n" +
                "          1,\n" +
                "          0,\n" +
                "          2\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"numbers\": [\n" +
                "          2,\n" +
                "          0,\n" +
                "          2\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  }";
    }
    private String getUpdatedTicketString() {
        return " {\n" +
                "    \"id\": \"ticket_1\",\n" +
                "    \"lines\": [\n" +
                "      {\n" +
                "        \"numbers\": [\n" +
                "          0,\n" +
                "          1,\n" +
                "          1\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"numbers\": [\n" +
                "          1,\n" +
                "          1,\n" +
                "          1\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"numbers\": [\n" +
                "          1,\n" +
                "          0,\n" +
                "          2\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"numbers\": [\n" +
                "          2,\n" +
                "          0,\n" +
                "          2\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"numbers\": [\n" +
                "          0,\n" +
                "          1,\n" +
                "          1\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"numbers\": [\n" +
                "          1,\n" +
                "          0,\n" +
                "          0\n" +
                "        ]\n" +
                "      },{\n" +
                "        \"numbers\": [\n" +
                "          0,\n" +
                "          1,\n" +
                "          1\n" +
                "        ]\n" +
                "      },{\n" +
                "        \"numbers\": [\n" +
                "          0,\n" +
                "          1,\n" +
                "          1\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  }";
    }
}
