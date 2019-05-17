import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Country;

public class JPATest {
	public static void main(String[] args) {
		 EntityManagerFactory factory = Persistence.createEntityManagerFactory("JpaHdbsqlTest");
         EntityManager em = factory.createEntityManager();
         // Read the existing entries and write to console
         Query q = em.createQuery("SELECT c FROM Country c");
         List<Country> userList = q.getResultList();
         
         for (Country user : userList) {
              System.out.println(user.getCountryName());
         }
         System.out.println("Size: " + userList.size());

//         // Create new user
//         em.getTransaction().begin();
//         User user = new User();
//         user.setName("Tom Johnson");
//         user.setLogin("tomj");
//         user.setPassword("pass");
//         em.persist(user);
//         em.getTransaction().commit();

         em.close();
	}
}
