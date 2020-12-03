package com.example.java_background2.repository;

import android.os.Handler;

import com.example.java_background2.Result;

import java.util.concurrent.Executor;

public class NumberRepository {
    private final Handler resultHandler;
    private final Executor executor; //final이 없으면  null일 수도 있기 때문에.

    public NumberRepository(Handler resultHandler, Executor executor) {
        this.resultHandler = resultHandler;
        this.executor = executor;
    }

    public void longTask(final RepositoryCallback<Integer> callback) {
        executor.execute(() -> {
            try {
                //background
                int num = 0;
                for (int i = 0; i < 100; i++) {
                    num++;
                    //UI 갱신을 위해서 콜백
                    Result<Integer> result = new Result.Success<>(num);
                    //callback.onComplete(result);
                    notifyResult(result, callback);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Result<Integer> result =  new Result.Error<>(e);
                notifyResult(result, callback);
            }
        });
    }

    private void notifyResult(final Result<Integer> result, final RepositoryCallback<Integer> callback) {
        resultHandler.post(() -> callback.onComplete(result));
    }
}
