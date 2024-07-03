package id.dojo.model;

import id.dojo.DBConnect;
import id.dojo.helper.Res;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.Timestamp;
import java.util.List;

public class Payment {
    static Sql2o sql2o = DBConnect.getSql2o();
    private int payment_id;
    private int customer_id;
    private int rental_id;
    private int amount;
    private Timestamp payment_date;

    public static Sql2o getSql2o() {
        return sql2o;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getRental_id() {
        return rental_id;
    }

    public int getAmount() {
        return amount;
    }

    public Timestamp getPayment_date() {
        return payment_date;
    }

    public static Res<List<Payment>> fetchPayment(){
        try(Connection con = sql2o.open()){
            System.out.println("Connected to database");
            List<Payment> test = con.createQuery("SELECT payment_id, customer_id, rental_id, amount, payment_date FROM payment").executeAndFetch(Payment.class);
            return new Res<List<Payment>>("Berhasil fetch", test);
//        } catch(Sql2oException ex) {
//            return ex.toString();
        }
    }
}
