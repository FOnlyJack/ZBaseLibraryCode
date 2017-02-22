package com.lecarlink.zframwork.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.lecarlink.zframwork.R;
import java.util.List;
/**
* Container   Fragment
*/
public class BaseContainerFragment extends BaseFragment {

	private final static String LOG_TAG = BaseContainerFragment.class.getSimpleName();

	private int mContainerViewId;
	protected Class<BaseFragment> mHomefrgClass;

	public static <V extends BaseFragment>
	BaseContainerFragment newInstance(Class<V> homefrgClass) {
		return newInstance(0,homefrgClass);
	}

	public static <V extends BaseFragment>
	BaseContainerFragment newInstance(int containerViewId, Class<V> homefrgClass) {
		BaseContainerFragment fragment = new BaseContainerFragment();

        fragment.mContainerViewId = containerViewId;
        fragment.mHomefrgClass = (Class<BaseFragment>) homefrgClass;

		return fragment;
	}


	@Override
	public int getLayoutRes() {
		return mContainerViewId > 0? mContainerViewId:R.layout.fragment_container;
	}

	@Override
	public void initView() {
		View view = getLayoutView().findViewById(R.id.container_fragment);
		if (view == null || !(view instanceof FrameLayout)) {
			throw new RuntimeException(
					"Your BaseContainerFragment must have a FrameLayout whose id attribute is 'R.id.container'");
		}
	}

	@TargetApi(Build.VERSION_CODES.KITKAT)
	@Override
	public void initBusiness() {
		if ( !mIsViewInited ) {
			if (mHomefrgClass != null){
				try {
					replace(mHomefrgClass.newInstance(), false);
				} catch (java.lang.InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static String makeTag(int index) {
		return BaseContainerFragment.class.toString() + ":" + index;
	}

	public void replace(Fragment fragment, boolean addToBackStack) {
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		if (addToBackStack) {
			transaction.addToBackStack(null);
		}
		transaction.replace(R.id.container_fragment, fragment);
		transaction.commit();
		getChildFragmentManager().executePendingTransactions();
	}
	
	public boolean pop() {
		Log.e("test", "pop fragment: " + getChildFragmentManager().getBackStackEntryCount());
		boolean isPop = false;
		if (getChildFragmentManager().getBackStackEntryCount() > 0) {
			isPop = true;
			getChildFragmentManager().popBackStack();
		}
		return isPop;
	}

	public void add(Fragment fragment, boolean addToBackStack){
		FragmentTransaction transaction = hideAll();
		transaction.add(R.id.container_fragment, fragment);
		if (addToBackStack) {
			transaction.addToBackStack(null);
		}
		transaction.commit();
		getChildFragmentManager().executePendingTransactions();
	}

	public void hide(Fragment fragment){
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.hide(fragment);
		transaction.commit();
		getChildFragmentManager().executePendingTransactions();
	}

	public FragmentTransaction hideAll() {
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		List<Fragment> fragments = getChildFragmentManager().getFragments();

		if (null != fragments) {
			for (Fragment fragment:fragments) {
				if (fragment==null) {
					continue;
				}
				transaction.hide(fragment);
			}
		}
		return transaction;
	}

	public void show(Fragment fragment){
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.show(fragment);
		transaction.commit();
		getChildFragmentManager().executePendingTransactions();
	}

	public BaseFragment backToFragment(String fragmentName) {
		BaseFragment fragment = null;
		int count = getChildFragmentManager().getBackStackEntryCount();
		while(count > 0) {
			fragment = (BaseFragment) getChildFragmentManager().getFragments().get(count);
			String name = getChildFragmentManager().getBackStackEntryAt(count - 1).getName();
			if(name.equals(fragmentName)){
				break;
			}
			getChildFragmentManager().popBackStack();
			count--;
		}
		return fragment;
	}
}
