package com.omt.cms.user.repo.op.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.common.Encryptor;
import com.omt.cms.core.common.SystemCodes.VerificationContext;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;
import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserVerification;
import com.omt.cms.user.repo.UserRepository;
import com.omt.cms.user.repo.op.UserOperations;
import com.omt.cms.user.repo.op.UserVerificationOperations;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class UserOpImpl extends BaseCrudOpImpl<User> implements UserOperations {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserOpImpl.class);
	@Autowired UserRepository userRepo;
	@Autowired UserVerificationOperations userVerOps;

	public UserOpImpl() {
		this.entityType = User.class;
	}

	@Override
	public PagingAndSortingRepository<User, Long> getRepository() {
		return userRepo;
	}

	@Override
	public User findUser(String loginName) {
		return userRepo.findByLoginName(loginName);
	}

	@Override
	public User findById(Long userId) {
		return userRepo.findOne(userId);
	}
	
	@Override
	public boolean loginNameExists(String loginName) {
		long count = userRepo.countByLoginName(loginName);
		return count > 0;
	}

	@Override
	public User add(User user) {
		return userRepo.save(user);
	}

	@Override
	public User update(User user) {
		return userRepo.save(user);
	}

	@Override
	public UserVerification genMailVerToken(Long userId, String email) {
		return generateRecord(userId, VerificationContext.USER_MAIL_VERIFY.getValue(), email);
	}

	@Override
	public UserVerification genPhoneVerToken(Long userId, String phoneNbr) {
		return generateRecord(userId, VerificationContext.USER_PHONE_VERIFY.getValue(), phoneNbr);
	}

	@Override
	public UserVerification genForgotPassTokenEmail(Long userId, String email) {
		return generateRecord(userId, VerificationContext.USER_FORGOT_PASSWORD_EMAIL.getValue(), email);
	}

	@Override
	public UserVerification genForgotPassTokenPhone(Long userId, String phone) {
		return generateRecord(userId, VerificationContext.USER_FORGOT_PASSWORD_PHONE.getValue(), phone);
	}

	@Override
	public boolean isValidMailVerToken(String verToken) {
		UserVerification userMailVT = userVerOps.findByToken(verToken);
		int context = VerificationContext.USER_MAIL_VERIFY.getValue();
		return userMailVT!=null && userMailVT.isValidToken() && userMailVT.getContext()==context;
	}
	
	@Override
	public UserVerification findRecordByMailVerToken(String verToken) {
		UserVerification userMailVT = userVerOps.findByToken(verToken);
		int context = VerificationContext.USER_MAIL_VERIFY.getValue();
		if(userMailVT!=null && userMailVT.isValidToken() && userMailVT.getContext()==context){
			return userMailVT;
		}
		return null;
	}

	@Override
	public UserVerification findRecordByEmailFPVerToken(Long userId, String verToken) {
		UserVerification userVT = userVerOps.findByUserToken(userId, verToken);
		int context = VerificationContext.USER_FORGOT_PASSWORD_EMAIL.getValue();
		if(userVT!=null && userVT.isValidToken() && userVT.getContext()==context){
			return userVT;
		}
		return null;
	}

	
	@Override
	public UserVerification findRecordByUserPhoneVerToken(Long userId, String verToken) {
		UserVerification userVT = userVerOps.findByUserToken(userId, verToken);
		int context = VerificationContext.USER_PHONE_VERIFY.getValue();
		if(userVT!=null && userVT.isValidToken() && userVT.getContext()==context){
			return userVT;
		}
		return null;
	}

	@Override
	public User verifyMailToken(User user, UserVerification userMailVT) {
		int context = VerificationContext.USER_MAIL_VERIFY.getValue();
		if(userMailVT!=null && userMailVT.isValidToken() && userMailVT.getContext()==context){
			long createdFor = userMailVT.getCreatedFor().longValue();
			long userId = user.getId().longValue();
			if(createdFor == userId){
				user.setEmailValid(true);
				user.setLastUpdated(DateHelper.getCurTimestamp());
				user = update(user);
				userVerOps.delete(userMailVT);
			}
		}		
		return user;
	}

	@Override
	public User verifyPhoneToken(User user, UserVerification userMailVT) {
		int context = VerificationContext.USER_PHONE_VERIFY.getValue();
		if(userMailVT!=null && userMailVT.isValidToken() && userMailVT.getContext()==context){
			long createdFor = userMailVT.getCreatedFor().longValue();
			long userId = user.getId().longValue();
			if(createdFor == userId){
				user.setPhoneValid(true);
				user.setLastUpdated(DateHelper.getCurTimestamp());
				user = update(user);
				userVerOps.delete(userMailVT);
			}
		}		
		return user;
	}

	
	private UserVerification generateRecord(Long userId, int context, String src){
		String token = null;
		if(context == VerificationContext.USER_PHONE_VERIFY.getValue() || context == VerificationContext.USER_FORGOT_PASSWORD_PHONE.getValue()){
			token = Encryptor.getInstance().getRandomNumber(6);
		}else{
			token = Encryptor.getInstance().getRandomString(20);
		}
		UserVerification userVT = userVerOps.findByContextRefAndContext(userId, context);
		if(userVT==null){
			userVT = new UserVerification();
			userVT.setContext(context);
			userVT.setCreatedFor(userId);
			userVT.setContextRefKey(userId);
			userVT.setToken(token);
			userVT.setMetadata(src);
			userVerOps.add(userVT);
		}else if(!userVT.isValidToken()){
			userVT.setToken(token);
			userVT.setCreatedOn(DateHelper.getCurTimestamp());
			userVerOps.update(userVT);
		}
		LOGGER.info("Verification token for user :{}, TOKEN:{}, context:{}", userId, userVT.getToken(), context);
		return userVT;
	}
	
	@Override
	public void deleteVerRecord(UserVerification userVT) {
		userVerOps.delete(userVT);
	}

	@Override
	public UserVerification findRecordByPhoneFPVerToken(Long userId, String verToken) {
		UserVerification userVT = userVerOps.findByUserToken(userId, verToken);
		int context = VerificationContext.USER_FORGOT_PASSWORD_PHONE.getValue();
		if(userVT!=null && userVT.isValidToken() && userVT.getContext()==context){
			return userVT;
		}
		return null;
	}

	@Override
	public UserVerification getForgotPasswordToken(Long userId) {
		int context = VerificationContext.USER_FORGOT_PASSWORD_EMAIL.getValue();
		return userVerOps.findByContextRefAndContext(userId, context);
	}
	
}
