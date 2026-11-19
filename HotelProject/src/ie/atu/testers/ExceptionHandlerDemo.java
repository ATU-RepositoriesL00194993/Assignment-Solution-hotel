package ie.atu.testers;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import ie.atu.hotel.*;

class ExceptionHandlerDemo {
  public static void main(String[] args) {
    ArrayList<Person> evacuationList = new ArrayList<Person>();
    evacuationList.add(new Guest(new Name("Mr", "John", "Jones"),
        "087 7654321",
        "johnjones@gmail.com",
        new PaymentCard(1111222233334444L, new Date(31, 12, 2029), 111)));

    evacuationList.add(new Guest(new Name("Mrs", "Sara", "Jones"),
        "087 1234567",
        "sara.jones@atu.ie",
        new PaymentCard(2222333344445555L, new Date(31, 12, 2028), 222)));

    evacuationList.add(new Employee(new Name("Mr", "Henry", "Smyth"),
        "086 1234567",
        new Date(20, 10, 1980),
        new Date(10, 10, 2012),
        42000.00));

    evacuationList.add(new Employee(new Name("Ms", "Lizzie", "Swan"),
        "087 1234567",
        new Date(18, 11, 1999),
        new Date(1, 1, 2020),
        2500000.00));

    for (Person p : evacuationList) {
      System.err.println(p);
    }

    boolean goodInput = false;
    do {
      try {
        String input = JOptionPane.showInputDialog(null,
            "Enter Person to View (" + 1 + " to " + evacuationList.size() + ")");
        int number = Integer.parseInt(input);
        JOptionPane.showMessageDialog(null, evacuationList.get(number - 1));
        goodInput = true;
      } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(null, "Invalid number");
      } catch (IndexOutOfBoundsException iobe) {
        JOptionPane.showMessageDialog(null,
            "Enter number between 1 and " + evacuationList.size());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Invalid number");
      }
    } while (!goodInput);
  }
}
