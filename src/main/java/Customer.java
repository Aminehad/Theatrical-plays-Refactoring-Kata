public class Customer {
    String name;
    int numcust;
    int soldepoints;


    public Customer(String name, int numcust, int soldepoints) {
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
    public int getsoldepoints(){
      return soldepoints;
    }
    public void setsoldepoints(int soldepoints){
      this.soldepoints=soldepoints;
    }
}
