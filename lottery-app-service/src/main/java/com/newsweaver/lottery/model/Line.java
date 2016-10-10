package com.newsweaver.lottery.model;

import java.util.List;

/**
 * Created by gary on 08/10/2016.
 */
public class Line {


    public Line() {
        //Default constructor for OM
    }

    List<Integer> numbers;

    public Line(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Line line = (Line) o;

        return !(numbers != null ? !numbers.equals(line.numbers) : line.numbers != null);

    }

    @Override
    public int hashCode() {
        return numbers != null ? numbers.hashCode() : 0;
    }
}
