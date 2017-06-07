/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.domain.services;

import jdk.nashorn.internal.parser.TokenType;


public class OrSpecification<T> extends CompositeSpecification<T> {

    
    private final ISpecification<T> left;
    private final ISpecification<T> right;

    public OrSpecification(ISpecification<T> left, ISpecification<T> right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public boolean isSatisfiedBy(T object) {
        return left.isSatisfiedBy(object) || right.isSatisfiedBy(object);
    }
    
}
