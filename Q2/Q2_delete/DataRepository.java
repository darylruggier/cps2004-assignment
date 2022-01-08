package Q2;

public class DataRepository {
  // can only have zero or one instanced object of the class DataRepository... ever
  private static DataRepository singleton = null;
  // privatising the constructor so that it cannot be instanced outside of the class itself
  private DataRepository() {}
  public static DataRepository get() {
    if (singleton == null) { // this only happens the first time
      singleton = new DataRepository();
    }
    return singleton;
  }

  private int data = 0;
  public void setInt(int data) {
    this.data = data;
  }
  public int getInt() {
    return data;
  }

  public void addOrder(Crypto crypto) {

    //   OrderBook.update();
  }
}