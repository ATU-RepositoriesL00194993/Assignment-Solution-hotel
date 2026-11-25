package ie.atu.testers;

import ie.atu.hotel.Name;
import java.util.ArrayList;

public class NameTester {
  public static void main(String[] args) {
    Name Bob = new Name("Mr", "Bob", "Brown");
    Name Bob2 = new Name("Mr", "Bob", "Brown");
    Name Alice = new Name("Ms", "Alice", "Doe");

    System.out.println("toString: " + Bob);
    System.out.println("is Bob female: " + Bob.isFemale());
    System.out.println("is Alice female: " + Alice.isFemale());
    System.out.println("Bob equals Bob2: " + Bob.equals(Bob2));
    System.out.println("Bob not equals Alice: " + Bob.equals(Alice));

    System.out.println("---------");
    System.out.println("ARRAY LIST");
    System.out.println("---------");

    ArrayList<Name> names = new ArrayList<Name>();
    names.add(Alice);
    names.add(Bob);
    names.add(Bob2);
    names.add(new Name());

    for (Name n : names) {
      System.out.println("toSring:  " + n);
      System.out.println("isFemale: " + n.isFemale());
    }
  }
}
