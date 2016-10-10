package com.newsweaver.lottery.persistence;

import com.newsweaver.lottery.ServiceConstants;
import com.newsweaver.lottery.exception.LotteryAppException;
import com.newsweaver.lottery.exception.entity.MultipleEntitiesFoundException;
import com.newsweaver.lottery.exception.entity.TicketNotFoundException;
import com.newsweaver.lottery.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gary on 07/10/2016.
 * A simulated repository in memory
 */
@Repository("ticketRepository")
public class TicketRepositoryImpl implements TicketRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketRepository.class);

    private List<Ticket> ticketList = new ArrayList<>();

    private int numberOfTickets = 0;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketList;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        ticket.setId(ServiceConstants.TICKETID_PREFIX + ++numberOfTickets);
        ticketList.add(ticket);
        return ticket;
    }

    @Override
    public Ticket findTicketById(String ticketId) {
        List<Ticket> resultList = this.ticketList.stream().filter(t -> t.getId().equals(ticketId)).collect(Collectors.toList());
        if(resultList.size() > 1) {
            throw new LotteryAppException(new MultipleEntitiesFoundException("Found two tickets with the same Id!"));
        } else if(resultList.isEmpty()) {
            LOGGER.error("Ticket with specified Id not found! id was {}", ticketId);
            throw new LotteryAppException(new TicketNotFoundException("Ticket with specified Id not found! id was:" +
                    " " + ticketId));
        }
        return resultList.get(0);
    }

    @Override
    public void updateTicket(Ticket existingTicket) {
        ticketList.removeIf(ticket -> ticket.getId().equals(existingTicket.getId()));
        ticketList.add(existingTicket);
    }
}
