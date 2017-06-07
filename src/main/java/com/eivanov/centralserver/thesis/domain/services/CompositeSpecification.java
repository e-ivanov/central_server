/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.domain.services;


public abstract class CompositeSpecification<T> implements ISpecification<T> {


    public abstract boolean isSatisfiedBy(T object);

    @Override
    public ISpecification<T> and(ISpecification<T> other) {
        return new AndSpecification<T>(this, other);
    }

    @Override
    public ISpecification<T> or(ISpecification<T> other) {
        return new OrSpecification<T>(this, other);
    }

    @Override
    public ISpecification<T> not() {
        return new NotSpecification<T>(this);
    }
    
}
