package com.synergy.recupro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.synergy.recupro.model.Accounts;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
//@Repository
@RepositoryRestResource(path = "Accountses")
//@PreAuthorize("hasAnyRole('ADMIN','RECRUITMENT_LEAD','BDM','TEAM','ACCOUNT_MANAGER','USER')")
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
	@ApiOperation("Find All Accounts")
//	@PreAuthorize("hasAnyRole('RECRUITMENT_LEAD', 'ADMIN')")
	@Override
	public List<Accounts> findAll();

	@ApiOperation("Find Account By ID")
	@PreAuthorize("hasAnyRole('RECRUITMENT_LEAD', 'ADMIN')")
	public Optional<Accounts> findById(Long id);

	@ApiOperation("Find All Accounts for Matching Input")
	@PreAuthorize("hasAnyRole('RECRUITMENT_LEAD', 'ADMIN')")
	@Override
	public <S extends Accounts> List<S> findAll(Example<S> example);

	@ApiOperation("Save Account")
	@PreAuthorize("hasAnyRole('ADMIN','RECRUITMENT_LEAD','BDM','TEAM','ACCOUNT_MANAGER','USER')")
	@Override
	public <S extends Accounts> S save(S entity);

//	@ApiOperation("saves a new Address")
//	@PreAuthorize("hasAnyRole('ADMIN','RECRUITMENT_LEAD','BDM','TEAM','ACCOUNT_MANAGER','USER')")
//	@Override
//	public <S extends Accounts> List<S> saveAll(Iterable<S> entities);

	@ApiOperation("Delete An Account")
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public void delete(Accounts entity);

	@ApiOperation("Delete All Accounts")
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public void deleteAll();

//	@ApiOperation("saves a new Address")
//	@PreAuthorize("hasRole('ADMIN')")
//	@Override
//	public void deleteById(Long id);

}
