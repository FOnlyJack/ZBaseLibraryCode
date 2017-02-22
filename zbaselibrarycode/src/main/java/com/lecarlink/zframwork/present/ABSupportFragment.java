package com.lecarlink.zframwork.present;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.lecarlink.zframwork.mvp.ZActivityViewer;
import com.lecarlink.zframwork.mvp.ZBasePresenter;


public class ABSupportFragment extends Fragment implements ZActivityViewer{

    private ZBasePresenter presenter;

    @Override
    public void showToastMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInfoDialog(String message) {
        showInfoDialog(null, message);
    }

    @Override
    public void showInfoDialog(String title, String message) {
        new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public void showLoadingDialog(String message) {

    }

    @Override
    public void cancelLoadingDialog() {

    }

    @Override
    public void showInfoDialog(String title, String message, String okButtonText) {
        new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(okButtonText, null)
                .show();
    }

    @Override
    public void registerPresenter(ZBasePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void closeAllTask() {
        if(null != presenter){
            presenter.closeAllTask();
        }
    }
}
