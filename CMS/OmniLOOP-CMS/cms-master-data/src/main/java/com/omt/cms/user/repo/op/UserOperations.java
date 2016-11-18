package com.omt.cms.user.repo.op;

import org.springframework.stereotype.Component;

import com.omt.cms.repo.op.base.CrudOperations;
import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserVerification;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public interface UserOperations extends CrudOperations<User> {

	public User findUser(String loginName);
	
	public User findById(Long userId);

	public boolean loginNameExists(String loginName);
	
	public User add(User user);
	
	public User update(User user);
	
	public UserVerification genMailVerToken(Long userId, String email);

	public UserVerification genPhoneVerToken(Long userId, String phoneNbr);

	public boolean isValidMailVerToken(String verToken);
	
	public UserVerification findRecordByMailVerToken(String verToken);
	
	public UserVerification findRecordByUserPhoneVerToken(Long userId, String verToken);

	public UserVerification findRecordByPhoneFPVerToken(Long userId, String verToken);
	
	public UserVerification findRecordByEmailFPVerToken(Long userId, String verToken);

	public User verifyMailToken(User user, UserVerification userMailVT);
	
	public User verifyPhoneToken(User user, UserVerification userMailVT);

	public UserVerification genForgotPassTokenEmail(Long userId, String email);
	
	public UserVerification genForgotPassTokenPhone(Long userId, String phone);
	
	public void deleteVerRecord(UserVerification userVT);

	public UserVerification getForgotPasswordToken(Long userId);

}
