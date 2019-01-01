package org.horaapps.leafpic.activities.base;

import android.support.annotation.CallSuper;

import org.horaapps.liz.ThemedActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends ThemedActivity {
    CompositeDisposable disposables = new CompositeDisposable();
    String val = "";

    public void disposeLater(Disposable disposable) {
        disposables.add(disposable);
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        disposables.dispose();
        super.onDestroy();
    }

    public String getVal()
    {
        return val;
    }

    public void setVal(String v)
    {
        val = v;
    }
}
