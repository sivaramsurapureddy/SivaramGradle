package com.synergy.recupro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.synergy.recupro.model.Requirements;
@CrossOrigin(origins = "*") 
@PreAuthorize("hasAnyRole('ADMIN','RECRUITMENT_LEAD','BDM','TEAM','ACCOUNT_MANAGER','USER')")
//Modified from @Repository to @RepositoryRestResource the annotation to add the one to many requirement association
@RepositoryRestResource
public interface RequirementRepository extends JpaRepository<Requirements, Long> {
	 @PreAuthorize("hasAnyRole('USER', 'ADMIN','RECRUITMENT_LEAD')")
	List<Requirements> findByAccountsId(Long account_id);
	
	@PreAuthorize("hasAnyRole('ADMIN','RECRUITMENT_LEAD')")
	@Override
	public List<Requirements> findAll();

	@PreAuthorize("hasAnyRole('USER', 'ADMIN','RECRUITMENT_LEAD')")
	public Optional<Requirements> findById(Long id);

	@PreAuthorize("hasAnyRole('ADMIN','RECRUITMENT_LEAD')")
	@Override
	public <S extends Requirements> List<S> findAll(Example<S> example);
// Commented for now to test via spring data rest calls
//	@PreAuthorize("hasAnyRole('ADMIN','RECRUITMENT_LEAD','BDM','TEAM','ACCOUNT_MANAGER','USER')")
//	@Override
//	public <S extends Requirements> S save(S entity);

//	@PreAuthorize("hasAnyRole('ADMIN','RECRUITMENT_LEAD','BDM','TEAM','ACCOUNT_MANAGER','USER')")
//	@Override
//	public <S extends Requirements> List<S> saveAll(Iterable<S> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public void delete(Requirements entity);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public void deleteAll();

//	@PreAuthorize("hasRole('ADMIN')")
//	@Override
//	public void deleteById(Long id);

	
	
}
