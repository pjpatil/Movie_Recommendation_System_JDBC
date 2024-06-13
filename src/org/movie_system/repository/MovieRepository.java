package org.movie_system.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.movie_system.model.AdminModel;
import org.movie_system.model.GenresModel;
import org.movie_system.model.MovieMasterModel;
import org.movie_system.model.UserModel;

import com.mysql.cj.jdbc.CallableStatement;

import mrs.predict.config.DBHelper;

public class MovieRepository extends DBHelper {
	private int uid=0;

	public boolean isAddMovie(MovieMasterModel mmodel) {
		try {
			stmt = conn.prepareStatement("insert into moviemaster values('0',?,?,?,?,?,?)");

			stmt.setString(1, mmodel.getMovtitle());
			stmt.setInt(2, mmodel.getMovyear());
			stmt.setInt(3, mmodel.getMovtime());
			stmt.setString(4, mmodel.getMovlang());
			stmt.setString(5, mmodel.getMovdtrel());
			stmt.setString(6, mmodel.getMovrelcountry());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		}

		catch (Exception ex) {
			System.out.println("Error is" + ex);
			return false;
		}
	 }

	public List<MovieMasterModel> getAllMovies() {
		List<MovieMasterModel> list = new ArrayList<MovieMasterModel>();
		try {
			stmt = conn.prepareStatement("select *from moviemaster");
			rs = stmt.executeQuery();
			while (rs.next()) {
				MovieMasterModel model = new MovieMasterModel();
			
				model.setMid(rs.getInt(1));
				model.setMovtitle(rs.getString(2));
				model.setMovyear(rs.getInt(3));
				model.setMovtime(rs.getInt(4));
				model.setMovlang(rs.getString(5));
				model.setMovdtrel(rs.getString(6));
				model.setMovrelcountry(rs.getString(7));
				list.add(model);
			}
			return list.size() > 0 ? list : null;
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return null;
		}
	}
	
	// Display movie by name 
	public List<MovieMasterModel> getMovie(String smovie)
    {
 	  
 	  List<MovieMasterModel> list = new ArrayList<MovieMasterModel>();
		try {
			stmt = conn.prepareStatement("select *from moviemaster where mov_title=?");
			stmt.setString(1, smovie);
			rs = stmt.executeQuery();
			while (rs.next()) {
				MovieMasterModel model = new MovieMasterModel();
			
				model.setMid(rs.getInt(1));
				model.setMovtitle(rs.getString(2));
				model.setMovyear(rs.getInt(3));
				model.setMovtime(rs.getInt(4));
				model.setMovlang(rs.getString(5));
				model.setMovdtrel(rs.getString(6));
				model.setMovrelcountry(rs.getString(7));
				list.add(model);
			}
			return list.size() > 0 ? list : null;
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return null;
		}
 	   
    }
	
	
	public int getMovId() {
		try
	 	   {
	 		   stmt=conn.prepareStatement("select max(mov_id) from moviemaster");
	 		   rs=stmt.executeQuery();
	 		   if(rs.next())
	 		   {
	 			   this.uid=rs.getInt(1);
	 		   }
//	 		   ++uid;
	 		   return uid;
	 		   
	 	   }
	 	   catch(Exception ex)
	 	   {
	 		   return 0;
	 		   
	 	   }
//		return 0;
	}

	
	
	
	
//    public int getUserIdAutomatic()
//    {
// 	   try
// 	   {
// 		   stmt=conn.prepareStatement("select max(userid) from user");
// 		   rs=stmt.executeQuery();
// 		   if(rs.next())
// 		   {
// 			   this.uid=rs.getInt(1);
// 		   }
// 		   ++uid;
// 		   return uid;
// 		   
// 	   }
// 	   catch(Exception ex)
// 	   {
// 		   return 0;
// 		   
// 	   }
//    }
//    public boolean isAddUser(UserModel umodel)
//    {
// 	   try
// 	   {
// 		   clst= (CallableStatement) conn.prepareCall("{call saveuser(?,?,?,?,?,?)}");
// 		   clst.setInt(1, umodel.getUid());
// 		   clst.setString(2, umodel.getUname());
// 		  clst.setInt(3, umodel.getUage());
// 		 clst.setString(4, umodel.getUmobileno());
// 		   clst.setString(5, umodel.getUpassword());
// 		  
// 		   clst.setInt(6, umodel.getMid());
// 		   boolean b=clst.execute();
// 		   return !b;
// 		  
// 		   
// 	   }
// 	   catch(Exception ex)
// 	   {
// 		   System.out.println("Exception"+ex);
// 		   return false;
// 	   }
//    }

	
	// Display Top tree movie.
	public List<MovieMasterModel> getTopTreeMovies() {
		List<MovieMasterModel> list = new ArrayList<MovieMasterModel>();
		try {
			stmt = conn.prepareStatement("select *from moviemaster order by mov_dt_rel desc limit 3");
			rs = stmt.executeQuery();
			while (rs.next()) {
				MovieMasterModel model = new MovieMasterModel();
			
				model.setMid(rs.getInt(1));
				model.setMovtitle(rs.getString(2));
				model.setMovyear(rs.getInt(3));
				model.setMovtime(rs.getInt(4));
				model.setMovlang(rs.getString(5));
				model.setMovdtrel(rs.getString(6));
				model.setMovrelcountry(rs.getString(7));
				list.add(model);
			}
			return list.size() > 0 ? list : null;
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return null;
		}
	}

