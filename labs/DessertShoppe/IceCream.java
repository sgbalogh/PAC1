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
    int lineLen = this.getName().length();
    for (int x = 0; x < (DessertShoppe.MAX_ITEM_NAME_SIZE - lineLen); x++) {
      line += " ";
    }

    for (int y = 0; y < (DessertShoppe.COST_WIDTH - DessertShoppe.cents2dollarsAndCents(getCost()).length()); y++) {
      line += " ";
    }

    line += DessertShoppe.cents2dollarsAndCents(getCost());
    return line;
  }

}
