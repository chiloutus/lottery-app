package com.newsweaver.lottery.dto;

import com.newsweaver.lottery.model.Line;

/**
 * Created by gary on 09/10/2016.
 */
public class LineStatusDTO {

    private Line line;
    private int result;

    public LineStatusDTO() {
        //No argument constructor for OM
    }

    public LineStatusDTO(Line line, int result) {
        this.line = line;
        this.result = result;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineStatusDTO that = (LineStatusDTO) o;

        if (result != that.result) return false;
        return line != null ? line.equals(that.line) : that.line == null;

    }

    @Override
    public int hashCode() {
        int result1 = line != null ? line.hashCode() : 0;
        result1 = 31 * result1 + result;
        return result1;
    }
}
