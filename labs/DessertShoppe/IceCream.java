public class IceCream extends DessertItem {
  private int cost;

  public IceCream(String name, int cost) {
    super(name);
    this.cost = cost;
  }

  public int getCost() {
    return cost;
  }

  public String getReceiptEntry() {
    String line = this.getName();
    for (int x = 0; x < (DessertShoppe.MAX_ITEM_NAME_SIZE - this.getName().length()); x++) {
      line += " ";
    }
    line += DessertShoppe.cents2dollarsAndCents(getCost());
    return line;
  }

}
