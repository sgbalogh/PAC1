public class Sundae extends IceCream {
  private int cost;

  public Sundae(String iceCreamName, int iceCreamCost, String toppingName, int toppingCost) {
    super(toppingName + " Sundae with " + iceCreamName, iceCreamCost + toppingCost);
    cost = super.getCost();
  }

  public int getCost() {
    return cost;
  }
}
