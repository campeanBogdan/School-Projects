package Model.Repostiroy.RepositoryImplementation;

import Model.Model.ClientBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClientBookRepositoryImplementation {

    private final JDBConnection jdbConnection;


    public ClientBookRepositoryImplementation(JDBConnection jdbConnection) {
        this.jdbConnection = jdbConnection;
    }



    public ClientBook getStock(Integer idBook) {
        Connection conn = jdbConnection.getConnection();
        ClientBook cb = null;
        try {
            String sql = "SELECT * FROM ass2.clientbook WHERE idbook=?";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idBook);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cb.setId(rs.getInt("id"));
                cb.setIdBook(rs.getInt("idbook"));
                cb.setIdClient(rs.getInt("idclient"));
                cb.setStock(rs.getInt("stock"));

                return cb;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
}
