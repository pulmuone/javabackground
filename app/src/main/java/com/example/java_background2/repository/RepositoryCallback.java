package com.example.java_background2.repository;

import com.example.java_background2.Result;

public interface RepositoryCallback<T> {
    void onComplete(Result<T> result);
}