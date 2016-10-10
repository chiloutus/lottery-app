package com.newsweaver.lottery.model;



import java.util.List;

/**
 * Created by gary on 07/10/2016.
 */
public class Ticket {
    private String id;

    private List<Line> lines;

    private boolean checked = false;

    public Ticket() {
        //No argument constructor for OM
    }

    public Ticket(List<Line> lines) {
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void addLines(List<Line> linesToAdd) {
        this.lines.addAll(linesToAdd);
    }


}
