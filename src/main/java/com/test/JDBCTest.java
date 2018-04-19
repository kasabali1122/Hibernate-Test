package com.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.Statement;  
@Controller  
public class JDBCTest {  
   
    @RequestMapping(value = "/hibernateAdd", method = RequestMethod.GET)
    public ModelAndView addwithHibernate(String code, String name){  
	 ModelAndView modelAndView = new ModelAndView();
	
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        Stock stock = new Stock();
        
        stock.setStockCode(code);
        stock.setStockName(name);
        
        
        session.save(stock);
        session.getTransaction().commit();
      
    	modelAndView.setViewName("messages");
    	String message =  "added with code :" + stock.getStockCode();
    	modelAndView.addObject("message", message);
    	return modelAndView;

    }  
    @RequestMapping(value = "/hibernateDelete", method = RequestMethod.GET)
    public ModelAndView deletewithHibernate(String code){  
    	
    	ModelAndView modelAndView = new ModelAndView();
	
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();

        
        Query query = session.createQuery("delete from Stock where stockCode=:param1");
        query.setParameter("param1", code);  
         
        int result = query.executeUpdate();
        session.getTransaction().commit();
        if (result > 0) {
            System.out.println("DB process completed");
        }
  
    	String message =  "Deleted with stock code :" + code;
    	modelAndView.addObject("message", message);
    	return modelAndView;

    }  
}  
