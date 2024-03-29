package org.example.persistence;

import org.example.exceptions.EntityNotFoundException;
import org.example.exceptions.InexistentEntityException;
import org.example.model.Entity;

/**
 * CRUD operations repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> -  type of entities saved in repository
 */
public interface IRepository<ID, E extends Entity<ID>>{
    /**
     * Returns the entity that has the given id
     * @param id ID - the id of the entity to be returned
     *           id must not be null
     * @return the entity with the specified id
     * @throws IllegalArgumentException if the id is null.
     * @throws InexistentEntityException if there is no entity with the given id
     */
    E findOne(ID id);

    /**
     * Returns all entities in the repository
     * @return - an iterable object that contains all entities
     */
    Iterable<E> getAll();

    /**
     * Clears the repository's data
     */
    void clear();

    /**
     * Add an entity
     * @param entity
     * @return
     */
    E add(E entity) ;

    /**
     * Update an entity
     * @param entity
     * @return
     * @throws InexistentEntityException
     * @throws IllegalArgumentException
     */
    void update(E entity);


    /**
     * Delete an entity
     * @param id
     * @throws InexistentEntityException
     * @throws IllegalArgumentException
     * @throws EntityNotFoundException
     */
    void delete(ID id);
}






//public interface IRepo <ID, E extends Entity<ID>> {
//    E add(E e) throws EntityRepoException;
//    E update(E e) throws EntityRepoException;
//    E remove(ID id) throws EntityRepoException;
//    E getById(ID id) throws EntityRepoException;
//    Iterable<E> getAll()throws EntityRepoException;
//} sau asa https://github.com/stefnmUBB/MPP_Lab/blob/master/Homework/lab2/java/repo/src/main/java/java/ro/ubbcluj/cs/stefnmubb/festivalsellpoint/repo/IRepo.java