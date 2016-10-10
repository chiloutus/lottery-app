package com.newsweaver.web;

import com.newsweaver.lottery.dto.StatusDTO;
import com.newsweaver.lottery.dto.TicketDTO;
import com.newsweaver.lottery.service.ticket.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by gary on 05/10/2016.
 * The controller layer for the lottery app
 */
@Api(value = "Lottery App Api")
@RestController
@RequestMapping("/v1")
public class LotteryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);

    @Resource(name = "ticketService")
    TicketService ticketService;

    @RequestMapping(path = "/tickets/create", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "Creates a new ticket with the specified number of lines", notes = "")
    public TicketDTO createTicket(@RequestParam(value="numberOfLines") int numberOfLines){
        LOGGER.info("Request to create a ticket received, numberOfLines:", numberOfLines);
        return ticketService.createTicket(numberOfLines);
    }

    @RequestMapping(path = "/tickets/update/{ticketId}", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "Adds the specified number of lines to the existing ticket", notes = "")
    public TicketDTO updateTicket(@PathVariable(value = "ticketId") String ticketId,
                                  @RequestParam(value = "numberOfLines") int numberOfLines){
        LOGGER.info("Request to update a ticket received, numberOfLines: ", numberOfLines );
        return ticketService.addLinesToTicket(ticketId, numberOfLines);
    }

    //This needs to be a PUT as it actually updates the state of a ticket
    @RequestMapping(path = "/tickets/check/{ticketId}", method = RequestMethod.PUT,
            produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "Checks the status of the ticket", notes = "")
    public StatusDTO checkTicket(@PathVariable(value = "ticketId") String ticketId) {
        LOGGER.info("Request to update a ticket received, ticketId: ", ticketId);
        return ticketService.checkTicket(ticketId);
    }




}
