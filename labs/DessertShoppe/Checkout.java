public class Checkout {

  private static int numberOfItems = 0;
  private DessertItem[] dessertArray = new DessertItem[100];


  public void enterItem(DessertItem d) {
    dessertArray[numberOfItems] = d;
    numberOfItems++;
  }

  public void clear() {
    numberOfItems = 0;
    dessertArray = new DessertItem[100];
  }

  public int totalTax() {
    double tax = this.totalCost() * (DessertShoppe.TAX_RATE / 100.00);
    double rounder = (tax - (int)tax);
    if (rounder >= .5) {
      tax++;
    }
    return (int)tax;
  }

  public int totalCost() {
    double subtotal = 0;
    for (int i = 0; i < numberOfItems; i++) {
      subtotal += this.dessertArray[i].getCost();
    }
    double rounder = (subtotal - (int)subtotal);
    if (rounder >= .5) {
      subtotal++;
    }
    return (int)subtotal;
  }

  public int numberOfItems() {
    return numberOfItems;
  }

  public String toString() {
    String receipt = "\n";
    String totalTax = "";
    String totalCost = "";
    int spacer = DessertShoppe.MAX_ITEM_NAME_SIZE - DessertShoppe.STORE_NAME.length();

    for (int t = 0; t < spacer; t++) {
      receipt += " ";
    }
    receipt += DessertShoppe.STORE_NAME + "\n";

    if (spacer > 0) {
      for (int x = 0; x < spacer; x++) {
        receipt += " ";
      }
    }

    for (int y = 0; y < DessertShoppe.STORE_NAME.length(); y++) {
      receipt += "-";
    }
    receipt += "\n\n";

    for (int i = 0; i < dessertArray.length; i++) {
      if (dessertArray[i] != null) {
        receipt += dessertArray[i].getReceiptEntry() + "\n";
      }
    }

    receipt += "\nTax";

    for (int x = 0; x < DessertShoppe.MAX_ITEM_NAME_SIZE - 3; x++){
      receipt += " ";
    }

    totalTax = DessertShoppe.cents2dollarsAndCents(totalTax());

    for (int y = 0; y < DessertShoppe.COST_WIDTH - totalTax.length(); y++) {
      receipt += " ";
    }

    receipt += totalTax + "\nTotal Cost";

    for (int x = 0; x < DessertShoppe.MAX_ITEM_NAME_SIZE - 10; x++){
      receipt += " ";
    }

    totalCost = DessertShoppe.cents2dollarsAndCents(totalTax() + totalCost());
    for (int y = 0; y < DessertShoppe.COST_WIDTH - totalCost.length(); y++) {
      receipt += " ";
    }
    
    receipt += totalCost;

    return receipt;
  }





}
