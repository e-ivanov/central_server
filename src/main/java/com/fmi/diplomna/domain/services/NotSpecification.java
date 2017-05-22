/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.domain.services;


public class NotSpecification<T> extends CompositeSpecification<T> {

    private final ISpecification<T> other;

    public NotSpecification(ISpecification<T> other) {
        this.other = other;
    }
    
    @Override
    public boolean isSatisfiedBy(T object) {
        return !other.isSatisfiedBy(object);
    }
    
}
