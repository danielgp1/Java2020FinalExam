package model;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Department {
    private String deptName;
    private Manager manager;
    private List<Staff> staff;
    public BiConsumer<Staff,Double> appointmentHandler;

    public Department(Manager manager, String deptName) {
        setManager(manager);
        setDeptName(deptName);
        staff = new ArrayList<>();
        appointmentHandler = (worker, workerSalary) -> {worker.setWorkAt(deptName);
                                                        worker.setHiredAt(LocalDate.now());
                                                        worker.setSalary(workerSalary);
                                                        staff.add(worker);};
    }

    public void setManager(Manager manager) {
        if(manager != null)
            this.manager = manager;
        else
            throw new InvalidParameterException();
    }

    public void setDeptName(String deptName) {
        if(deptName != null)
            this.deptName = deptName;
        else
            throw new InvalidParameterException();
    }

    public List<Staff> getStaff() {
        return staff;
    }



    @Override
    public String toString() {
        return String.format("Department: %s, Manager: %s, Staff: %s", deptName, manager, staff.toString());
    }
}
