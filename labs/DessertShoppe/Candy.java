public class Candy extends DessertItem {

  private double weight;
  private double pricepound;

  public Candy(String name, Double weight, int pricepound) {
    super(name);
    this.weight = weight;
    this.pricepound = pricepound;
  }

  public int getCost() {
    double cost = (weight * pricepound);
    double rounder = (cost - (int)cost);
    if (rounder >= .5) {
      cost++;
    }
    return (int)cost;
  }




}
