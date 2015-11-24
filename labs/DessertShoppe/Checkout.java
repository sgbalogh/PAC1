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
    // double subtotal = 0;
    // double totalTax = 0;
    // for (int i = 0; i < numberOfItems; i++) {
    //   subtotal += this.dessertArray[i].getCost();
    // }
    // totalTax = ((DessertShoppe.TAX_RATE / 100.0) * subtotal);
    // double rounder = (totalTax - (int)totalTax);
    // if (rounder >= .5) {
    //   totalTax++;
    // }
    // return (int)totalTax;
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
    String receipt = "";
    for (int i = 0; i < dessertArray.length; i++) {
      if (dessertArray[i] != null) {
        receipt += dessertArray[i].getName() + "\n";
      }
    }
    return receipt;
  }





}
