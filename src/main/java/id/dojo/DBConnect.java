package id.dojo;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DBConnect {
        private static Sql2o sql2o;

        static{
            sql2o = new Sql2o("jdbc:postgresql://localhost:5432/dvdrenal", "vigiaa", "vigia");
        }

        public static Sql2o getSql2o(){
            return sql2o;
        }

        public static Connection wrapConn(){
            return sql2o.open();
        }
}
