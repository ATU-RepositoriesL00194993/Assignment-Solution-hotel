package ie.atu.serialize;

import java.io.*;
import java.util.*;
import javax.swing.*;

import ie.atu.hotel.Employee;
import ie.atu.hotel.Name;

public class EmployeeSerializer {
  private ArrayList<Employee> employees;

  private final String FILENAME = "employees.bin";
  private File employeesFile;

  // Default Constructor
  public EmployeeSerializer() {
    // Construct EmployeeList ArrayList
    employees = new ArrayList<Employee>();

    // Construct employeesFile...
    employeesFile = new File(FILENAME);

    // TODO Physically create the employeesFile
    try {
      employeesFile.createNewFile();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

  }

  //////////////////////////////////////
  // Method Name : add() //
  // Return Type : void //
  // Parameters : None //
  // Purpose : Reads one Employee record from the user //
  // and adds it to the ArrayList called employees //
  /////////////////////////////////////////
  public void add() {
    // Create an Employee object
    Employee employee = new Employee();

    // TODO - Update add() so it checks if Cancel was clicked when reading Employee

    // Read its details
    boolean created = employee.read();
    // NEW: CODE CHECKS IF DETAILS ENTERED PROPERLY
    if (created) {
      // And add it to the employees ArrayList
      employees.add(employee);
    }
  }

  ///////////////////////////////////
  // Method Name : list() //
  // Return Type : void //
  // Parameters : None //
  // Purpose : Lists all Employee recors in employees //
  ////////////////////////////////////
  public void list() {
    String employeesToList = "";

    if (!employees.isEmpty()) {
      // for every Employee object in employees
      for (Employee tmpEmployee : employees) {
        // add it to employeesToList and
        employeesToList += tmpEmployee;
        // add a newline
        employeesToList += "\n";
      }

      // Display employeesToList in a messageDialog
      JOptionPane.showMessageDialog(null, employeesToList, "EMPLOYEE LIST", JOptionPane.INFORMATION_MESSAGE);
    } else
      // Display "No Employees stored..." in messageDialog
      JOptionPane.showMessageDialog(null, "No Empoyees to list.", "EMPLOYEE LIST", JOptionPane.INFORMATION_MESSAGE);
  }

  /////// NEW METHOD - FIND EMPLOYEE BY ID
  public Employee find() {
    // get ID
    // read ID from user
    JTextField txtID = new JTextField();
    JDialog dialog = new JDialog();
    dialog.setAlwaysOnTop(true);
    int option = JOptionPane.showConfirmDialog(dialog, txtID, "Enter Employee's ID", JOptionPane.OK_CANCEL_OPTION);

    if (option != JOptionPane.OK_OPTION) {
      return null;
    }

    int idFromUser = 0;
    // try convert String from user to number
    try {
      idFromUser = Integer.parseInt(txtID.getText());
    } catch(NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Invalid ID input", "READ ID", JOptionPane.INFORMATION_MESSAGE);
      return null;
    }

    for (Employee e : employees) {
      if (e.getID() == idFromUser) {
        // found!
        return e;
      }
    }

    // Inform user if not found
    JOptionPane.showMessageDialog(null, "Employee with ID " + idFromUser + " NOT FOUND.", "EMPLOYEE VIEW", JOptionPane.INFORMATION_MESSAGE);
    return null;
  }

  ////////////////////////////////////////////
  // Method Name : view() //
  // Return Type : Employee //
  // Parameters : None //
  // Purpose : Displays the required Employee record on screen //
  // : And returns it, or null if not found //
  /////////////////////////////////////////////
  public Employee view() {
    // TODO - Write the code for view()
    if (employees.isEmpty()) {
      JOptionPane.showMessageDialog(null, "There are no Employees.", "VIEW EMPLOYEE", JOptionPane.INFORMATION_MESSAGE);
      return null;
    }
    // find employee by ID from user
    Employee foundEmployee = find();    

    if (foundEmployee == null) {
      return null;
    }
    // Display found employee
    JOptionPane.showMessageDialog(null, foundEmployee, "EMPLOYEE VIEW", JOptionPane.INFORMATION_MESSAGE);
    return foundEmployee;
  }

  ///////////////////////////////////////////////
  // Method Name : delete() //
  // Return Type : void //
  // Parameters : None //
  // Purpose : Deletes the required Employee recordfrom employees //
  ////////////////////////////////////////////////
  public void delete() {
    // TODO - Write the code for delete()
    if (employees.isEmpty()) {
      JOptionPane.showMessageDialog(null, "There are no Employees.", "DELETE EMPLOYEE", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    Employee foundEmployee = find();
    employees.remove(foundEmployee);

    JOptionPane.showMessageDialog(null, "Successfuly deleted employee.", "DELETE EMPLOYEE", JOptionPane.INFORMATION_MESSAGE);
  }

  ///////////////////////////////////////////
  // Method Name : edit() //
  // Return Type : void //
  // Parameters : None //
  // Purpose : Edits the required Employee recod in employees //
  ////////////////////////////////////////////
  public void edit() {
    // TODO - Write the code for edit()
    if (employees.isEmpty()) {
      JOptionPane.showMessageDialog(null, "There are no Employees.", "DELETE EMPLOYEE", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    Employee foundEmployee = find();
    if (foundEmployee == null) {
      return;
    }

    // edit by calling read
    foundEmployee.read();
  }

  // This method will serialize the employees ArrayList when called,
  // i.e. it will write employees to a file called employees.bin
  public void serializeEmployees() {
    // TODO - Write the code for serializeEmployees()
    ObjectOutputStream os = null;
    try {
      // Serialize the ArrayList of Employee objects
      FileOutputStream fileStream = new FileOutputStream(employeesFile);
      os = new ObjectOutputStream(fileStream);
      os.writeObject(employees);
      os.close();
    } catch (FileNotFoundException fNFE) {
      System.out.println("Cannot create file to save employees list");
    } catch (IOException ioE) {
      System.out.println("Cannot write to " + FILENAME + ".");
    }

    // JOptionPane.showMessageDialog(null, "You must write the code for the
    // serializeEmployees() method.",
    // "NOT IMPLEMENTED", JOptionPane.INFORMATION_MESSAGE);
  }

  // This method will deserialize the Employees ArrayList when called,
  // i.e. it will restore the employees ArrayList from the file employees.bin
  public void deserializeEmployees() {
    ObjectInputStream is = null;

    if (employeesFile.length() != 0) { // if there employeesFile is not empty
      try {
        // Deserialize the ArrayList...
        FileInputStream fileStream = new FileInputStream(employeesFile);

        is = new ObjectInputStream(fileStream);

        employees = (ArrayList<Employee>) is.readObject();

        is.close();
      } catch (FileNotFoundException e) {
        System.out.println("Cannot open file " + employeesFile.getName() + ".");
      } catch (IOException e) {
        System.out.println("Cannot read from " + employeesFile.getName() + ".");
      } catch (ClassNotFoundException e) {
        System.out.println("Employee class not found during deserialization.");
      }
    }
  }
}
