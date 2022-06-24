package model;

import java.util.function.BiConsumer;

public class Manager extends Employee {
    private BiConsumer<Staff,Double> appointRule;

    public void setAppointRule(BiConsumer<Staff, Double> appointRule) {
        this.appointRule = appointRule;
    }

    public Manager(String name, double salary, BiConsumer<Staff, Double> appointRule) {
        super(name, salary);
        setAppointRule(appointRule);
    }

    public void onStaffAppointment(Staff member, Double newStaffMemberSalary) {
        appointRule.accept(member,newStaffMemberSalary);
    }
}
