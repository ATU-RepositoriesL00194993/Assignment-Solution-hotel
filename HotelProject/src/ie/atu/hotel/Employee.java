package ie.atu.hotel;

import java.io.*;

import javax.swing.JTextField;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class Employee extends Person implements Payable, Serializable {
  private static int nextID = 1000;

  private Date dateOfBirth;
  private Date startDate;
  private double salary;
  private int ID;

  // Empty constructor
  public Employee() {
    super();
    this.dateOfBirth = new Date();
    this.startDate = new Date();
    this.salary = 0.0;

    this.ID = this.nextID++;
  }

  // Constructor with arguments
  public Employee(Name name, String phoneNumber, Date dateOfBirth, Date startDate, double salary) {
    super(name, phoneNumber);
    this.dateOfBirth = dateOfBirth;
    this.startDate = startDate;
    this.salary = salary;

    this.ID = this.nextID++;
  }

  // -------------------
  // NEW ADD METHOD for EmployeeSerializer
  // --------------- ----
  public boolean read() {
    JTextField employeeID = new JTextField();
    employeeID.setText("" + this.getID());
    JTextField txtFirstName = new JTextField();
    // dropdown menu for titles
    String[] titles = { "Mr", "Ms", "Mrs", "Miss" };
    JComboBox<String> boxTitle = new JComboBox<String>(titles);
    // txtFirstName.requestFocus();
    JTextField txtSurname = new JTextField();
    JTextField txtPhoneNumber = new JTextField();
    JTextField txtSalary = new JTextField();

    Object[] message = {
        "Employee Number:", employeeID,
        "Title:", boxTitle,
        "First Name:", txtFirstName,
        "Surname:", txtSurname,
        "Phone Number:", txtPhoneNumber,
        "Salary:", txtSalary,
    };

    JDialog dialog = new JDialog();
    dialog.setAlwaysOnTop(true);
    int option = JOptionPane.showConfirmDialog(dialog, message, "Enter book details", JOptionPane.OK_CANCEL_OPTION);

    // if CANCEL clicked
    if (option != JOptionPane.OK_OPTION) {
      nextID--;
      return false;
    }

    // create Employee from entered details
    this.setName(new Name(boxTitle.getSelectedItem().toString(), txtFirstName.getText(), txtSurname.getText()));
    this.setPhoneNumber(txtPhoneNumber.getText());
    // handle error if salary entered in incorrect format
    try {
      salary = Double.parseDouble(txtSalary.getText());
    } catch (NumberFormatException e) {
      System.out.println("Invalid salary input: " + e.getMessage());
      nextID--;
      return false;
    }
    this.setSalary(salary);
    return true;
  }

  // Interface methods
  @Override
  public double calculatePay(double taxPercentage) {
    double pay = this.salary / 12;
    return pay - (pay * (taxPercentage / 100));
  }

  @Override
  public double incrementSalary(double incrementAmount) {
    // Calculate new salary
    double incremented = this.salary + incrementAmount;
    // Check condition (new salary can not be greater then 150 000)
    if (incremented <= 150000) {
      this.salary = incremented;
    }
    return this.salary;
  }

  //
  public String toString() {
    return "employee id: " + this.ID + " " + super.toString() + " salary:" + this.salary + " DOB:" + dateOfBirth
        + " startDate:" + this.startDate;
  }

  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Employee))
      return false;

    Employee employee = (Employee) obj;

    return super.equals(employee) &&
        employee.getID() == this.ID &&
        employee.getDOB().equals(this.dateOfBirth) &&
        employee.getStartDate().equals(this.startDate) &&
        employee.getSalary() == this.salary;
  }

  // Getters and Setters
  public Date getDOB() {
    return this.dateOfBirth;
  }

  public void setDOB(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Date getStartDate() {
    return this.startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public double getSalary() {
    return this.salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public int getID() {
    return this.ID;
  }
}
