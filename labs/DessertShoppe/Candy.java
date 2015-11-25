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

  public String getReceiptEntry() {
    String line = this.getName();
    for (int x = 0; x < (DessertShoppe.MAX_ITEM_NAME_SIZE - this.getName().length()); x++) {
      line += " ";
    }
    for (int y = 0; y < (DessertShoppe.COST_WIDTH - DessertShoppe.cents2dollarsAndCents(getCost()).length()); y++) {
      line += " ";
    }
    line += DessertShoppe.cents2dollarsAndCents(getCost());
    line = String.format("%.2f",this.weight) + " lbs. @ " + DessertShoppe.cents2dollarsAndCents((int)this.pricepound) + " /lb.\n" + line;
    return line;
  }



}
