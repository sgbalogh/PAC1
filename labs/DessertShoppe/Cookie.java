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

  public String getReceiptEntry() {
    String line = this.getName();
    for (int x = 0; x < (DessertShoppe.MAX_ITEM_NAME_SIZE - this.getName().length()); x++) {
      line += " ";
    }
    for (int y = 0; y < (DessertShoppe.COST_WIDTH - DessertShoppe.cents2dollarsAndCents(getCost()).length()); y++) {
      line += " ";
    }
    line += DessertShoppe.cents2dollarsAndCents(getCost());
    line = this.count + " @ " + DessertShoppe.cents2dollarsAndCents((int)this.pricedozen) + " /dz.\n" + line;
    return line;
  }

}
