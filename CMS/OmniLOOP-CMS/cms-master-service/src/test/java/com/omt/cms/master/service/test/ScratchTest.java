package com.omt.cms.master.service.test;

import java.util.ArrayList;
import java.util.List;

public class ScratchTest {

	public static void main(String[] args) {

		List<Long> curMenusIds = new ArrayList<>();
		List<Long> menuIds = new ArrayList<>();
		
		curMenusIds.add(1L);
		curMenusIds.add(2L);
		curMenusIds.add(3L);
		
		menuIds.add(3L);
		menuIds.add(4L);
		menuIds.add(5L);
		
		
		List<Long> newIds = new ArrayList<>();
		List<Long> exIds = new ArrayList<>();
		List<Long> delIds = new ArrayList<>();
		
		for(Long menuId : menuIds){
			if(curMenusIds.contains(menuId)){
				exIds.add(menuId);
			}else{
				newIds.add(menuId);
			}
		}
		
		for(Long cmId : curMenusIds){
			if(!menuIds.contains(cmId)){
				delIds.add(cmId);
			}
		}

		System.out.println("NW->" + newIds);
		System.out.println("CUR->" + exIds);
		System.out.println("DEL->" +  delIds);
	}

}
