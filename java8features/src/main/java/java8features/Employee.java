package java8features;

public class Employee {

    private Long numEmployee;
    private String name;
    private String lastName;

    public Employee(Long numEmployee, String name, String lastName) {
        super();
        this.numEmployee = numEmployee;
        this.name = name;
        this.lastName = lastName;
    }

    public Long getNumEmployee() {
        return numEmployee;
    }

    public void setNumEmployee(Long numEmployee) {
        this.numEmployee = numEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    public void incNumEmployee() {
        this.numEmployee = this.numEmployee +1;
    }
}
