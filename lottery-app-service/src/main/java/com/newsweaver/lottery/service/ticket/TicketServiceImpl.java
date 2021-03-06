package com.newsweaver.lottery.service.ticket;

import com.newsweaver.lottery.persistence.TicketRepository;
import com.newsweaver.lottery.service.EntityConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by gary on 07/10/2016.
 */
@Service("ticketService")
public class TicketServiceImpl extends AbstractTicketService {

    @Value("${all.match.case}")
    private Integer allMatchCase;

    @Value("${none.match.first}")
    private Integer noneMatchFirst;

    @Value("${sum.of.list.result}")
    private Integer listSumResult;

    @Value("${sum.of.list.value}")
    private Integer listSumValue;

    @Value("${default.case}")
    private Integer defaultCase;

    @Value("${line.length}")
    private Integer lineLength;

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

    @Override
    public int getAllMatch() {
        return allMatchCase;
    }

    @Override
    public int getNoneMatchFirst() {
        return noneMatchFirst;
    }

    @Override
    public int getListSumResult() {
        return listSumResult;
    }

    @Override
    public int getListSumValue() {
        return listSumValue;
    }

    @Override
    public int getDefaultCase() {
        return defaultCase;
    }

    @Override
    public int getLineLength() {
        return lineLength;
    }
}
