package com.lecarlink.zframwork.present;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import com.lecarlink.zframwork.mvp.ZActivityViewer;
import com.lecarlink.zframwork.mvp.ZBasePresenter;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@Deprecated
public class ABFragment extends Fragment implements ZActivityViewer {

    @Override
    public void showToastMessage(String message) {
    }

    @Override
    public void showInfoDialog(String message) {
    }

    @Override
    public void showInfoDialog(String title, String message) {
    }

    @Override
    public void showLoadingDialog(String message) {
    }

    @Override
    public void cancelLoadingDialog() {
    }

    @Override
    public void showInfoDialog(String title, String message, String okButtonText) {
    }

    @Override
    public void registerPresenter(ZBasePresenter presenter) {
    }

    @Override
    public void closeAllTask() {
    }
}
