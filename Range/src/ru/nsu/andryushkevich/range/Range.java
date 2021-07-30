package ru.nsu.andryushkevich.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (range.to <= from || range.from >= to) {
            return null;
        }

        if (isInside(range.from) && isInside(range.to)) {
            return new Range(range.from, range.to);
        }

        if (isInside(range.from)) {
            return new Range(range.from, to);
        }

        if (isInside(range.to)) {
            return new Range(from, range.to);
        }

        return this;
    }

    public Range[] getUnion(Range range) {
        if (range.to < from || range.from > to) {
            return new Range[]{this, range};
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        if (from >= range.from && to <= range.to) {
            return new Range[0];
        }

        if (isInside(range.from) && isInside(range.to)) {
            return new Range[]{
                    new Range(from, range.from),
                    new Range(range.to, to)
            };
        }

        if (isInside(range.from)) {
            return new Range[]{new Range(from, range.from)};
        }

        if (isInside(range.to)) {
            return new Range[]{new Range(range.to, to)};
        }

        return new Range[]{this};
    }

    @Override
    public String toString() {
        return "[" + from + ", " + to + "]";
    }
}