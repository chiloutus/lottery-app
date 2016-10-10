package com.newsweaver.lottery.persistence;


import com.newsweaver.lottery.model.Ticket;

import java.util.List;

/**
 * Created by gary on 07/10/2016.
 */
public interface TicketRepository {
    List<Ticket> getAllTickets();

    Ticket createTicket(Ticket ticket);

    Ticket findTicketById(String ticketId);

    void updateTicket(Ticket existingTicket);
}
