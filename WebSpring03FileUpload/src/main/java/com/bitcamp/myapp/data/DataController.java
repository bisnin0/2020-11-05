package com.bitcamp.myapp.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class DataController {
	@Autowired
	public DataDAO dDao; //servlet-context.xml에 dao세팅함 변수이름은 똑같아야한다. 세팅한것과.. dDao
	
	@RequestMapping("/dataList")
	public ModelAndView dataList() {
		List<DataVO> list = dDao.allList();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("data/dataList");
		return mav;
	}
}
