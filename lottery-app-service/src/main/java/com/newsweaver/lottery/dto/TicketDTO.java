package com.newsweaver.lottery.dto;

import com.newsweaver.lottery.model.Line;

import java.util.List;

/**
 * Created by gary on 07/10/2016.
 */
public class TicketDTO {

    private String id;
    private List<Line> lines;

    public TicketDTO() {
        //No argument constructor for OM
    }

    public TicketDTO(List<Line> lines) {
        this.lines = lines;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }
}
