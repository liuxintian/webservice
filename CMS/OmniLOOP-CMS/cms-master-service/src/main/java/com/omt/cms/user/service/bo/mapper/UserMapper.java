package com.omt.cms.user.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.service.bo.base.AddressBO;
import com.omt.cms.core.service.bo.base.mapper.BOMapper;
import com.omt.cms.core.service.bo.base.mapper.BaseBOMapper;
import com.omt.cms.entity.Address;
import com.omt.cms.user.entity.User;
import com.omt.cms.user.service.bo.UserBO;

@Component
public class UserMapper extends BaseBOMapper implements BOMapper<User, UserBO>{

	public Class<User> getEntityType() {
		return User.class;
	}

	public Class<UserBO> getBOType() {
		return UserBO.class;
	}

	public UserBO fromEntityToBO(User entity) {
		UserBO bo = new UserBO();
		if(entity!=null){
			BeanUtils.copyProperties(entity, bo);
			if(entity.getAddress()!=null){
				AddressBO addrBO = bo.getAddress();
				if(addrBO==null){
					addrBO = new AddressBO();
				}
				BeanUtils.copyProperties(entity.getAddress(), addrBO);
				bo.setAddress(addrBO);
			}
		}
		return bo;
	}

	public User createNewEntityFromBO(UserBO bo, Long creatorId) {
		User entity = new User();
		if(bo!=null){
			BeanUtils.copyProperties(bo, entity);
			entity.setId(null);
			entity.setEmailValid(false);
			entity.setPhoneValid(false);
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

	public User copyUpdatedBOToEntity(UserBO bo, User entity, Long modifierId) {
		if(bo!=null && entity!=null){
			entity.setUserName(bo.getUserName());
			entity.setUserContact(bo.getUserContact());
			entity.setTitle(bo.getTitle());
			entity.setDateOfBirth(bo.getDateOfBirth());
			if(bo.getAddress()!=null){
				Address addr = entity.getAddress();
				if(addr==null){
					addr = new Address();
				}
				BeanUtils.copyProperties(bo.getAddress(), addr);
				entity.setAddress(addr);
			}
			if(bo.getUserUId()!=null)
				entity.setUserUId(bo.getUserUId());
			copyUpdatedCommonFields(bo, entity);
		}
		return entity;
	}

}
