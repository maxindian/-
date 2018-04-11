package com.wy.java;

import java.sql.*;
import java.util.*;
import java.lang.Double.*;

import com.wy.java.*;

public class DBHandle {
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	public int getFlag(String sql){
		int flag=0;
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				flag=rs.getInt(1);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return flag;
	}
	public ArrayList<Ir_list> getIr_list(String sql){
		ArrayList<Ir_list> ir=new ArrayList<Ir_list>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Ir_list ir_=new Ir_list();
				ir_.setIr_ID(rs.getInt(1));
				ir_.setTitle_name(rs.getString(2));
				ir_.setDirecter(rs.getString(3));
				ir_.setCurrent_time(rs.getDate(4));
				ir.add(ir_);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}return ir;
	}
	public ArrayList<qa_list> getQa_list(String sql){
		ArrayList<qa_list> ir=new ArrayList<qa_list>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				qa_list ir_=new qa_list();
				ir_.setQa_ID(rs.getInt(1));
				ir_.setTitle_name(rs.getString(2));
				ir_.setDirecter(rs.getString(3));
				ir_.setCurrent_time(rs.getDate(4));
				ir.add(ir_);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}return ir;
	}
	public ArrayList<qutum_list> getQu_list(String sql){
		ArrayList<qutum_list> ir=new ArrayList<qutum_list>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				qutum_list ir_=new qutum_list();
				ir_.setQutum_ID(rs.getInt(1));
				ir_.setTitle_name(rs.getString(2));
				ir_.setDirecter(rs.getString(3));
				ir_.setCurrent_time(rs.getDate(4));
				ir.add(ir_);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}return ir;
	}
	public ArrayList<yd_list> getYd_list(String sql){
		ArrayList<yd_list> ir=new ArrayList<yd_list>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				yd_list ir_=new yd_list();
				ir_.setYd_ID(rs.getInt(1));
				ir_.setTitle_name(rs.getString(2));
				ir_.setDirecter(rs.getString(3));
				ir_.setCurrent_time(rs.getDate(4));
				ir.add(ir_);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}return ir;
	}
	
	public ArrayList<gen_list> getGen_list(String sql){
		ArrayList<gen_list> ir=new ArrayList<gen_list>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				gen_list ir_=new gen_list();
				ir_.setGen_ID(rs.getInt(1));
				ir_.setTitle_name(rs.getString(2));
				ir_.setDirecter(rs.getString(3));
				ir_.setCurrent_time(rs.getDate(4));
				ir.add(ir_);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}return ir;
	}
	
	public ArrayList<String> getPassword(String sql){
		ArrayList<String> psd=new ArrayList<String>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				psd.add(rs.getString(1));
			}			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return psd;
	}
	public int getRowCount(String sql){
		int pageCount=0;
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				pageCount=rs.getInt(1);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return pageCount;
	}
	
	public double getAvgScore(String sql){
		double avgScore=0;
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				avgScore=rs.getDouble(1);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return avgScore;
	}
	
	public ArrayList<User> getStudent(String sql){
		ArrayList<User> stu=new ArrayList<User>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				User st=new User();
				st.setUser_ID(rs.getInt(1));
				st.setUser_name(rs.getString(2));
				st.setUser_psd(rs.getString(3));
				st.setUser_flag(rs.getString(4));
				//int Class_ID=rs.getInt(5);
				//st.setStu_class(this.getName("select Class_name from Class_tb where Class_ID='"+Class_ID+"'"));
				stu.add(st);
			}
		}catch(Exception ex){
				ex.printStackTrace();
			}finally{				
				this.close();
			}
			return stu;
	}
	
	public ArrayList<Course> getCourse(String sql){
		ArrayList<Course> course=new ArrayList<Course>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Course cou=new Course();
				cou.setCou_ID(rs.getInt(1));
				cou.setCou_name(rs.getString(2));
				course.add(cou);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}return course;
	}
	public ArrayList<Banji> getBanji(String sql){
		ArrayList<Banji> banji=new ArrayList<Banji>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Banji bj=new Banji();
				bj.setClass_ID(rs.getInt(1));
				bj.setClass_name(rs.getString(2));
				banji.add(bj);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();			
		}
		return banji;
	}
	public ArrayList<Score> getScore(String sql){
		ArrayList<Score> score=new ArrayList<Score>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Score sco=new Score();
				sco.setScore_ID(rs.getInt(1));
				int Stu_ID=rs.getInt(2);
				sco.setStu_name(this.getName("select Stu_name from Stu_tb where Stu_ID='"+Stu_ID+"'"));
				int Class_ID=rs.getInt(3);
				sco.setClass_name(this.getName("select Class_name from Class_tb where Class_ID='"+Class_ID+"'"));
				int Tea_ID=rs.getInt(4);
				sco.setTea_name(this.getName("select Tea_name from Tea_tb where Tea_ID='"+Tea_ID+"'"));
				int Cou_ID=rs.getInt(5);
				sco.setCou_name(this.getName("select Cou_name from Cou_tb where Cou_ID='"+Cou_ID+"'"));
				sco.setScore_score(rs.getInt(6));
				score.add(sco);
	
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}return score;
	}
	
	
	public ArrayList<String> getAttribute(String sql){
		ArrayList<String> att=new ArrayList<String>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				att.add(rs.getString(1));
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return att;
	}
	public ArrayList<Integer> getTypes(String sql){
		ArrayList<Integer> att=new ArrayList<Integer>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				att.add(rs.getInt(1));
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return att;
	}
	
	public int getID(String sql){
		int ID=1;
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				ID=rs.getInt(1);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return ID;
	}
	public String getName(String sql){
		String name="";
		ResultSet rst=null;
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rst=ps.executeQuery();
			if(rst.next()){
				name=rst.getString(1);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(rst!=null){
					rst.close();
					rst=null;
				}if(ps!=null){
					ps.close();
					ps=null;
				}if(conn!=null){
					conn.close();
					conn=null;
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return name;
	}
	public boolean Update(String sql){
		boolean b=false;
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			int rs=ps.executeUpdate();
			if(rs!=0){
				b=true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	public void close(){
		try{
			if(rs!=null){
				rs.close();
				rs=null;
			}if(ps!=null){
				ps.close();
				ps=null;
			}if(conn!=null){
				conn.close();
				conn=null;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
