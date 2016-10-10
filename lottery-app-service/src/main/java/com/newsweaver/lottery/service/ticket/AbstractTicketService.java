package com.newsweaver.lottery.service.ticket;

import com.newsweaver.lottery.ServiceConstants;
import com.newsweaver.lottery.dto.LineStatusDTO;
import com.newsweaver.lottery.dto.StatusDTO;
import com.newsweaver.lottery.dto.TicketDTO;
import com.newsweaver.lottery.exception.LotteryAppException;
import com.newsweaver.lottery.exception.entity.InvalidInputException;
import com.newsweaver.lottery.exception.entity.TicketAlreadyCheckedException;
import com.newsweaver.lottery.model.Line;
import com.newsweaver.lottery.model.Ticket;
import com.newsweaver.lottery.persistence.TicketRepository;
import com.newsweaver.lottery.service.EntityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by gary on 07/10/2016.
 */
public abstract class AbstractTicketService implements TicketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTicketService.class);

    @Override
    public TicketDTO createTicket(int numberOfLines) {

        //validate inputs and throw an exception (400) if its invalid
        if(numberOfLines < 0) {
            LOGGER.error("Received an invalid request for a new ticket with", numberOfLines, "lines");
            throw new LotteryAppException(new InvalidInputException("Number of lines must be above 0, was: " + numberOfLines ));
        }

        //Create a ticket with the number of lines specified
        Ticket created = new Ticket(generateLines(numberOfLines));

        LOGGER.error("Created new ticket with ID", created.getId());

        //persist the ticket
        created = getTicketRepository().createTicket(created);

        return getEntityConverter().convert(created); //convert back to DTO for returning to the user
    }

    @Override
    public TicketDTO addLinesToTicket(String id, int numberOfLines) {

        //validate inputs and throw an exception (400) if its invalid
        if(numberOfLines < 0) {
            LOGGER.error("Received an invalid request to add ", numberOfLines, "to a ticket");
            throw new LotteryAppException(new InvalidInputException("Input was invalid, numberOfLines: " + numberOfLines ));
        }

        Ticket existingTicket = getTicketRepository().findTicketById(id);
        if(existingTicket.isChecked()) {
            LOGGER.error("Received an add lines to an already checked ticket! Ticket Id was:", id);
            throw new LotteryAppException(new TicketAlreadyCheckedException("This ticket has already been checked" +
                    ", it may not be amended now."));
        }
        existingTicket.addLines(generateLines(numberOfLines));
        return getEntityConverter().convert(existingTicket);
    }

    @Override
    public StatusDTO checkTicket(String ticketId) {

        //firstly read out the ticket
        Ticket existingTicket = getTicketRepository().findTicketById(ticketId);

        //Read out the ticket status
        StatusDTO ticketStatus = evaluateTicketStatus(existingTicket);

        //Then before we return, update the ticket so it cant be amended
        existingTicket.setChecked(true);
        getTicketRepository().updateTicket(existingTicket);

        return ticketStatus;

    }

    private StatusDTO evaluateTicketStatus(Ticket existingTicket) {
        List<LineStatusDTO> statuses = new ArrayList<>();
        for(Line line : existingTicket.getLines()) {
            int status = 0;
            int[] numbers = line.getNumbers();
            if(numbers[0] + numbers[1] + numbers[2] == 2) {
                status = 10;
            } else if(numbers[0] == numbers[1] && numbers[0] == numbers[2]) {
                status = 5;
            } else if(numbers[0] != numbers[1] && numbers[0] != numbers[2]) {
                status = 1;
            }
            statuses.add(new LineStatusDTO(line, status));
        }
        statuses = sortStatuses(statuses);
        return new StatusDTO(statuses, existingTicket.getId());
    }
    //Custom sorting logic
    private List<LineStatusDTO> sortStatuses(List<LineStatusDTO> statuses) {
        Collections.sort(statuses, (o1, o2) -> {
            if(o1.getResult() > o2.getResult()) {
                return 1;
            } else if(o1.getResult() < o2.getResult()) {
                return -1;
            } else {
                return 0;
            }
        });
        return statuses;
    }

    private List<Line> generateLines(int numberOfLines) {
        List<Line> lines = new ArrayList<>();
        for(int i = 0; i < numberOfLines; i++) {
            int[] lineNumbers = new int[ServiceConstants.LINE_LENGTH];
            for(int j = 0; j < lineNumbers.length; j++) {
                lineNumbers[j] = ThreadLocalRandom.current().nextInt(0,3);
            }
            lines.add(new Line(lineNumbers));
        }
        return lines;
    }

    abstract TicketRepository getTicketRepository();

    abstract EntityConverter getEntityConverter();
}
