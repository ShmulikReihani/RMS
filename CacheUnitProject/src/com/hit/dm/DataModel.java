package com.hit.dm;

import java.io.Serializable;

public class DataModel<T> implements Serializable{

	
	private Long id;
	private T content;

	public DataModel(Long id,T content)
	{
		this.id = id;
		this.content = content;
	}
	
	public Long getDataModelId()
	{
		return this.id;
	}
	
	public void setDataModelId(long id)
	{
		this.id = id;
	}
	
	public T getContent()
	{
		return this.content;
	}
	
	public void setContent(T content)
	{
		this.content = content;
	}

	@Override
	public boolean equals(Object obj) {

		if(obj instanceof DataModel<?>)
		{
			final DataModel<?> temp = (DataModel<?>) obj;
			if(this.id.equals(temp.id) && this.content.equals(temp.content) )
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		
		return id.hashCode() + content.hashCode();
	}

	@Override
	public String toString() {
		String s = "" + getDataModelId() +  " , " + getContent();
		return s;
	}
	
	
	
}