	// check movie ID present
	public boolean checkMovId(int tempId) {
		try{
			stmt = conn.prepareStatement("select *from moviemaster where mov_id=?");
			stmt.setInt(1, tempId);
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}
	
	// check movie Name present
	public boolean checkMovName(String smovie) {
		try{
			stmt = conn.prepareStatement("select *from moviemaster where mov_title=?");
			stmt.setString(1, smovie);
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}
	
	
	//  Update movie Title by id
	public boolean updateTitle(int tempId, String newTitle) {
		try{
			stmt = conn.prepareStatement("update moviemaster set mov_title =? where mov_id = ?");
			stmt.setString(1, newTitle);
			stmt.setInt(2, tempId);
			int value=stmt.executeUpdate();
			
			return value > 0 ? true : false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}
	

	// delete MOvie by id
	public boolean deleteMovie(int newId) {
		try{
			stmt = conn.prepareStatement("delete from moviemaster where mov_id =?");
			stmt.setInt(1, newId);
			int value=stmt.executeUpdate();
			
			return value > 0 ? true : false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

	
	public GenresModel getConcat(String movtit) {
		List<GenresModel> list=new ArrayList<GenresModel>();
		GenresModel gModel=new GenresModel();
		try{
			stmt=conn.prepareStatement("select m.mov_id, m.mov_title,m.mov_year,m.mov_time,m.mov_lang,m.mov_dt_rel,m.mov_rel_country,group_concat(g.gen_title) from moviemaster m inner join movie_genres mg on mg.mov_id=m.mov_id inner join genres g on g.gen_id=mg.gen_id where m.mov_title=? group by m.mov_id, m.mov_title,m.mov_year,m.mov_time,m.mov_lang,m.mov_dt_rel,m.mov_rel_country");
			stmt.setString(1, movtit);
			rs=stmt.executeQuery();
			if(rs.next())
	 		   {
				gModel.setMid(rs.getInt(1));
				gModel.setMovtitle(rs.getString(2));
				gModel.setMovyear(rs.getInt(3));
				gModel.setMovtime(rs.getInt(4));
				gModel.setMovlang(rs.getString(5));
				gModel.setMovdtrel(rs.getString(6));
				gModel.setMovrelcountry(rs.getString(7));
				gModel.setGentitle(rs.getString(8));
				
				list.add(gModel);
	 		   }
			return gModel;
		}
		catch(Exception ex)
	 	   {
//	 		   System.out.println("Erroer is"+ex);
	 		   return gModel;
	 	   } 
	}

	
	
}
