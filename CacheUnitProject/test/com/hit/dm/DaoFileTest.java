package com.hit.dm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hit.dao.DaoFileImpl;

class DaoFileTest {

	@Test
	void test() {
		
		DaoFileImpl<String> dao = new DaoFileImpl<>("resources\\datasource.txt");
		
		DataModel<String> data;
		
		data = new DataModel<String>(1111111111l, "my 1 content");
		dao.save(data);
		data = new DataModel<String>(1222222222l, "my 2 content");
		dao.save(data);
		data = new DataModel<String>(1333333333l, "my 3 content");
		dao.save(data);
		data = new DataModel<String>(1444444444l, "my 4 content");
		dao.save(data);
		data = new DataModel<String>(1555555555l, "my 5 content");
		dao.save(data);
		data = new DataModel<String>(1666666666l, "my 6 content");
		dao.save(data);
		data = new DataModel<String>(1777777777l, "my 7 content");
		dao.save(data);
		
		DataModel<String> temp = dao.find(1444444444l);
		
		System.out.println("print file first time: " );
		dao.printAll();
		
		System.out.println("find before delete: "+"getContent: "+temp.getContent() + " getDataModelId: " + temp.getDataModelId());
		
		dao.delete(temp);
		
		temp = dao.find(temp.getDataModelId());

		if(temp == null)
			System.out.println( "delete work!" );
		else
			System.out.println("delete not work! "+temp.getContent() + " " + temp.getDataModelId());

		
		System.out.println("print file second time: " );
		dao.printAll();
		
	}

}
