package com.dayi.mybatis.spring.plus.support.ext;

import com.dayi.mybatis.spring.plus.support.Criterion;

import java.util.Collection;

/**
 * @author chenzhaoju
 */
public class Conjunction extends Junction {

    public Conjunction() {
        super( Nature.AND );
    }

    protected Conjunction(Criterion... criterion) {
        super( Nature.AND, criterion );
    }

    public Conjunction(Collection<Criterion> criterions) {
        super(Nature.AND, criterions);
    }
}
