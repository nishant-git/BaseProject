package com.baseproject.data.models;

/**
 * Created by Nishant Shah on 21-Sep-16.
 */
public class PostModel implements IModel {

	public int userId;
	public int id;
	public String title;
	public String body;

	@Override public Object toViewModel() {
		return null;
	}

	@Override public Object toDomainModel() {
		return null;
	}
}