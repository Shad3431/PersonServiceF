package cohort_65.java.personservice.person.dao;

import cohort_65.java.personservice.person.dto.CityPopulationDto;
import cohort_65.java.personservice.person.model.Child;
import cohort_65.java.personservice.person.model.Employee;
import cohort_65.java.personservice.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByAddressCityIgnoreCase(String city);

    List<Person> findByNameIgnoreCase(String name);

    List<Person> findByBirthDateBetween(LocalDate birthDateAfter, LocalDate birthDateBefore);

    @Query("SELECT new cohort_65.java.personservice.person.dto.CityPopulationDto(p.address.city, COUNT(p)) " +
            "FROM Person p GROUP BY p.address.city")
    List<CityPopulationDto> getCityPopulation();
    @Query("select e from Employee e where e.salary between :from and :to")
    List<Employee> findEmployeesBySalaryRange(@Param("from") int from, @Param("to") int to);

    @Query("select c from Child c")
    List<Child> findAllChildrenEntities();
}
