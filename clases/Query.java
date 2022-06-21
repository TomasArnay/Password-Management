package clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    public int queryAdd(String name, String account, String pass){
        String consulta = "insert into registros(nombre, cuenta, contrasenia) values (?, ?, ?);";
        try {
            MyConnection c = new MyConnection();
            st = c.getConnection().createStatement();
            rs = st.executeQuery("select * from registros where nombre like'" + name + "'");
            if (rs.next()){ //Analiza si existe un registro con ese dni
                return 0;
            }else{
                PreparedStatement ps = c.getConnection().prepareStatement(consulta);
                ps.setString(1, name);
                ps.setString(2, account);
                ps.setString(3, pass);
                ps.executeUpdate();

                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 2;
        }
    }

    public int deleteRecords(String name) throws SQLException {
        String query = "delete from registros where nombre = '" + name + "'";
        int amountBefore = amountRecords();

        MyConnection c = new MyConnection();
        st = c.getConnection().createStatement();
        PreparedStatement remove = c.getConnection().prepareStatement(query);
        remove.executeUpdate();

        int amountAfter = amountRecords();
        if (amountBefore == amountAfter){
            return 0;
        }else{
            return 1;
        }
    }

    public int amountRecords(){
        MyConnection c = new MyConnection();
        String query = "select * from registros;";
        int i = 0;
        try{
            st = c.getConnection().createStatement();
            rs = st.executeQuery(query);
            if (rs.next()){ //Analiza si existe un registro
                do {
                    i++;
                } while (rs.next()); //Itera hasta que no haya otro registro
            }
        }catch (SQLException error){
            System.out.println("Error: " + error.getMessage());
        }
        return i;
    }

    public int updateRecord(String[] data){
        MyConnection c = new MyConnection();
        int result = 0;
        String query = "update registros set nombre = '" + data[0] +
                "', cuenta = '" + data[1] +
                "', contrasenia = '" + data[2] +
                "' where idregistro = " + data[3] + ";";

        try {
            st = c.getConnection().createStatement();
            result = st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Statement st;
    private ResultSet rs;
}
