import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String dbURL = "jdbc:mysql://localhost:3306/estudiantes";
        String dbUsername = "root";
        String dbPassword = "";
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword)) {
            // INSERT
            String sqlInsert = "INSERT INTO calificaciones VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sqlInsert)) {
                System.out.println("Ingrese el ID:");
                int id = Integer.parseInt(scanner.nextLine());

                System.out.println("Ingrese el nombre:");
                String nombre = scanner.nextLine();

                System.out.println("Ingrese el número de identificación:");
                int cedula = Integer.parseInt(scanner.nextLine());

                System.out.println("Ingrese la calificación:");
                float calificacion1 = Float.parseFloat(scanner.nextLine());

                System.out.println("Ingrese la calificación 2:");
                float calificacion2 = Float.parseFloat(scanner.nextLine());

                statement.setInt(1, id);
                statement.setString(2, nombre);
                statement.setInt(3, cedula);
                statement.setFloat(4, calificacion1);
                statement.setFloat(5, calificacion2);

                int rowsInserted = statement.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Se insertó un nuevo registro");
                }
            }

            // SELECT
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM calificaciones")) {
                int id;
                String nombre;

                while (rs.next()) {
                    id = rs.getInt("id");
                    nombre = rs.getString("nombre");
                    System.out.println(id + " " + nombre);
                }
            }

            // UPDATE
            String sqlUpdate = "UPDATE calificaciones SET nombre = ?, cedula = ?, calificacion1 = ?, calificacion2 = ? WHERE id = ?";
            try (PreparedStatement statementUpdate = conn.prepareStatement(sqlUpdate)) {
                System.out.println("Ingrese el ID del registro a actualizar:");
                int idUpdate = Integer.parseInt(scanner.nextLine());

                System.out.println("Ingrese el nuevo nombre:");
                String nombreUpdate = scanner.nextLine();

                System.out.println("Ingrese el nuevo número de identificación:");
                int cedulaUpdate = Integer.parseInt(scanner.nextLine());

                System.out.println("Ingrese la nueva calificación 1:");
                float calificacion1Update = Float.parseFloat(scanner.nextLine());

                System.out.println("Ingrese la nueva calificación 2:");
                float calificacion2Update = Float.parseFloat(scanner.nextLine());

                statementUpdate.setString(1, nombreUpdate);
                statementUpdate.setInt(2, cedulaUpdate);
                statementUpdate.setFloat(3, calificacion1Update);
                statementUpdate.setFloat(4, calificacion2Update);
                statementUpdate.setInt(5, idUpdate);

                int rowsUpdated = statementUpdate.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("El registro fue actualizado exitosamente");
                }
            }

            // DELETE
            String sqlDelete = "DELETE FROM calificaciones WHERE id = ?";
            try (PreparedStatement statementDelete = conn.prepareStatement(sqlDelete)) {
                System.out.println("Ingrese el ID del registro a eliminar:");
                int idDelete = Integer.parseInt(scanner.nextLine());

                statementDelete.setInt(1, idDelete);

                int rowsDeleted = statementDelete.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("El registro fue eliminado exitosamente");
                }
            }

        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
