package ua.com.ex.reprository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ua.com.ex.model.Category;

public interface CategoryReprository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

	// String selectAllByJoinFetch = "SELECT c FROM Client c " +
	// "LEFT JOIN FETCH c.level " +
	// "LEFT JOIN FETCH c.status " +
	// "LEFT JOIN FETCH c.manager ";
	//
	// String selectActiveClient = "SELECT c FROM Client c WHERE c.active = true
	// ";
	//
	// @Query(selectActiveClient)
	// List<Client> findAllByFetch();
	//
	// @Query(selectActiveClient + "AND id = ?1")
	// Client findOneActive(Long clientId);
	//
	// @Query("SELECT c FROM Client c JOIN FETCH c.clientHistory")
	// List<Client> findAllAndFetchClientStatusHistoryEagly();

}
