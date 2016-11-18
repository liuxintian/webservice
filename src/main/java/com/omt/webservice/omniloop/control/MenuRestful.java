package com.omt.webservice.omniloop.control;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.omt.webservice.omniloop.entity.CodeMenu;
import com.omt.webservice.omniloop.entity.Menu;


@RestController
@RequestMapping(value="/v1")
public class MenuRestful {
	private Logger omtlogger = Logger.getLogger(MenuRestful.class);
	
	@RequestMapping(value="/{exchange}/{code}/menu", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody CodeMenu getMenuItems(@PathVariable("exchange") String exchange, @PathVariable("code") String code){
		omtlogger.info("--menu request with parameters exchange:code:menu :"+exchange+":" + code);
		
		CodeMenu codemenu = new CodeMenu();
		codemenu.setCode(exchange+":"+code);
		List<Menu> menulist = new ArrayList<Menu>();
		Menu menuone = new Menu();
		menuone.setId("001");
		menuone.setIsEnabled(true);
		menuone.setTitle("Voting");
		menulist.add(menuone);
		
		menuone = new Menu();
		menuone.setId("002");
		menuone.setIsEnabled(true);
		menuone.setTitle("Live AGM");
		menulist.add(menuone);
		
		menuone = new Menu();
		menuone.setId("003");
		menuone.setIsEnabled(true);
		menuone.setTitle("Holdings");
		menulist.add(menuone);
		
		menuone = new Menu();
		menuone.setId("004");
		menuone.setIsEnabled(false);
		menuone.setTitle("Questions");
		menulist.add(menuone);
		
		menuone = new Menu();
		menuone.setId("005");
		menuone.setIsEnabled(false);
		menuone.setTitle("Help");
		menulist.add(menuone);
		
		codemenu.setMenu(menulist);
		return codemenu;
	}

	@RequestMapping(value="/{exchange}/{code}/report", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody CodeMenu getReportItems(@PathVariable("exchange") String exchange, @PathVariable("code") String code){
		omtlogger.info("--report request with parameters exchange:code:menu :"+exchange+":" + code);
		
		CodeMenu codemenu = new CodeMenu();
		codemenu.setCode(exchange+":"+code);
		List<Menu> menulist = new ArrayList<Menu>();
		Menu menuone = new Menu();
		menuone.setId("001");
		menuone.setIsEnabled(true);
		menuone.setTitle("Voting");
		menulist.add(menuone);
		
		menuone = new Menu();
		menuone.setId("002");
		menuone.setIsEnabled(true);
		menuone.setTitle("Live AGM");
		menulist.add(menuone);
		
		menuone = new Menu();
		menuone.setId("003");
		menuone.setIsEnabled(true);
		menuone.setTitle("Holdings");
		menulist.add(menuone);
		
		menuone = new Menu();
		menuone.setId("004");
		menuone.setIsEnabled(false);
		menuone.setTitle("Questions");
		menulist.add(menuone);
		
		menuone = new Menu();
		menuone.setId("005");
		menuone.setIsEnabled(false);
		menuone.setTitle("Help");
		menulist.add(menuone);
		
		codemenu.setMenu(menulist);
		return codemenu;
	}
	
}
