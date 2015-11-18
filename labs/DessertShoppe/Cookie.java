public class Cookie extends DessertItem {

private int count;
private int pricedozen;

  public Cookie(String name, int count, int pricedozen) {
    super(name);
    this.count = count;
    this.pricedozen = pricedozen;
  }

  public int getCost() {
    double cost = (count / 12.0) * pricedozen;
    double rounder = (cost - (int)cost);
    if (rounder >= .5) {
      cost++;
    }
    return (int)cost;
  }

}
