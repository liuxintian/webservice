package com.omt.cms.cust.service.util;

import com.microtripit.mandrillapp.lutung.view.MandrillMessage;

public abstract class MailMessageFactory {

	protected abstract MandrillMessage createMessage();
}
