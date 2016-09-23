package com.baseproject.data.models;

/**
 * Created by Nishant Shah on 21-Sep-16.
 */
public interface IModel<T,E> {
	T toViewModel();
	E toDomainModel();
}
