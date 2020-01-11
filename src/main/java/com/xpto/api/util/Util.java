package com.xpto.api.util;

import java.util.ArrayList;
import java.util.List;

public class Util {

	public static final <T>List<T> castObjectList(List<?> oldList, Class<T> c){
	    List<T> newList = new ArrayList<T>();
	    for (Object o : oldList) {
	    	if(o != null && c.isAssignableFrom(o.getClass())) {
	    		newList.add(c.cast(o));
	    	}
	    }
	    return newList;
	}
}