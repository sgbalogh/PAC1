public class SampleTest {
  public static void main(String[] args) {

    Checkout myCheckout = new Checkout();

    myCheckout.enterItem(new Sundae("Vanilla Ice Cream",115, "Caramel", 50));
    myCheckout.enterItem(new Sundae("Vanilla Ice Cream",115, "Caramel", 50));
    myCheckout.enterItem(new Sundae("Vanilla Ice Cream",115, "Caramel", 50));


    System.out.println(myCheckout.totalTax());
    System.out.println(myCheckout.totalCost());





  }



}
