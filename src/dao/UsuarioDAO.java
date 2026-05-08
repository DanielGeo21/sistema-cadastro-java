/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import factory.ConnectionFactory; 
import modelo.Usuario; 
import java.sql.*; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author DANIEL_5332
 */
public class UsuarioDAO {
     private Connection connection; 
    Long id; 
    String nome; 
    String cpf; 
    String email; 
    String telefone; 
    String celular;
    String rua;
    String senha;
 
 
    public UsuarioDAO(){ 
       this.connection = new ConnectionFactory().getConnection(); 
}
    
     public void adiciona(Usuario usuario){
         String sql = "INSERT INTO usuario(nome,cpf,email,telefone,celular,rua,senha) VALUES(?,?,?,?,?,?,?)"; 
try { 
PreparedStatement stmt = connection.prepareStatement(sql); 
stmt.setString(1, usuario.getNome()); 
stmt.setString(2, usuario.getCpf()); 
stmt.setString(3, usuario.getEmail()); 
stmt.setString(4, usuario.getTelefone());
stmt.setString(5, usuario.getCelular());
stmt.setString(6, usuario.getRua());
stmt.setString(7, usuario.getSenha());
stmt.execute(); 
stmt.close();
     }  catch (SQLException u) { 
throw new RuntimeException(u); 
} 
     }
     public Usuario autenticar(String email, String senha) {
    String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, senha);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setCpf(rs.getString("cpf"));
            usuario.setEmail(rs.getString("email"));
            usuario.setTelefone(rs.getString("telefone"));
            usuario.setCelular(rs.getString("celular"));
            usuario.setRua(rs.getString("rua"));
            usuario.setSenha(rs.getString("senha"));

            rs.close();
            stmt.close();

            return usuario; // login válido
        }

        rs.close();
        stmt.close();
        return null; // login inválido

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
     
}

