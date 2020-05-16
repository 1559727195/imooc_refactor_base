package com.imooc.ft_home.constant;

import android.Manifest;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/16 17:55
 * Used
 */
public class Constant {

	/**
	 * 权限常量相关
	 */
	public static final int WRITE_READ_EXTERNAL_CODE = 0x01;
	public static final String[] WRITE_READ_EXTERNAL_PERMISSION = new String[] {
			Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
	};

	public static final int HARDWEAR_CAMERA_CODE = 0x02;
	public static final String[] HARDWEAR_CAMERA_PERMISSION =
			new String[] { Manifest.permission.CAMERA };


	/**
	 * Router相关
	 */


}
