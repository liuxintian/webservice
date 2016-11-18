package com.omt.cms.master.data.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omt.cms.master.entity.AsxRegistry;
import com.omt.cms.master.repo.op.AsxRegistryOperations;

public class AsxRegistryOperationTest extends BaseRepoTest{
	
	@Autowired private AsxRegistryOperations ops;

	@Test
	public void populate() throws IOException{
		//List<String> lines = FileUtils.readLines(new File("/home/shiva/Desktop/scratch/temp/companies.csv"));
		List<String> lines = new ArrayList<>();
		for(String line : lines){
			if(StringUtils.isNotBlank(line)){
				String[] values = StringUtils.split(line, ",");
				String name = values[0];
				String asx =  values[1];
				String industry =  values[2];
				String subInd = null;
				if(values.length > 3){
					subInd = values[3];
				}
				AsxRegistry registry = new AsxRegistry();
				registry.setCompanyName(name);
				registry.setCompanyTicker(asx.toLowerCase());
				registry.setIndustry(industry);
				registry.setSubIndustry(subInd);
				try{
					ops.add(registry);	
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
	}
}
