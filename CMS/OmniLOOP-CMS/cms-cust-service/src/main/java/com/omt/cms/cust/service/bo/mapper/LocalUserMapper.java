package com.omt.cms.cust.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.bo.base.AddressBO;
import com.omt.cms.core.service.bo.base.CommonBO;
import com.omt.cms.core.service.bo.base.LocalUserBO;
import com.omt.cms.cust.entity.LocalUser;
import com.omt.cms.entity.Address;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class LocalUserMapper {

	public LocalUserBO fromEntityToBO(LocalUser entity) {
		LocalUserBO bo = new LocalUserBO();
		if(entity!=null){
			BeanUtils.copyProperties(entity, bo);
			bo.setCompanyId(entity.getCompany().getId());
			if(entity.getAddress()!=null){
				AddressBO addr = bo.getAddress();
				if(addr==null){
					addr = new AddressBO();
				}
				BeanUtils.copyProperties(entity.getAddress(), addr);
				bo.setAddress(addr);
			}
		}
		return bo;
	}

	public LocalUser createNewEntityFromBO(LocalUserBO bo, Long creatorId) {
		LocalUser entity = new LocalUser();
		if(bo!=null){
			BeanUtils.copyProperties(bo, entity);
			entity.setStatus(RecordStatus.ACTIVE.getValue());
			if(bo.getAddress()!=null){
				Address addr = entity.getAddress();
				if(addr==null){
					addr = new Address();
				}
				BeanUtils.copyProperties(bo.getAddress(), addr);
				entity.setAddress(addr);
			}
		}
		return entity;
	}

	public void updateOnlyStatus(LocalUserBO bo, LocalUser entity){
        if(bo!=null && entity!=null && bo.getStatus()!=null){
                entity.setLastUpdated(DateHelper.getCurTimestamp());
                entity.setStatus(bo.getStatus());
        }
    }


	public LocalUser copyUpdatedBOToEntity(LocalUserBO bo, LocalUser entity, Long modifierId) {
		if(bo!=null && entity!=null){
			if(bo.getStatus()==null){
				entity.setLoginName(bo.getLoginName());
				entity.setUserName(bo.getUserName());
				if(bo.getAddress()!=null){
					Address addr = entity.getAddress();
					if(addr==null){
						addr = new Address();
					}
					BeanUtils.copyProperties(bo.getAddress(), addr);
					entity.setAddress(addr);
				}
				entity.setUserContact(bo.getUserContact());
				copyUpdatedCommonFields(bo, entity);
			}else{
				updateOnlyStatus(bo, entity);
			}
		}
		return entity;
	}

	private void copyUpdatedCommonFields(CommonBO bo, LocalUser entity){
		if(bo!=null && entity!=null){
			entity.setLastUpdated(DateHelper.getCurTimestamp());
			entity.setNotes(bo.getNotes());
			entity.setTagRole(bo.getTagRole());

			if(bo.getValidUntil()!=null){
				entity.setValidUntil(bo.getValidUntil());	
			}
		}
	}

}
