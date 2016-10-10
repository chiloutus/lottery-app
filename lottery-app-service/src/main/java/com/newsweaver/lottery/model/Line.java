package com.newsweaver.lottery.model;

import java.util.Arrays;

/**
 * Created by gary on 08/10/2016.
 */
public class Line {


    public Line() {
        //Default constructor for OM
    }

    int[] numbers;

    public Line(int[] numbers) {
        this.numbers = numbers;
    }

    public int[] getNumbers() {
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Line line = (Line) o;

        return Arrays.equals(numbers, line.numbers);

    }

    @Override
    public int hashCode() {
        return numbers != null ? Arrays.hashCode(numbers) : 0;
    }
}
