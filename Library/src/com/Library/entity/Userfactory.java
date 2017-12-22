package com.Library.entity;

import java.io.Serializable;

public class Userfactory implements Serializable, IUser {

	/**
	 * 序列号，无法更改
	 */
	private static final long serialVersionUID = -7389715187134163658L;

	@Override
	public Object getUser(UserInfor userInfor) {
		Object object = null;
		if(userInfor.getUserTypeID() == 0)
		{
			object = new Admin(userInfor);
		}
		else if(userInfor.getUserTypeID() == 1)
		{
			object = new Student(userInfor);
		}
		else if(userInfor.getUserTypeID() == 2)
		{
			object = new Teacher(userInfor);
		}
		return object;
	}

}
