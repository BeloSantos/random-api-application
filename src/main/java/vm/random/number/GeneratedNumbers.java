package vm.random.number;

import java.util.ArrayList;
import java.util.List;

public class GeneratedNumbers {

  private static List<RandomNumber> generatedNumbers = null;


  public static List <RandomNumber> getInstance() {
    if(generatedNumbers == null)
      generatedNumbers = new ArrayList<RandomNumber>();

    return generatedNumbers;
  }


}
