public class Sundae extends IceCream {

  private String iceCreamName = "";

  public Sundae(String iceCreamName, int iceCreamCost, String toppingName, int toppingCost) {
    super(toppingName + " Sundae", iceCreamCost + toppingCost);
    this.iceCreamName = iceCreamName;
  }

  public String getIceCream() {
    return this.iceCreamName;
  }

  public String getReceiptEntry() {

    String line1 = this.getName();
    String line2 = "";
    int line2Len = 0;

    if ((line1.length() + 5) <= DessertShoppe.MAX_ITEM_NAME_SIZE) {
      line1 += " with";
      line2 = this.iceCreamName;
    } else {
      line2 = "with " + this.iceCreamName;
    }

    if (line2.length() > DessertShoppe.MAX_ITEM_NAME_SIZE) {
      line2 = line2.substring(0,DessertShoppe.MAX_ITEM_NAME_SIZE);
    }
    line2Len = line2.length();
    for (int x = 0; x < DessertShoppe.MAX_ITEM_NAME_SIZE - line2Len; x++) {
      line2 += " ";
    }

    for (int y = 0; y < (DessertShoppe.COST_WIDTH - DessertShoppe.cents2dollarsAndCents(getCost()).length()); y++) {
      line2 += " ";
    }

    line2 += DessertShoppe.cents2dollarsAndCents(getCost());
    return line1 + "\n" + line2;
  }

}
