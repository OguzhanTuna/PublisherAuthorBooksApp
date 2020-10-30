package com.bwcompany.publisherauthorbookapp.provider;

public interface IWrapperMethodProvider {
    //...

    default <T> T unwrap(Class<T> cls)
    {
        return cls.cast(this);
    }
}
