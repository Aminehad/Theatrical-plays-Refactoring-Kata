public class Customer {
    String name;
    int numcust;
    double soldepoints;


    public Customer(String name, int numcust, double soldepoints) {
        this.name = name;
        this.numcust = numcust;
        this.soldepoints = soldepoints;
    }
    public String getname(){
        return name;
    }
    public int getnumcust(){
      
      return numcust;
    }
    public double getsoldepoints(){
      return soldepoints;
    }
    public void setsoldepoints(double soldepoints){
      this.soldepoints=soldepoints;
    }
}
