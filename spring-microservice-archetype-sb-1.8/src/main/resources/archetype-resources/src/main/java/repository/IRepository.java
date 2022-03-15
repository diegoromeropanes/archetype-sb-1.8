package ${package}.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ${package}.annotation.TimeDetector;
import ${package}.entity.ArchetypeEntity;
import feign.Param;

@Repository
public interface IRepository extends JpaRepository<ArchetypeEntity, Integer>{
	
	@TimeDetector
	@Query(value = "select a from ArchetypeEntity a where a.name = ?1")
	List<ArchetypeEntity> repositorySearchByName(String name);
	
	@TimeDetector
	@Modifying
	@Query(value = "insert into ARCHETYPE_SB (name, rut, dv, message, creation_date) values (:name, :rut, :dv, :message, sysdate)", nativeQuery = true)
	@Transactional
	void repositoryInsert(@Param("name") String name, @Param("rut") Integer rut, @Param("dv") String dv, @Param("message") String message);
	
	@TimeDetector
	@Modifying
	@Query(value = "update ARCHETYPE_SB set name = :name, message = :message where rut = :rut and dv = :dv", nativeQuery = true)
	@Transactional
	int repositoryUpdateByRut(@Param("name") String name, @Param("message") String message, @Param("rut") Integer rut, @Param("dv") String dv);
	
	@TimeDetector
	@Modifying
	@Query(value = "delete from ARCHETYPE_SB where rut = :rut", nativeQuery = true)
	@Transactional
	void repositoryDeleteByRut(@Param("rut") Integer rut);
}
