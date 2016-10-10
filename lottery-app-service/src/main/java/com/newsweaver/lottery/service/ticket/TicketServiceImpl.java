package com.newsweaver.lottery.service.ticket;

import com.newsweaver.lottery.persistence.TicketRepository;
import com.newsweaver.lottery.service.EntityConverter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by gary on 07/10/2016.
 */
@Service("ticketService")
public class TicketServiceImpl extends AbstractTicketService {

    @Resource(name = "ticketRepository")
    TicketRepository ticketRepository;

    @Resource
    EntityConverter entityConverter;

    @Override
    TicketRepository getTicketRepository() {
        return ticketRepository;
    }

    @Override
    EntityConverter getEntityConverter() {
        return entityConverter;
    }
}
