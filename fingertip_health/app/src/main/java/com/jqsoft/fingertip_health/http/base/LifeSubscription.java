package com.jqsoft.fingertip_health.http.base;

import rx.Subscription;

/**
 * Created by quantan.liu on 2017/3/21.
 */

public interface LifeSubscription{
    void bindSubscription(Subscription subscription);
}

