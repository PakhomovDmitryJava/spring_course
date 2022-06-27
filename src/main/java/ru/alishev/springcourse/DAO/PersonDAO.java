package ru.alishev.springcourse.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
/*
    Here we use JdbcTemplate
*/

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return  jdbcTemplate.query("SELECT * FROM Person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Optional <Person> show(String email){
        return jdbcTemplate.query("SELECT * FROM Person WHERE email=?",
                new Object[]{email},
                new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void save(Person updatedPerson) {
        jdbcTemplate.update("INSERT INTO Person(name, age, email, address) VALUES(?, ?, ?, ?)",
                updatedPerson.getName(),
                updatedPerson.getAge(),
                updatedPerson.getEmail(),
                updatedPerson.getAddress());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=?, address=? WHERE id=?",
                updatedPerson.getName(),
                updatedPerson.getAge(),
                updatedPerson.getEmail(),
                updatedPerson.getAddress(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}


//-------------------------------------------------
        /*
        This is connection with JDBC
        */
//
//    private static int PEOPLE_COUNT;
//
//    private static final String URL
//            = "jdbc:postgresql://localhost:5432/first_db";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "123123";
//
//
//    private static Connection connection;
//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            connection = DriverManager.getConnection
//                    (URL,USERNAME,PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<Person> index() {
//        List<Person> people = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT * FROM Person";
//            ResultSet resultSet = statement.executeQuery(SQL); // receiving data only
//
//            while (resultSet.next()){
//                Person person = new Person();
//
//                person.setId(resultSet.getInt("id"));
//                person.setName(resultSet.getString("name"));
//                person.setAge(resultSet.getInt("age"));
//                person.setEmail(resultSet.getString("email"));
//
//                people.add(person);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return people;
//    }
//
//    public void save(Person person) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement
//                    ("INSERT INTO Person VALUES (1, ?, ?, ?)");
//
//            preparedStatement.setString(1, person.getName());
//            preparedStatement.setInt(2, person.getAge());
//            preparedStatement.setString(3, person.getEmail());
//
//            preparedStatement.executeUpdate();
//
//            //    This is a bad way to get a statement cause our database
//            //    doesn't protected from SQL injections.
//            //
//           Statement statement = connection.createStatement();
//           String SQL = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() +
//                   "'," + person.getAge() + ",'" + person.getEmail() + "')";
//         //INSERT INTO VALUES (1, 'Tom', 'asd@asdsf.com'
//           statement.executeUpdate(SQL); // update data only
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Person show(int id) {
//        Person person = null;
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("SELECT * FROM Person WHERE id=?");
//
//            preparedStatement.setInt(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            resultSet.next();
//
//            person = new Person();
//
//            person.setId(resultSet.getInt("id"));
//            person.setName(resultSet.getString("name"));
//            person.setAge(resultSet.getInt("age"));
//            person.setEmail(resultSet.getString("email "));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return person;
//    }
//
//    public void update(int id, Person updatedPerson) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement
//                    ("UPDATE Person SET name=?, age=?, email=? where id=?");
//
//            preparedStatement.setString(1, updatedPerson.getName());
//            preparedStatement.setInt(2, updatedPerson.getAge());
//            preparedStatement.setString(3, updatedPerson.getEmail());
//            preparedStatement.setInt(4, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void delete(int id) {
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("DELETE FROM Person WHERE id=?");
//            preparedStatement.setInt(1, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//________________________________________________________
/*We were doing this without database, with ArrayLsit.*/
//
//    public void delete(int id) {
//        people.removeIf(p -> p.getId() == id);
//    }
//
//
//    public List<Person> index() {
//        return people;
//    }
//
//    public Person show(int id) {
//        return people
//                .stream()
//                .filter(person -> person.getId() == id)
//                .findAny().orElse(null);
//    }
//
//    public void save(Person person) {
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
//    }
//
//    public void update(int id, Person updatedPerson) {
//        Person personToBeUpdated = show(id);
//
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
//    }
//
//    public void delete(int id) {
//        people.removeIf(p -> p.getId() == id);
//    }

