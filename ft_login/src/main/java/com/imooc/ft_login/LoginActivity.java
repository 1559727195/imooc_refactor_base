package com.imooc.ft_login;

import com.imooc.ft_login.inter.IUserLoginView;
import com.imooc.ft_login.presenter.UserLoginPresenter;
import com.imooc.lib_commin_ui.base.BaseActivity;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/14 19:50
 * Used
 */
public class LoginActivity extends BaseActivity implements IUserLoginView {

	private UserLoginPresenter mUserLoginPresenter;

	



	@Override
	public String getUserName() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public void finishActivity() {

	}

	@Override
	public void showLoginFailedView() {

	}

	@Override
	public void showLoadingView() {

	}

	@Override
	public void hideLoadingView() {

	}
}
