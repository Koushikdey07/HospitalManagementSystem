package com.dao;
import com.entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SpecialistDao {

	private Connection conn;

	public SpecialistDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	
	public boolean addSpecialist(String spec) {
		boolean f = false;
		try {
			String sql = "insert into specialist_details(spec_name) values(?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, spec);
			int i = ps.executeUpdate();
			if(i==1) {
				f=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public List<Specialist> getAllSpecialist(){
		List <Specialist> list = new ArrayList<Specialist>();
		Specialist s = null;
		try {
			String sql = "select id,spec_name from specialist_details";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				s = new Specialist();
				s.setId(rs.getInt(1));
				s.setSpecialistName(rs.getString(2));
				list.add(s); 
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int countSpecialist() {
		int i=0;
		try {
			String sql = "select id,spec_name from specialist_details";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
