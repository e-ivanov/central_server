/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.domain.services;

/**
 *
 * @author killer
 */
public interface ISpecification<T> {
    
    public boolean isSatisfiedBy(T object);
    public ISpecification<T> and(ISpecification<T> other);
    public ISpecification<T> or(ISpecification<T> other);
    public ISpecification<T> not();
}
