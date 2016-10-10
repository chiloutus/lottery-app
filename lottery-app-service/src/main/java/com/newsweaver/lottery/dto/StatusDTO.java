package com.newsweaver.lottery.dto;

import java.util.List;

/**
 * Created by gary on 09/10/2016.
 */
public class StatusDTO {

    private String ticketId;
    private List<LineStatusDTO> statuses;

    public StatusDTO() {
        //No argument constructor for OM
    }

    public StatusDTO(List<LineStatusDTO> statuses, String id) {
        this.statuses = statuses;
        this.ticketId = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public List<LineStatusDTO> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<LineStatusDTO> statuses) {
        this.statuses = statuses;
    }
}
