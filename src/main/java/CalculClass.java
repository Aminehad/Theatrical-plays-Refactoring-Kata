public class CalculClass {
    static String TRAGEDY = "tragedy";
    static String COMEDY = "comedy";
    

        public double calcul(Performance perf,Play play,Customer cus){
        Double thisAmount = 0.0;// un double pour les montants

      switch (play.type) {
        case TRAGEDY:
          thisAmount = 400.00;// 400.0 au lieu de 40000
          if (perf.audience > 30) {
            thisAmount += 10 * (perf.audience - 30);// 10 au lieu de 1000
          }
          if(cus.soldepoints>150){
            thisAmount-=15;
            cus.soldepoints-=150;
            }
          break;
        case COMEDY:
          thisAmount = 300.00;// 300.0 au lieu de 30000
          if (perf.audience > 20) {
            thisAmount += 100 + 5.0 * (perf.audience - 20); // 100 au lieu de 10000 et 5.0 au lieu de 500
          }
          thisAmount += 3.00 * perf.audience; // 3.0 au lieu de 300
           if(cus.soldepoints>150){
            thisAmount-=15;
            cus.soldepoints-=150;
          }
          break;
       
      }     
       
        return thisAmount;
    }
}
