package model;

import java.time.LocalDate;
import java.util.Objects;

public class Staff extends Employee{
    private String workAt;
    private LocalDate hiredAt;

    public String getWorkAt() {
        return workAt;
    }

    public void setWorkAt(String workAt) {
        this.workAt = Objects.requireNonNullElse(workAt, "Candidate");
    }

    public LocalDate getHiredAt() {
        return hiredAt;
    }

    public void setHiredAt(LocalDate hiredAt) {
        this.hiredAt = Objects.requireNonNullElse(hiredAt, LocalDate.now());
    }

    public Staff(String name, double salary, String workAt, LocalDate hiredAt) {
        super(name, salary);
        setWorkAt(workAt);
        setHiredAt(hiredAt);
    }

    @Override
    public String toString() {
        return String.format("%s, workAt: %s, hiredAt: %s", super.toString(), workAt, hiredAt.toString());
    }
}
