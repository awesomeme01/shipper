package com.example.demo.helper;

import java.time.LocalDateTime;

public class DateRangeWrapper {
    private LocalDateTime from;
    private LocalDateTime to;

    public DateRangeWrapper() {
    }

    public DateRangeWrapper(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }
}
