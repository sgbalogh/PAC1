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
    return "HOTLINE BLING";
  }

}
