package com.shxt.cme.modules.product.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.shxt.cme.domain.Member;
import com.shxt.cme.domain.Merchont;
import com.shxt.cme.domain.Product;
import com.shxt.cme.domain.Shop;
import com.shxt.cme.domain.User;
import com.shxt.framework.persistence.jdbc.support.BaseDao;
import com.shxt.framework.utils.DbUtils;

@Repository
public class ProductDao extends BaseDao {
	public Page<Product> findWithPage1(Pageable pageable, User user) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * ");
		sql.append(" FROM t_production ");
		sql.append(" WHERE pro_type=1 and shop_id='" + user.getUserId() + "' ");
		Object[] args = new Object[] {};
		return queryForPage(sql.toString(), pageable, new ProductRowMapper(),
				args);

	}

	public Page<Product> findWithPage2(Pageable pageable, User user) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * ");
		sql.append(" FROM t_production ");
		sql.append(" WHERE pro_type=2 and shop_id='" + user.getUserId() + "' ");
		Object[] args = new Object[] {};
		return queryForPage(sql.toString(), pageable, new ProductRowMapper(),
				args);

	}

	public Page<Product> findWithPage3(Pageable pageable, User user) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * ");
		sql.append(" FROM t_production ");
		sql.append(" WHERE pro_type=3 and shop_id='" + user.getUserId() + "' ");
		Object[] args = new Object[] {};
		return queryForPage(sql.toString(), pageable, new ProductRowMapper(),
				args);

	}

	private class ProductRowMapper implements ParameterizedRowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();

			product.setProId(rs.getString("pro_id"));
			product.setProName(rs.getString("pro_name"));
			product.setProPrice(rs.getString("pro_price"));
			product.setDisPrice(rs.getString("dis_price"));
			product.setProType(rs.getInt("pro_type"));
			product.setIntroduction(rs.getString("introduction"));
			product.setImageAddress(rs.getString("image_address"));
			product.setShopId(rs.getString("shop_id"));
			return product;
		}
	}
	
	public Merchont findMerchontType(User user) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * ");
		sql.append(" FROM t_merchont ");
		sql.append(" WHERE merchont_id='" + user.getUserId() + "' ");
		Object[] args = new Object[] {};
		return queryForObject(sql.toString(), args, new MerchontRowMapper());
	}
	
	private class MerchontRowMapper implements ParameterizedRowMapper<Merchont> {
		@Override
		public Merchont mapRow(ResultSet rs, int rowNum) throws SQLException {
			Merchont merchont = new Merchont();
			merchont.setMerchontId(rs.getString("merchont_Id"));
			merchont.setAccoutNum(rs.getString("accout_Num"));
			merchont.setRealName(rs.getString("real_Name"));
			merchont.setMerchontType(rs.getInt("merchont_Type"));
			merchont.setId(rs.getString("id"));
			merchont.setUserId(rs.getString("user_id"));
			merchont.setPhone(rs.getString("phone"));
			return merchont;
		}
	}
	
	public void insertSelective1(Product product, User user) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into  t_production(pro_id,pro_name,pro_price,dis_price,pro_type,image_address,introduction,shop_id) ");
		sql.append(" values(?,?,?,?,?,?,?,?) ");
		Object[] args = new Object[] { DbUtils.getKey(),
				product.getProPrice(), product.getDisPrice(), 1,
				product.getImageAddress(),
				product.getIntroduction(), user.getUserId() };
		update(sql.toString(), args);
	}

	public void insertSelective2(Product product, User user) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into  t_production(pro_id,pro_name,pro_price,dis_price,pro_type,image_address,introduction,shop_id) ");
		sql.append(" values(?,?,?,?,?,?,?,?) ");
		Object[] args = new Object[] { DbUtils.getKey(),
				product.getProPrice(), product.getDisPrice(), 2,
				product.getImageAddress(), 
				product.getIntroduction(), user.getUserId() };
		update(sql.toString(), args);
	}

	public void insertSelective3(Product product, User user) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into  t_production(pro_id,pro_name,pro_price,dis_price,pro_type,image_address,introduction,shop_id) ");
		sql.append(" values(?,?,?,?,?,?,?,?) ");
		Object[] args = new Object[] { DbUtils.getKey(),
				product.getProPrice(), product.getDisPrice(), 3,
				product.getImageAddress(),
				product.getIntroduction(), user.getUserId() };
		update(sql.toString(), args);
	}

	public int updateByPrimaryKeySelective(Product product) {
		String sql = "UPDATE  t_production " + "SET " + "pro_name='"
				+ product.getProName() + "'" + ",pro_price='"
				+ product.getProPrice() + "'" + ",dis_price='"
				+ product.getDisPrice() + "'" + ",image_address='"
				+ product.getImageAddress() + "'" + ",introduction='"
				+ product.getIntroduction() + "'" + "WHERE pro_id='"
				+ product.getProId() + "'";
		int ss = update(sql, null);
		return ss;
	}

	public Product findInfoPro(Product product) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * ");
		sql.append(" FROM t_production ");
		sql.append(" WHERE pro_id='" + product.getProId() + "' ");
		Object[] args = new Object[] {};
		return queryForObject(sql.toString(), args, new ProductRowMapper());
	}

	public int deleteByPrimaryKeySelective(Product product) {
		String sql = "DELETE FROM  t_production " + "WHERE pro_id='"
				+ product.getProId() + "'";
		int ss = update(sql, null);
		return ss;
	}

}
