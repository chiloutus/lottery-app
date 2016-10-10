package com.newsweaver.lottery.service;

import com.newsweaver.lottery.dto.TicketDTO;
import com.newsweaver.lottery.model.Ticket;
import org.springframework.stereotype.Component;

/**
 * Created by gary on 07/10/2016.
 * A util class to convert DTOs to models and visa versa
 */
@Component
public class EntityConverter {


    public TicketDTO convert(Ticket ticket) {
        TicketDTO result = new TicketDTO(ticket.getLines());
        result.setId(ticket.getId());
        return result;
    }
}
