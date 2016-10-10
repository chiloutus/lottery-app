package com.newsweaver.lottery.service.ticket;

import com.newsweaver.lottery.dto.StatusDTO;
import com.newsweaver.lottery.dto.TicketDTO;

import java.util.List;

/**
 * Created by gary on 07/10/2016.
 */
public interface TicketService {

    TicketDTO createTicket(int numberOfLines);

    TicketDTO addLinesToTicket(String id, int numberOfLines);

    StatusDTO checkTicket(String ticketId);
}
