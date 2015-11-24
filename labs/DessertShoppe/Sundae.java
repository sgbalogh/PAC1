public class Sundae extends IceCream {

  public Sundae(String iceCreamName, int iceCreamCost, String toppingName, int toppingCost) {
    super(toppingName + " Sundae", iceCreamCost + toppingCost);
    this.name = this.name + " with\n" + iceCreamName;
  }

}
