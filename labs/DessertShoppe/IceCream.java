public class IceCream extends DessertItem {
  private int cost;

  public IceCream(String name, int cost) {
    super(name);
    this.cost = cost;
  }

  public int getCost() {
    return cost;
  }

}
